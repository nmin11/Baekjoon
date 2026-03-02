class Solution {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val INF = Int.MAX_VALUE / 2
        var dp = IntArray(n) { INF }.also { it[0] = 0 }
        
        for ((aTrace, bTrace) in info) {
            val next = IntArray(n) { INF }
            
            for (a in 0 until n) {
                if (dp[a] == INF) continue
                
                if (a + aTrace < n) {
                    next[a + aTrace] = minOf(next[a + aTrace], dp[a])
                }
                
                if (dp[a] + bTrace < m) {
                    next[a] = minOf(next[a], dp[a] + bTrace)
                }
            }
            
            dp = next
        }
        
        return (0 until n).firstOrNull { dp[it] != INF } ?: -1
    }
}