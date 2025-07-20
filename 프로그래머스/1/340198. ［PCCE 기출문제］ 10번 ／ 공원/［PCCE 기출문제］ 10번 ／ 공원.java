import java.util.*;

class Solution {
    private static final String NOT_OWNED = "-1";
    private static final int CANNOT_PLACE = -1;
    
    private boolean canPlace(int row, int col, int mat, String[][] park) {
        for (int i = 0; i < mat; i++) {
            for (int j = 0; j < mat; j++) {
                if (!park[row + i][col + j].equals(NOT_OWNED)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public int solution(int[] mats, String[][] park) {
        int length = park.length;
        int width = park[0].length;
        
        Arrays.sort(mats);
        for (int i = 0; i < mats.length / 2; i++) {
            int tmp = mats[i];
            mats[i] = mats[mats.length - 1 - i];
            mats[mats.length - 1 - i] = tmp;
        }
        
        for (int mat : mats) {
            for (int i = 0; i + mat <= length; i++) {
                for (int j = 0; j + mat <= width; j++) {
                    if (canPlace(i, j, mat, park)) {
                        return mat;
                    }
                }
            }
        }
        
        return CANNOT_PLACE;
    }
}