import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static boolean[] visited;

    private static void addEdge(int head, int tail) {
        graph.get(head).add(tail);
        graph.get(tail).add(head);
    }

    private static void dfs(int v) {
        if (visited[v]) return;

        visited[v] = true;
        for (int i : graph.get(v)) {
            if (!visited[i]) dfs(i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n + 1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int head = Integer.parseInt(st.nextToken());
            int tail = Integer.parseInt(st.nextToken());

            addEdge(head, tail);
        }

        int count = 0;
        for (int i = 1; i < n + 1; i++) {
            if (!visited[i]) {
                count++;
                dfs(i);
            }
        }

        System.out.println(count);
    }
}