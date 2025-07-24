class Solution {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        val idxMap = mutableMapOf<String,Int>()
        for (i in players.indices) {
            idxMap[players[i]] = i
        }
        
        for (calling in callings) {
            val idx = idxMap[calling]!!
            val tmp = players[idx - 1]
            players[idx - 1] = players[idx]
            players[idx] = tmp
            
            idxMap[players[idx]] = idx
            idxMap[players[idx - 1]] = idx - 1
        }
        
        return players
    }
}