import java.util.*;

class Solution {
    private String plusMonth(String targetDate, int monthsNum) {
        String[] parts = targetDate.split("\\.");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        
        month += monthsNum;
        year += (month - 1) / 12;
        month = (month - 1) % 12 + 1;
        
        return String.format("%04d.%02d.%02d", year, month, day);
    }
    
    private boolean checkExpired(String today, String expiredDate) {
        return toCamparableDate(today) >= toCamparableDate(expiredDate);
    }
    
    private int toCamparableDate(String dateStr) {
        return Integer.parseInt(dateStr.replace(".", ""));
    }
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> termMap = new HashMap<>();
        for (String term : terms) {
            String[] parts = term.split(" ");
            termMap.put(parts[0], Integer.parseInt(parts[1]));
        }
        
        List<Integer> targets = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] parts = privacies[i].split(" ");
            String collectedDate = parts[0];
            String expiredDate = plusMonth(parts[0], termMap.get(parts[1]));
            
            if (checkExpired(today, expiredDate)) {
                targets.add(i + 1);
            }
        }
        
        return targets.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}