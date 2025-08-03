import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        score = Arrays.stream(score)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();
        
        int pointer = 0;
        int answer = 0;
        
        while(pointer + m <= score.length) {
            for (int i = pointer; i < pointer + m; i++) {
                if (score[i] < k) {
                    k = score[i];
                }
            }
            
            answer += k * m;
            pointer += m;
        }
        
        return answer;
    }
}