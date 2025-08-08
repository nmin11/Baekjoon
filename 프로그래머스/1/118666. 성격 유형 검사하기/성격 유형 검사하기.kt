class Solution {
    fun solution(survey: Array<String>, choices: IntArray): String {
        val score = IntArray(26)
        val points = intArrayOf(0, 3, 2, 1, 0, 1, 2, 3)
        
        for (i in survey.indices) {
            val left = survey[i][0]
            val right = survey[i][1]
            val choice = choices[i]
            when {
                choice < 4 -> score[left.code - 'A'.code] += points[choice]
                choice > 4 -> score[right.code - 'A'.code] += points[choice]
                else -> Unit
            }
        }
        
        val pairs = arrayOf('R' to 'T', 'C' to 'F', 'J' to 'M', 'A' to 'N')
        val sb = StringBuilder()
        for ((a, b) in pairs) {
            val scoreA = score[a.code - 'A'.code]
            val scoreB = score[b.code - 'A'.code]
            sb.append(if (scoreA >= scoreB) a else b)
        }
        
        return sb.toString()
    }
}