import java.util.Map;
import java.util.HashMap;

class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> score = new HashMap<>();
        score.put('R', 0);
        score.put('T', 0);
        score.put('C', 0);
        score.put('F', 0);
        score.put('J', 0);
        score.put('M', 0);
        score.put('A', 0);
        score.put('N', 0);
        
        for (int i = 0; i < survey.length; i++) {
            if (choices[i] == 4) continue;
            
            int point = Math.abs(choices[i] - 4);
            if (choices[i] < 4) {
                char c = survey[i].charAt(0);
                score.put(c, score.get(c) + point);
            } else if (choices[i] > 4) {
                char c = survey[i].charAt(1);
                score.put(c, score.get(c) + point);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if (score.get('R') >= score.get('T')) {
            sb.append('R');
        } else {
            sb.append('T');
        }
        
        if (score.get('C') >= score.get('F')) {
            sb.append('C');
        } else {
            sb.append('F');
        }
        
        if (score.get('J') >= score.get('M')) {
            sb.append('J');
        } else {
            sb.append('M');
        }
        
        if (score.get('A') >= score.get('N')) {
            sb.append('A');
        } else {
            sb.append('N');
        }
        
        return sb.toString();
    }
}