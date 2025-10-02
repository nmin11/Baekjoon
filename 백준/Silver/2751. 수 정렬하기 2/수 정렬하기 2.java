import java.io.*;

public class Main {
    static int[] nums, tmp;

    private static void mergeSort(int start, int end) {
        if (end - start < 1) return;

        int mid = start + (end - start) / 2;
        mergeSort(start, mid);
        mergeSort(mid + 1, end);

        for (int i = start; i <= end; i++) {
            tmp[i] = nums[i];
        }

        int k = start;
        int left = start;
        int right = mid + 1;

        while (left <= mid && right <= end) {
            if (tmp[left] > tmp[right]) {
                nums[k] = tmp[right];
                k++;
                right++;
            } else {
                nums[k] = tmp[left];
                k++;
                left++;
            }
        }

        while (left <= mid) {
            nums[k] = tmp[left];
            k++;
            left++;
        }

        while (right <= end) {
            nums[k] = tmp[right];
            k++;
            right++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        nums = new int[n + 1];
        tmp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        mergeSort(1, n);

        for (int i = 1; i <= n; i++) {
            bw.write(nums[i] + "\n");
        }
        bw.flush();
        bw.close();
    }
}