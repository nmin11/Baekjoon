import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] combination = new int[n + m + 1][n + m + 1];
        for (int i = 0; i <= n + m; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    combination[i][j] = 1;
                } else {
                    combination[i][j] = combination[i - 1][j - 1] + combination[i - 1][j];

                    if (combination[i][j] > MAX) {
                        combination[i][j] = MAX + 1;
                    }
                }
            }
        }

        if (combination[n + m][m] < k) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0 || m > 0) {
            if (n == 0) {
                sb.append('z');
                m--;
            } else if (m == 0) {
                sb.append('a');
                n--;
            } else {
                int count = combination[n - 1 + m][m];

                if (count >= k) {
                    sb.append('a');
                    n--;
                } else {
                    sb.append('z');
                    k -= count;
                    m--;
                }
            }
        }

        System.out.println(sb);
    }
}
