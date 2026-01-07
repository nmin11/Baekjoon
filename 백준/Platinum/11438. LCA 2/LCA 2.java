import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static List<List<Integer>> tree;
    private static int[] depth;
    private static int[][] parent;
    private static boolean[] visited;
    private static int maxLevel;

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

        maxLevel = 0;
        int tmp = 1;
        while (tmp <= n) {
            tmp <<= 1;
            maxLevel++;
        }

        depth = new int[n + 1];
        parent = new int[maxLevel + 1][n + 1];
        visited = new boolean[n + 1];

        bfs(1);

        for (int k = 1; k <= maxLevel; k++) {
            for (int node = 1; node <= n; node++) {
                parent[k][node] = parent[k - 1][parent[k - 1][node]];
            }
        }

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
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
        parent[0][root] = 0;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : tree.get(cur)) {
                if (!visited[next]) {
                    visited[next] = true;
                    depth[next] = depth[cur] + 1;
                    parent[0][next] = cur;
                    queue.offer(next);
                }
            }
        }
    }

    private static int findLCA(int a, int b) {
        if (depth[a] > depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = depth[b] - depth[a];
        for (int k = 0; k <= maxLevel; k++) {
            if ((diff & (1 << k)) != 0) {
                b = parent[k][b];
            }
        }

        if (a == b) return a;

        for (int k = maxLevel; k >= 0; k--) {
            if (parent[k][a] != parent[k][b]) {
                a = parent[k][a];
                b = parent[k][b];
            }
        }

        return parent[0][a];
    }
}
