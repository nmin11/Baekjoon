class Solution {
    public int[] solution(String[] wallpaper) {
        int lux = wallpaper.length - 1;
        int luy = wallpaper[0].length() - 1;
        int rdx = 0;
        int rdy = 0;
        
        for (int i = 0; i < wallpaper.length; i++) {
            for (int j = 0; j < wallpaper[i].length(); j++) {
                if (wallpaper[i].charAt(j) == '#') {
                    lux = Math.min(lux, i);
                    luy = Math.min(luy, j);
                    rdx = Math.max(rdx, i + 1);
                    rdy = Math.max(rdy, j + 1);
                }
            }
        }
        
        int[] answer = {lux, luy, rdx, rdy};
        return answer;
    }
}