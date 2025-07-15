import java.util.Set;

class Solution {
    private boolean checkAttendance(int desireTime, int attendanceTime) {
        int desiredTotal = ((desireTime / 100) * 60) + (desireTime % 100);
        int attendanceTotal = ((attendanceTime / 100) * 60) + (attendanceTime % 100);
        
        return attendanceTotal - desiredTotal <= 10;
    }
    
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int satIdx = (12 - (startday - 1)) % 7;
        int sunIdx = (satIdx + 1) % 7;
        Set<Integer> weekendIdx = Set.of(satIdx, sunIdx);
        
        for (int i = 0; i < timelogs.length; i++) {
            boolean attendedAllWeekdays = true;
            for (int j = 0; j < timelogs[i].length; j++) {
                if (weekendIdx.contains(j)) continue;
                if (!checkAttendance(schedules[i], timelogs[i][j])) {
                    attendedAllWeekdays = false;
                    break;
                }
            }
            
            if (attendedAllWeekdays) answer++;
        }
        
        return answer;
    }
}