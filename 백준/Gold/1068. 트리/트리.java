import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static List<List<Integer>> tree;
    private static boolean[] visited;
    private static int deletedNode;
    private static int leafCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        visited = new boolean[n];
        StringTokenizer st = new StringTokenizer(br.readLine());

        int root = -1;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) {
                root = i;
            } else {
                tree.get(parent).add(i);
            }
        }

        deletedNode = Integer.parseInt(br.readLine());

        if (deletedNode == root) {
            System.out.println(0);
        } else {
            dfs(root);
            System.out.println(leafCount);
        }
    }

    private static void dfs(int node) {
        if (node == deletedNode) return;

        visited[node] = true;
        int childCount = 0;

        for (int child : tree.get(node)) {
            if (!visited[child] && child != deletedNode) {
                childCount++;
                dfs(child);
            }
        }

        if (childCount == 0) leafCount++;
    }
}
