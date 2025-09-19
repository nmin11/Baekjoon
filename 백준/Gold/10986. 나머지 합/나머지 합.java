import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        long[] prefixSum = new long[N + 1];
        long[] count = new long[M];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());
            prefixSum[i] = (prefixSum[i-1] + num) % M;
            if (prefixSum[i] < 0) {
                prefixSum[i] += M;
            }
            count[(int)prefixSum[i]]++;
        }
        
        long result = 0;
        result += count[0];

        for (int i = 0; i < M; i++) {
            if (count[i] >= 2) {
                result += count[i] * (count[i] - 1) / 2;
            }
        }
        
        System.out.println(result);
    }
}