import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long min = Long.parseLong(st.nextToken());
        long max = Long.parseLong(st.nextToken());

        boolean[] isSquare = new boolean[(int)(max - min + 1)];

        for (long i = 2; i * i <= max; i++) {
            long square = i * i;

            long start = min / square;
            if (min % square != 0) start++;

            for (long j = start; square * j <= max; j++) {
                isSquare[(int)(square * j - min)] = true;
            }
        }

        int count = 0;
        for (boolean b : isSquare) {
            if (!b) count++;
        }

        System.out.println(count);
    }
}
