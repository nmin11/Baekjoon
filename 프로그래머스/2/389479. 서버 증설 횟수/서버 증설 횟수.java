class Solution {
    public int solution(int[] players, int m, int k) {
        int[] added = new int[k];
        int serverCount = 0;
        int expendCount = 0;
        
        for (int i = 0; i < players.length; i++) {
            serverCount -= added[i % k];
            added[i % k] = 0;

            int needed = players[i] / m;
            int toAdd = needed - serverCount;
            
            if (toAdd > 0) {
                added[i % k] = toAdd;
                serverCount += toAdd;
                expendCount += toAdd;
            }
        }
        
        return expendCount;
    }
}