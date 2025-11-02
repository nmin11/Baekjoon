import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    private void dfs(
        List<String> results,
        Map<String, PriorityQueue<String>> fromToMap,
        String from
    ) {
        while (fromToMap.containsKey(from) && !fromToMap.get(from).isEmpty()) {
            dfs(results, fromToMap, fromToMap.get(from).poll());
        }
        
        results.add(0, from);
    }
    
    public String[] solution(String[][] tickets) {
        List<String> results = new LinkedList<>();
        Map<String, PriorityQueue<String>> fromToMap = new HashMap<>();
        
        for (String[] ticket : tickets) {
            fromToMap.putIfAbsent(ticket[0], new PriorityQueue<>());
            fromToMap.get(ticket[0]).add(ticket[1]);
        }
        
        dfs(results, fromToMap, "ICN");
        
        return results.toArray(new String[0]);
    }
}