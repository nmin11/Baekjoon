import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static int[] tree;
    private static int leafStartIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int treeHeight = 0;
        int length = n;
        while (length > 0) {
            length /= 2;
            treeHeight++;
        }

        int treeSize = (int) Math.pow(2, treeHeight + 1);
        leafStartIndex = treeSize / 2;
        tree = new int[treeSize];
        Arrays.fill(tree, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            tree[leafStartIndex + i] = Integer.parseInt(br.readLine());
        }

        buildTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(query(a - 1, b - 1)).append('\n');
        }

        System.out.print(sb);
    }

    private static void buildTree() {
        for (int i = leafStartIndex - 1; i > 0; i--) {
            tree[i] = Math.min(tree[i * 2], tree[i * 2 + 1]);
        }
    }

    private static int query(int left, int right) {
        int leftIndex = leafStartIndex + left;
        int rightIndex = leafStartIndex + right;
        int min = Integer.MAX_VALUE;

        while (leftIndex <= rightIndex) {
            if (leftIndex % 2 == 1) {
                min = Math.min(min, tree[leftIndex]);
                leftIndex++;
            }

            if (rightIndex % 2 == 0) {
                min = Math.min(min, tree[rightIndex]);
                rightIndex--;
            }

            leftIndex /= 2;
            rightIndex /= 2;
        }

        return min;
    }
}
