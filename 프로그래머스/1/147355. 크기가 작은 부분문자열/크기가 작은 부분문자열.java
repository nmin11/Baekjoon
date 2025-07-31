class Solution {
    public int solution(String t, String p) {
        int tLen = t.length();
        int pLen = p.length();
        long pNum = Long.parseLong(p);
        int answer = 0;
        
        for (int i = 0; i < tLen - pLen + 1; i++) {
            long subNum = Long.parseLong(t.substring(i, i + pLen));
            if (subNum <= pNum) answer++;
        }
        
        return answer;
    }
}