class Solution {
    public String solution(String X, String Y) {
        int[] cx = new int[10];
        int[] cy = new int[10];
        
        for (char c : X.toCharArray()) cx[c - '0']++;
        for (char c : Y.toCharArray()) cy[c - '0']++;
        
        StringBuilder sb = new StringBuilder();
        for (int n = 9; n >= 0; n--) {
            int target = Math.min(cx[n], cy[n]);
            while (target-- > 0) sb.append(n);
        }
        
        if (sb.length() == 0) return "-1";
        if (sb.charAt(0) == '0') return "0";
        
        return sb.toString();
    }
}