import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static class Pair implements Comparable<Pair> {
        int value;
        int index;

        public Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] a = new Pair[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Pair(Integer.parseInt(br.readLine()), i);
        }

        Arrays.sort(a);

        int maxMove = 0;
        for (int i = 0; i < n; i++) {
            if (maxMove < a[i].index - i) maxMove = a[i].index - i;
        }

        System.out.println(maxMove + 1);
    }
}