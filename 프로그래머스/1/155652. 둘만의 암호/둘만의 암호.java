class Solution {
    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();
        
        for (char c : s.toCharArray()) {
            int cnt = 0;
            
            while (cnt < index) {
                c = (c == 'z') ? 'a' : (char)(c + 1);
                if (skip.contains(String.valueOf(c))) continue;
                cnt++;
            }
            
            sb.append(c);
        }
        
        return sb.toString();
    }
}