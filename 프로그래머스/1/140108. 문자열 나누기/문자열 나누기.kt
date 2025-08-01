class Solution {
    fun solution(s: String): Int {
        var same = 0
        var diff = 0
        var answer = 0
        var curChar = s[0]
        
        for ((i, c) in s.withIndex()) {
            if (c == curChar) same++
            else diff++
            
            if (same == diff) {
                answer++
                if (i + 1 < s.length) {
                    curChar = s[i + 1]
                    same = 0
                    diff = 0
                }
            }
        }
        
        if (same != diff) answer++
        
        return answer
    }
}