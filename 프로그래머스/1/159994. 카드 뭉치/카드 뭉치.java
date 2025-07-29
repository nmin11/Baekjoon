class Solution {
    static final String YES = "Yes";
    static final String NO = "No";
    
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        int pointer1 = 0;
        int pointer2 = 0;
        
        for (String word : goal) {
            if (cards1[pointer1].equals(word)) {
                pointer1++;
            } else if (cards2[pointer2].equals(word)) {
                pointer2++;
            } else {
                return NO;
            }
        }
        
        return YES;
    }
}