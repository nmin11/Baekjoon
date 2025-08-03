class Solution {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        score.sort()
        score.reverse()

        var answer = 0
        
        for (i in m - 1 until score.size step m) {
            answer += score[i] * m
        }
        
        return answer
    }
}