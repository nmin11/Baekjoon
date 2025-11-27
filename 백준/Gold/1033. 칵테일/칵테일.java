import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Edge {
        int to, p, q;

        Edge(int to, int p, int q) {
            this.to = to;
            this.p = p;
            this.q = q;
        }
    }

    static List<List<Edge>> graph;
    static boolean[] visited;
    static long[] amounts;

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static void dfs(int node) {
        visited[node] = true;

        for (Edge edge : graph.get(node)) {
            if (!visited[edge.to]) {
                amounts[edge.to] = amounts[node] * edge.q / edge.p;
                dfs(edge.to);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        visited = new boolean[n];
        amounts = new long[n];

        long lcm = 1;
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, p, q));
            graph.get(b).add(new Edge(a, q, p));

            lcm *= p * q / gcd(p, q);
        }

        amounts[0] = lcm;
        dfs(0);

        long commonGcd = amounts[0];
        for (int i = 1; i < n; i++) {
            commonGcd = gcd(commonGcd, amounts[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(amounts[i] / commonGcd).append(' ');
        }

        System.out.print(sb);
    }
}
