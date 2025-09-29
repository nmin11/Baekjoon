import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] a, tmp;

    private static void mergeSort(int start, int end) {
        if (end - start < 1) return;

        int mid = start + (end - start) / 2;

        mergeSort(start, mid);
        mergeSort(mid + 1, end);

        for (int i = start; i <= end; i++) {
            tmp[i] = a[i];
        }

        int k = start;
        int left = start;
        int right = mid + 1;
        while (left <= mid && right <= end) {
            if (tmp[left] > tmp[right]) {
                a[k] = tmp[right];
                k++;
                right++;
            } else {
                a[k] = tmp[left];
                k++;
                left++;
            }
        }

        while (left <= mid) {
            a[k] = tmp[left];
            k++;
            left++;
        }

        while (right <= end) {
            a[k] = tmp[right];
            k++;
            right++;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        a = new int[n + 1];
        tmp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(1, n);

        System.out.println(a[k]);
    }
}