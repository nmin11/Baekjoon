import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
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
        for (int i = 0; i < n; i++) {
            tree[leafStartIndex + i] = Long.parseLong(br.readLine());
        }

        buildTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            long right = Long.parseLong(st.nextToken());

            if (command == 1) {
                update(left - 1, right);
            } else {
                long sum = query(left - 1, (int) right - 1);
                sb.append(sum).append('\n');
            }
        }

        System.out.print(sb);
    }

    private static void buildTree() {
        for (int i = leafStartIndex - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    private static void update(int index, long value) {
        int treeIndex = leafStartIndex + index;
        long diff = value - tree[treeIndex];

        while (treeIndex > 0) {
            tree[treeIndex] += diff;
            treeIndex /= 2;
        }
    }

    private static long query(int left, int right) {
        int leftIndex = leafStartIndex + left;
        int rightIndex = leafStartIndex + right;
        long sum = 0;

        while (leftIndex <= rightIndex) {
            if (leftIndex % 2 == 1) {
                sum += tree[leftIndex];
                leftIndex++;
            }

            if (rightIndex % 2 == 0) {
                sum += tree[rightIndex];
                rightIndex--;
            }

            leftIndex /= 2;
            rightIndex /= 2;
        }

        return sum;
    }
}
