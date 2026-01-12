import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        boolean[] used = new boolean[n + 1];
        long[] factorial = new long[n + 1];
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int command = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        if (command == 1) {
            long k = Long.parseLong(st.nextToken());
            int[] result = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                int count = 1;

                for (int j = 1; j <= n; j++) {
                    if (used[j]) continue;

                    if (k <= count * factorial[n - i]) {
                        k -= (count - 1) * factorial[n - i];
                        result[i] = j;
                        used[j] = true;
                        break;
                    }

                    count++;
                }
            }

            for (int i = 1; i <= n; i++) {
                sb.append(result[i]).append(" ");
            }
        } else {
            int[] permutation = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                permutation[i] = Integer.parseInt(st.nextToken());
            }

            long order = 1;

            for (int i = 1; i <= n; i++) {
                long smallerCount = 0;

                for (int j = 1; j < permutation[i]; j++) {
                    if (!used[j]) smallerCount++;
                }

                order += smallerCount * factorial[n - i];
                used[permutation[i]] = true;
            }

            sb.append(order);
        }

        System.out.print(sb);
    }
}
