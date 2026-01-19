import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long k = Long.parseLong(br.readLine());

        if (k == 1) {
            System.out.println(1);
            return;
        }

        if (k == 2) {
            System.out.println(10);
            return;
        }

        long[] fibonacci = new long[100];
        fibonacci[0] = 1;
        fibonacci[1] = 2;
        fibonacci[2] = 3;

        int len = 2;
        while (true) {
            fibonacci[len] = fibonacci[len - 1] + fibonacci[len - 2];
            if (fibonacci[len] > k) break;
            len++;
        }

        StringBuilder sb = new StringBuilder();
        int digit = len - 1;

        while (digit >= 0) {
            if (fibonacci[digit] <= k) {
                sb.append('1');
                k -= fibonacci[digit];
            } else {
                sb.append('0');
            }

            digit--;
        }

        System.out.println(sb);
    }
}
