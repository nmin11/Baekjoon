import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] peoples = new int[n];
        int[] sums = new int[n];

        for (int i = 0; i < n; i++) {
            peoples[i] = sc.nextInt();
        }

        /* Selection Sort */
        for (int i = 1; i < n; i++) {
            int pointer = i;
            int value = peoples[i];
            for (int j = i - 1; j >= 0; j--) {
                if (peoples[j] < peoples[i]) {
                    pointer = j + 1;
                    break;
                }

                if (j == 0) pointer = 0;
            }

            for (int j = i; j > pointer; j--) {
                peoples[j] = peoples[j - 1];
            }

            peoples[pointer] = value;
        }

        sums[0] = peoples[0];
        for (int i = 1; i < n; i++) {
            sums[i] = sums[i - 1] + peoples[i];
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += sums[i];
        }

        System.out.println(sum);
    }
}