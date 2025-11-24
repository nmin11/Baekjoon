import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean isPalindrome(int num) {
        String s = String.valueOf(num);
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int limit = 10000000;
        boolean[] isPrime = new boolean[limit + 1];
        for (int i = 2; i <= limit; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i <= limit; i++) {
            if (!isPrime[i]) continue;

            for (int j = i * i; j <= limit; j += i) {
                isPrime[j] = false;
            }
        }

        for (int i = n; i <= limit; i++) {
            if (isPrime[i] && isPalindrome(i)) {
                System.out.println(i);
                break;
            }
        }
    }
}
