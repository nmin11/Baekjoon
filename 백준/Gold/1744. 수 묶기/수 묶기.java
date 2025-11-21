import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> negative = new PriorityQueue<>();
        int ones = 0;
        int zeros = 0;

        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) positive.offer(num);
            else if (num == 1) ones++;
            else if (num == 0) zeros++;
            else negative.offer(num);
        }

        int sum = 0;

        while (positive.size() > 1) {
            sum += positive.poll() * positive.poll();
        }
        if (!positive.isEmpty()) sum += positive.poll();

        while (negative.size() > 1) {
            sum += negative.poll() * negative.poll();
        }
        if (!negative.isEmpty() && zeros == 0) sum += negative.poll();

        sum += ones;

        System.out.println(sum);
    }
}
