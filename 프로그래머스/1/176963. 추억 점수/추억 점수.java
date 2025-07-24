import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> yearnMap = new HashMap<>();
        for (int i = 0; i < name.length; i++) {
            yearnMap.put(name[i], yearning[i]);
        }
        
        return Arrays.stream(photo)
            .mapToInt(arr ->
                Arrays.stream(arr)
                    .mapToInt(person -> yearnMap.getOrDefault(person, 0))
                    .sum()
            ).toArray();
    }
}