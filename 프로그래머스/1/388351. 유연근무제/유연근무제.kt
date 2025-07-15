class Solution {
    private fun checkAttendance(desireTime: Int, attendanceTime: Int): Boolean {
        val desiredTotal = (desireTime / 100) * 60 + (desireTime % 100)
        val attendanceTotal = (attendanceTime / 100) * 60 + (attendanceTime % 100)

        return attendanceTotal - desiredTotal <= 10
    }
    
    fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {
        var answer: Int = 0
        val satIdx = (12 - (startday - 1)) % 7
        val sunIdx = (satIdx + 1) % 7
        val weekendIdx = setOf(satIdx, sunIdx)
        
        for (i in timelogs.indices) {
            val attendedAllWeekdays = timelogs[i].indices
                .filter { it !in weekendIdx }
                .all { checkAttendance(schedules[i], timelogs[i][it]) }

            if (attendedAllWeekdays) answer++
        }
        
        return answer
    }
}