import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static boolean canFit(int[] lessons, int size, int maxCount) {
        int curSum = 0;
        int blueRayCount = 1;

        for (int lesson : lessons) {
            if (curSum + lesson > size) {
                blueRayCount++;
                curSum = lesson;

                if (blueRayCount > maxCount) return false;
            } else {
                curSum += lesson;
            }
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] lessons = new int[n];
        int maxLesson = 0;
        int totalLength = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            lessons[i] = Integer.parseInt(st.nextToken());
            maxLesson = Math.max(maxLesson, lessons[i]);
            totalLength += lessons[i];
        }

        int left = maxLesson;
        int right = totalLength;
        int answer = totalLength;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canFit(lessons, mid, m)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
