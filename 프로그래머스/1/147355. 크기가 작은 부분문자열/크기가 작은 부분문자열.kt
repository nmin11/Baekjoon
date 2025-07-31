class Solution {
    fun solution(t: String, p: String): Int {
        val tLen = t.length
        val pLen = p.length
        val pNum = p.toLong()
        var answer: Int = 0
        
        for (i in 0 .. tLen - pLen) {
            val subNum = t.substring(i, i + pLen).toLong()
            if (subNum <= pNum) answer++
        }
        
        return answer
    }
}