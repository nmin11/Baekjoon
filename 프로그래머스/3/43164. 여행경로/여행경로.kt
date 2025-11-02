import java.util.PriorityQueue

class Solution {
    fun solution(tickets: Array<Array<String>>): Array<String> {
        val results = mutableListOf<String>()
        val fromToMap = mutableMapOf<String, PriorityQueue<String>>()
        
        tickets.forEach { (from, to) ->
            fromToMap.getOrPut(from) { PriorityQueue() }.add(to)
        }
        
        fun dfs(from: String) {
            val destinations = fromToMap[from]
            while (destinations != null && destinations.isNotEmpty()) {
                dfs(destinations.poll())
            }
            results.add(0, from)
        }
        
        dfs("ICN")
        
        return results.toTypedArray()
    }
}