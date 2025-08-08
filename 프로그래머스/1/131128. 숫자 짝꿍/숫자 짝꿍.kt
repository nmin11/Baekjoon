class Solution {
    fun solution(X: String, Y: String): String {
        val cx = IntArray(10)
        val cy = IntArray(10)
        
        for (c in X) cx[c - '0']++
        for (c in Y) cy[c - '0']++
        
        val sb = StringBuilder()
        for (n in 9 downTo 0) {
            val cnt = minOf(cx[n], cy[n])
            repeat(cnt) { sb.append(n) }
        }
        
        if (sb.isEmpty()) return "-1"
        if (sb[0] == '0') return "0"
        
        return sb.toString()
    }
}