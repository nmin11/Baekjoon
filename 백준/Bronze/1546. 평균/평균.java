import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxScore = 0;
        int sum = 0;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
            maxScore = Math.max(maxScore, nums[i]);
            sum += nums[i];
        }

        System.out.println(sum * 100.0 / maxScore / n);
    }
}