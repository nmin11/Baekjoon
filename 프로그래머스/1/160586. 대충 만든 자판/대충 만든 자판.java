import java.util.*;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> keyCount = new HashMap<>();
        
        for (String key : keymap) {
            for (int i = 0; i < key.length(); i++) {
                char c = key.charAt(i);
                if (!keyCount.containsKey(c) || i < keyCount.get(c)) {
                    keyCount.put(c, i + 1);
                }
            }
        }
        
        int len = targets.length;
        int[] answer = new int[len];
        
        for (int i = 0; i < len; i++) {
            int subtotal = 0;
            boolean canType = true;
            
            for (char c : targets[i].toCharArray()) {
                if (!keyCount.containsKey(c)) {
                    canType = false;
                    break;
                }
                
                subtotal += keyCount.get(c);
            }
            
            answer[i] = canType ? subtotal : -1;
        }
        
        return answer;
    }
}