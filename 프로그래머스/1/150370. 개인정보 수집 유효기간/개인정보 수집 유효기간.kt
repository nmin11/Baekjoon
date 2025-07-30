class Solution {
    private fun plusMonth(targetDate: String, monthsNum: Int): String {
        val (yearStr, monthStr, dayStr) = targetDate.split(".")
        var year = yearStr.toInt()
        var month = monthStr.toInt()
        var day = dayStr.toInt()
        
        month += monthsNum
        year += (month - 1) / 12
        month = (month - 1) % 12 + 1
        
        return "%04d.%02d.%02d".format(year, month, day)
    }
    
    private fun checkExpired(today: String, expiredDate: String): Boolean {
        return toComparableDate(today) >= toComparableDate(expiredDate)
    }
    
    private fun toComparableDate(dateStr: String): Int {
        return dateStr.replace(".", "").toInt()
    }
    
    fun solution(today: String, terms: Array<String>, privacies: Array<String>): IntArray {
        val termMap = mutableMapOf<String, Int>()
        for (term in terms) {
            val (key, value) = term.split(" ")
            termMap[key] = value.toInt()
        }
        
        val targets = mutableListOf<Int>()
        for ((idx, privacy) in privacies.withIndex()) {
            val (collectedDate, termType) = privacy.split(" ")
            val expiredDate = plusMonth(collectedDate, termMap[termType]!!)
            
            if (checkExpired(today, expiredDate)) {
                targets.add(idx + 1)
            }
        }
        
        return targets.toIntArray()
    }
}