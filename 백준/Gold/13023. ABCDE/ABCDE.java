import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean isConnected;
    static boolean[] visited;

    static void dfs(int node, int depth) {
        if (depth == 5 || isConnected) {
            isConnected = true;
            return;
        }

        visited[node] = true;

        for (int v : graph.get(node)) {
            if (!visited[v]) dfs(v, depth + 1);
        }

        visited[node] = false;
    }

    static void addEdge(int u, int v) {
        graph.get(u).add(v);
        graph.get(v).add(u);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            addEdge(u, v);
        }

        for (int i = 0; i < n; i++) {
            dfs(i, 1);
            if (isConnected) break;
        }

        if (isConnected) System.out.println(1);
        else System.out.println(0);
    }
}
