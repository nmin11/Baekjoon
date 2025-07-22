import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length;
        
        Map<String, Integer> friendIdx = new HashMap<>();
        for (int i = 0; i < friends.length; i++) {
            friendIdx.put(friends[i], i);
        }

        int[][] giftMatrix = new int[n][n];
        for (String gift : gifts) {
            String[] parts = gift.split(" ");
            String sender = parts[0];
            String receiver = parts[1];
            int from = friendIdx.get(sender);
            int to = friendIdx.get(receiver);
            
            giftMatrix[from][to]++;
        }
        
        int[] giftScore = new int[n];
        for (int i = 0; i < n; i++) {
            int given = 0;
            int received = 0;
            
            for (int j = 0; j < n; j++) {
                given += giftMatrix[i][j];
                received += giftMatrix[j][i];
            }
            
            giftScore[i] = given - received;
        }
        
        int[] receiveCnt = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                
                int given = giftMatrix[i][j];
                int received = giftMatrix[j][i];
                
                if ((given > received) ||
                    (given == received && giftScore[i] > giftScore[j])) {
                    receiveCnt[i]++;
                }
            }
        }
        
        int answer = 0;
        for (int cnt : receiveCnt) {
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
}