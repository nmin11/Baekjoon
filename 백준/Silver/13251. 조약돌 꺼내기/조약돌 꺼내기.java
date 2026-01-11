import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        int[] count = new int[m];
        int total = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            count[i] = Integer.parseInt(st.nextToken());
            total += count[i];
        }

        int k = Integer.parseInt(br.readLine());

        double answer = 0.0;
        for (int i = 0; i < m; i++) {
            if (count[i] >= k) {
                double probability = 1.0;

                for (int j = 0; j < k; j++) {
                    probability *= (double) (count[i] - j) / (total - j);
                }

                answer += probability;
            }
        }

        System.out.println(answer);
    }
}
