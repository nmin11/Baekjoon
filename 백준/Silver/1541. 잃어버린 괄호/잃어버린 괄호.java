import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int sum(String group) {
        int sum = 0;
        for (String num : group.split("\\+")) {
            sum += Integer.parseInt(num);
        }

        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] groups = br.readLine().split("-");

        int result = sum(groups[0]);
        for (int i = 1; i < groups.length; i++) {
            result -= sum(groups[i]);
        }

        System.out.println(result);
    }
}
