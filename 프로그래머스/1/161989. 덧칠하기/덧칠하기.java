class Solution {
    public int solution(int n, int m, int[] section) {
        int painted = 0;
        int answer = 0;
        
        for (int wall : section) {
            if (painted < wall) {
                painted = wall + m - 1;
                answer++;
            }
        }
        
        return answer;
    }
}