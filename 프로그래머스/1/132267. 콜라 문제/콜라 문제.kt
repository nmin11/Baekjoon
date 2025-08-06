class Solution {
    fun solution(a: Int, b: Int, n: Int): Int {
        var answer = 0
        var cur = n
        
        while (a <= cur) {
            val exchange = cur / a
            answer += exchange * b
            cur = (cur % a) + (exchange * b)
        }
        
        return answer
    }
}