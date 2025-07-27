class Solution {
    fun solution(n: Int, m: Int, section: IntArray): Int {
        var painted = 0
        var answer = 0
        
        for (wall in section) {
            if (painted < wall) {
                painted = wall + m - 1
                answer++
            }
        }
        
        return answer
    }
}