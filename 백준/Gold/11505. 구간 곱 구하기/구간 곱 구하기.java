import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static final int MOD = 1_000_000_007;
    private static long[] tree;
    private static int leafStartIndex;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int treeHeight = 0;
        int length = n;
        while (length > 0) {
            length /= 2;
            treeHeight++;
        }

        int treeSize = (int) Math.pow(2, treeHeight + 1);
        leafStartIndex = treeSize / 2;
        tree = new long[treeSize];
        Arrays.fill(tree, 1L);
        for (int i = 0; i < n; i++) {
            tree[leafStartIndex + i] = Long.parseLong(br.readLine());
        }

        buildTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            long right = Integer.parseInt(st.nextToken());

            if (command == 1) {
                update(left - 1, right);
            } else {
                long product = query(left - 1, (int) right - 1);
                sb.append(product).append('\n');
            }
        }

        System.out.print(sb);
    }

    private static void buildTree() {
        for (int i = leafStartIndex - 1; i > 0; i--) {
            tree[i] = tree[i * 2] * tree[i * 2 + 1] % MOD;
        }
    }

    private static void update(int index, long value) {
        int treeIndex = leafStartIndex + index;
        tree[treeIndex] = value;

        while (treeIndex > 1) {
            treeIndex /= 2;
            tree[treeIndex] = (tree[treeIndex * 2] * tree[treeIndex * 2 + 1]) % MOD;
        }
    }

    private static long query(int left, int right) {
        int leftIndex = leafStartIndex + left;
        int rightIndex = leafStartIndex + right;
        long product = 1;

        while (leftIndex <= rightIndex) {
            if (leftIndex % 2 == 1) {
                product = (product * tree[leftIndex]) % MOD;
                leftIndex++;
            }

            if (rightIndex % 2 == 0) {
                product = (product * tree[rightIndex]) % MOD;
                rightIndex--;
            }

            leftIndex /= 2;
            rightIndex /= 2;
        }

        return product;
    }
}
