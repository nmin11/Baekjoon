import java.util.Scanner;

public class Main {
    static int n;

    static void dfs(int num, int digit) {
        if (digit == n && isPrime(num)) {
            System.out.println(num);
            return;
        }

        for (int i = 1; i < 10; i++) {
            if (i % 2 == 0) continue;

            if (isPrime(num * 10 + i)) {
                dfs(num * 10 + i, digit + 1);
            }
        }
    }

    static boolean isPrime(int num) {
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        dfs(2, 1);
        dfs(3, 1);
        dfs(5, 1);
        dfs(7, 1);
    }
}