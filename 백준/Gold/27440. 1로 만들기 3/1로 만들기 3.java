import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
    private static int minCompute(long n) {
        if (n == 1) return 0;

        Queue<long[]> queue = new ArrayDeque<>();
        Map<Long, Integer> visited = new HashMap<>();

        queue.offer(new long[]{n, 0});
        visited.put(n, 0);

        while (!queue.isEmpty()) {
            long[] cur = queue.poll();
            long num = cur[0];
            int cnt = (int) cur[1];

            if (num == 1) {
                return cnt;
            }

            if (visited.containsKey(num) && visited.get(num) < cnt) {
                continue;
            }

            if (num % 3 == 0) {
                long next = num / 3;
                if (!visited.containsKey(next) || visited.get(next) > cnt + 1) {
                    visited.put(next, cnt + 1);
                    queue.offer(new long[]{next, cnt + 1});
                }
            }

            if (num % 2 == 0) {
                long next = num / 2;
                if (!visited.containsKey(next) || visited.get(next) > cnt + 1) {
                    visited.put(next, cnt + 1);
                    queue.offer(new long[]{next, cnt + 1});
                }
            }

            long next = num - 1;
            if (!visited.containsKey(next) || visited.get(next) > cnt + 1) {
                visited.put(next, cnt + 1);
                queue.offer(new long[]{next, cnt + 1});
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        System.out.println(minCompute(n));
    }
}
