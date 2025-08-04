class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 1; i < food.length; i++) {
            int shared = food[i] / 2;
            if (shared > 0) sb.append(String.valueOf(i).repeat(shared));
        }
        
        sb.append(0);
        sb.append(new StringBuilder(sb.substring(0, sb.length() - 1)).reverse());
        
        return sb.toString();
    }
}