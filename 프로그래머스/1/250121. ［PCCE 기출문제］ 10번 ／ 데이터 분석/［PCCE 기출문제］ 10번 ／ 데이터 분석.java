import java.util.*;

class Solution {
    private int toIdx(String target) {
        switch (target) {
            case "code":
                return 0;
            case "date":
                return 1;
            case "maximum":
                return 2;
            case "remain":
                return 3;
            default:
                return -1;
        }
    }
    
    private int[][] filterAndSort(int[][] arr, int threshold, int filterIdx, int sortIdx) {
        return Arrays.stream(arr)
            .filter(row -> row[filterIdx] < threshold)
            .sorted(Comparator.comparingInt(row -> row[sortIdx]))
            .toArray(int[][]::new);
    }
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        int filterIdx = toIdx(ext);
        int sortIdx = toIdx(sort_by);

        return filterAndSort(data, val_ext, filterIdx, sortIdx);
    }
}