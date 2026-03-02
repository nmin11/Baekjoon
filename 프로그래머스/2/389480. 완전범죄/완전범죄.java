import java.util.Arrays;

class Solution {
    private final int INF = Integer.MAX_VALUE / 2;
    
    public int solution(int[][] info, int n, int m) {
        int[] dp = new int[n];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        
        for (int[] item : info) {
            int aTrace = item[0];
            int bTrace = item[1];
            int[] next = new int[n];
            Arrays.fill(next, INF);
            
            for (int a = 0; a < n; a++) {
                if (dp[a] == INF) continue;
                
                if (a + aTrace < n) {
                    next[a + aTrace] = Math.min(next[a + aTrace], dp[a]);
                }
                
                if (dp[a] + bTrace < m) {
                    next[a] = Math.min(next[a], dp[a] + bTrace);
                }
            }
            
            dp = next;
        }
        
        for (int a = 0; a < n; a++) {
            if (dp[a] != INF) return a;
        }
        
        return -1;
    }
}