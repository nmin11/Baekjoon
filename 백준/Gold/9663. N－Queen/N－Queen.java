import java.util.Scanner;

public class Main {
    static int[] board;
    static int n;
    static int cnt = 0;
    
    private static boolean check(int row) {
        for (int  i = 0; i < row; i++) {
            if (board[i] == board[row]) return false;
            if (Math.abs(row - i) == Math.abs(board[row] - board[i])) return false;
        }
        
        return true;
    }

    private static void backtracking(int row) {
        if (row == n) {
            cnt++;
            return;
        }
        
        for (int i = 0; i < n; i++) {
            board[row] = i;
            if (check(row)) {
                backtracking(row + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        board = new int[n];
        backtracking(0);
        System.out.println(cnt);
    }
}
