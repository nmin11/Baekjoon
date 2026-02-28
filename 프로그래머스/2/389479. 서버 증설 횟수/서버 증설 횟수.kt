class Solution {
    fun solution(players: IntArray, m: Int, k: Int): Int {
        val added = IntArray(k)
        var serverCount = 0
        var expendCount = 0
        
        for (i in players.indices) {
            serverCount -= added[i % k]
            added[i % k] = 0
            
            val needed = players[i] / m
            val toAdd = needed - serverCount
            
            if (toAdd > 0) {
                added[i % k] = toAdd
                serverCount += toAdd
                expendCount += toAdd
            }
        }
        
        return expendCount
    }
}