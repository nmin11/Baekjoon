class Solution {
    fun solution(friends: Array<String>, gifts: Array<String>): Int {
        val n = friends.size
        
        val friendIdx = friends.withIndex().associate { it.value to it.index }
        
        val giftMatrix = Array(n) { IntArray(n) }
        for (gift in gifts) {
            val (sender, receiver) = gift.split(" ")
            val from = friendIdx[sender]!!
            val to = friendIdx[receiver]!!
            
            giftMatrix[from][to]++
        }
        
        val giftScore = IntArray(n)
        for (i in 0 until n) {
            var given = 0
            var received = 0
            
            for (j in 0 until n) {
                given += giftMatrix[i][j]
                received += giftMatrix[j][i]
            }
            
            giftScore[i] = given - received
        }
        
        val receiveCnt = IntArray(n)
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (i == j) continue
                
                val given = giftMatrix[i][j]
                val received = giftMatrix[j][i]
                
                if ((given > received) ||
                    (given == received && giftScore[i] > giftScore[j])) {
                    receiveCnt[i]++
                }
            }
        }
        
        return receiveCnt.maxOrNull() ?: 0
    }
}