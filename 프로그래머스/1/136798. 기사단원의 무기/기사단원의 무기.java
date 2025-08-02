class Solution {
    private int countDivisors(int n) {
        int count = 0;
        
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                count++;
                if (i != n / i) {
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public int solution(int number, int limit, int power) {
        int answer = 0;
        
        for (int i = 1; i <= number; i++) {
            int iron = countDivisors(i);
            if (iron > limit) iron = power;
            answer += iron;
        }
        
        return answer;
    }
}