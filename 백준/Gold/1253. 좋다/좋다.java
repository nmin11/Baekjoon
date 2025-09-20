import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] nums = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(nums);
        int count = 0;

        for (int i = 0; i < n; i++) {
            long target = nums[i];
            int left = 0;
            int right = n - 1;

            while (left < right) {
                long sum = nums[left] + nums[right];

                if (sum == target) {
                    if (left == i) {
                        left++;
                    } else if (right == i) {
                        right--;
                    } else {
                        count++;
                        break;
                    }
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}