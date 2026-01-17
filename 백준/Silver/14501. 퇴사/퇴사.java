import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] task = new int[n + 1];
        int[] profit = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            task[i] = Integer.parseInt(st.nextToken());
            profit[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n + 2];

        for (int day = n; day > 0; day--) {
            int finishDay = day + task[day];

            if (finishDay > n + 1) {
                dp[day] = dp[day + 1];
            } else {
                dp[day] = Math.max(dp[day + 1], profit[day] + dp[finishDay]);
            }
        }

        System.out.println(dp[1]);
    }
}
