class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while (a <= n) {
            int exchange = n / a;
            answer += exchange * b;
            n = (n % a) + (exchange * b);
        }
        
        return answer;
    }
}