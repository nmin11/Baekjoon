import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = sc.next();
        char[] sChar = s.toCharArray();
        int sum = 0;
        
        for (char c : sChar) {
            sum += c - '0';
        }
        
        System.out.println(sum);
    }
}