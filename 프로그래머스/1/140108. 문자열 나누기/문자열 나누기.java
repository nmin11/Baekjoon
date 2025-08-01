class Solution {
    public int solution(String s) {
        char c = s.charAt(0);
        int same = 0;
        int diff = 0;
        int answer = 0;
        
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) same++;
            else diff++;
            
            if (same == diff) {
                answer++;
                if (i + 1 < s.length()) {
                    c = s.charAt(i + 1);
                    same = 0;
                    diff = 0;
                }
            }
        }
        
        if (same != diff) answer++;

        return answer;
    }
}
