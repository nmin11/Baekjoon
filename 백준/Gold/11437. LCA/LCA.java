import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<List<Integer>> tree;
    private static int[] depth;
    private static int[] parent;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        depth = new int[n + 1];
        parent = new int[n + 1];
        visited = new boolean[n + 1];

        bfs(1);

        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int lca = findLCA(a, b);
            sb.append(lca).append('\n');
        }

        System.out.print(sb);
    }

    private static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited[root] = true;
        depth[root] = 0;
        parent[root] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : tree.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    depth[next] = depth[cur] + 1;
                    parent[next] = cur;
                    queue.offer(next);
                }
            }
        }
    }

    private static int findLCA(int a, int b) {
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        while (a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
