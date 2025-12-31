import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new int[26][2];

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            int node = input[0].charAt(0) - 'A';
            int left = input[1].charAt(0);
            int right = input[2].charAt(0);

            tree[node][0] = (left == '.') ? -1 : left - 'A';
            tree[node][1] = (right == '.') ? -1 : right - 'A';
        }

        StringBuilder sb = new StringBuilder();
        preOrder(0, sb);
        sb.append('\n');

        inOrder(0, sb);
        sb.append('\n');

        postOrder(0, sb);
        sb.append('\n');

        System.out.print(sb);
    }

    private static void preOrder(int node, StringBuilder sb) {
        if (node == -1) return;

        sb.append((char) (node + 'A'));
        preOrder(tree[node][0], sb);
        preOrder(tree[node][1], sb);
    }

    private static void inOrder(int node, StringBuilder sb) {
        if (node == -1) return;

        inOrder(tree[node][0], sb);
        sb.append((char) (node + 'A'));
        inOrder(tree[node][1], sb);
    }

    private static void postOrder(int node, StringBuilder sb) {
        if (node == -1) return;

        postOrder(tree[node][0], sb);
        postOrder(tree[node][1], sb);
        sb.append((char) (node + 'A'));
    }
}
