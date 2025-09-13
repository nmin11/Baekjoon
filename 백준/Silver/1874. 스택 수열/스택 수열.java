import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        
        Stack<Integer> stack = new Stack<>();
        int num = 1;
        StringBuffer bf = new StringBuffer();
        boolean isNoPrinted = false;
        
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            
            if (cur >= num) {
                while (cur >= num) {
                    stack.push(num++);
                    bf.append("+\n");
                }
                
                stack.pop();
                bf.append("-\n");
            } else {
                int pop = stack.pop();
                if (pop > cur) {
                    System.out.println("NO");
                    isNoPrinted = true;
                    break;
                } else {
                    bf.append("-\n");
                }
            }
        }
        
        if (!isNoPrinted) {
            System.out.println(bf.toString());
        }
    }
}