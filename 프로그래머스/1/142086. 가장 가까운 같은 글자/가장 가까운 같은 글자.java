import java.util.*;

class Solution {
    public int[] solution(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        int len = s.length();
        int[] answer = new int[len];
        Arrays.fill(answer, -1);
        
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (charMap.containsKey(c)) {
                answer[i] = i - charMap.get(c);
            }
            charMap.put(c, i);
        }
        
        return answer;
    }
}