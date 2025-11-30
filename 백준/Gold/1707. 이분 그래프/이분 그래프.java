import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<List<Integer>> graph;
    static int[] visited;
    static boolean isBipartite;

    private static void dfs(int node, int checkNum) {
        visited[node] = checkNum;

        for (int next : graph.get(node)) {
            if (visited[next] == -1) {
                dfs(next, 1 - checkNum);
            } else if (visited[next] == checkNum) {
                isBipartite = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < k; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            for (int i = 0; i <= v; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            visited = new int[v + 1];
            Arrays.fill(visited, -1);
            isBipartite = true;

            for (int i = 1; i <= v; i++) {
                if (visited[i] == -1) {
                    dfs(i, 0);
                    if (!isBipartite) break;
                }
            }

            sb.append(isBipartite ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }
}
