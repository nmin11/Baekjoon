class Solution {
    fun solution(s: String): IntArray {
        val charMap = mutableMapOf<Char, Int>()
        var answer = IntArray(s.length) { -1 }
        
        for (i in s.indices) {
            val c = s[i]
            val preIdx = charMap.getOrDefault(c, -1)
            
            if (preIdx != -1) {
                answer[i] = i - preIdx
            }
            
            charMap[c] = i
        }
        
        return answer
    }
}