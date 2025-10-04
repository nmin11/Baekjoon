import java.io.*;

public class Main {
    static int[] nums;

    private static void radixSort(int[] nums) {
        int[] result = new int[nums.length];
        int digit = 1;

        for (int i = 0; i < 5; i++) {
            int[] queue = new int[10];

            for (int num : nums) {
                queue[(num / digit) % 10]++;
            }

            for (int j = 1; j < 10; j++) {
                queue[j] += queue[j - 1];
            }

            for (int j = nums.length - 1; j >= 0; j--) {
                result[queue[nums[j] / digit % 10] - 1] = nums[j];
                queue[nums[j] / digit % 10]--;
            }

            for (int j = 0; j < nums.length; j++) {
                nums[j] = result[j];
            }

            digit *= 10;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        radixSort(nums);

        for (int i = 0; i < n; i++) {
            bw.write(nums[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
}