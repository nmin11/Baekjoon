import java.util.Map;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            idxMap.put(players[i], i);
        }
        
        for (String calling : callings) {
            int idx = idxMap.get(calling);
            String tmp = players[idx - 1];
            players[idx - 1] = players[idx];
            players[idx] = tmp;
            
            idxMap.put(players[idx], idx);
            idxMap.put(players[idx - 1], idx - 1);
        }
        
        return players;
    }
}