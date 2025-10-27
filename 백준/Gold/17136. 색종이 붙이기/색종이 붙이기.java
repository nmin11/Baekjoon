import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] board = new int[10][10];
    static int[] remain = {0, 5, 5, 5, 5, 5};
    static int result = Integer.MAX_VALUE;

    private static boolean isValid(int x, int y, int size) {
        if (x + size > 10 || y + size > 10) return false;

        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (board[i][j] == 0) return false;
            }
        }

        return true;
    }

    private static void fill(int x, int y, int size, int flag) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                board[i][j] = flag;
            }
        }
    }

    private static void backtracking(int x, int y, int count) {
        if (count >= result) return;

        if (y == 10) {
            result = count;
            return;
        }

        int nextX = (x + 1) % 10;
        int nextY = (nextX == 0) ? y + 1 : y;

        if (board[y][x] == 0) {
            backtracking(nextX, nextY, count);
            return;
        }

        for (int size = 5; size >= 1; size--) {
            if (remain[size] == 0 || !isValid(x, y, size)) continue;

            remain[size]--;
            fill(x, y, size, 0);
            backtracking(nextX, nextY, count + 1);
            fill(x, y, size, 1);
            remain[size]++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0, 0);

        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
}
