import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if (n == 1) {
            System.out.println(1);
            return;
        }

        long prevZero = 0;
        long prevOne = 1;

        for (int i = 2; i <= n; i++) {
            long curZero = prevZero + prevOne;
            long curOne = prevZero;

            prevZero = curZero;
            prevOne = curOne;
        }

        System.out.println(prevZero + prevOne);
    }
}
