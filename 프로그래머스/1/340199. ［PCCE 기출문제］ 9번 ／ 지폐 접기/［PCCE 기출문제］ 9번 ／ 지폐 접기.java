class Solution {
    private boolean checkValid(int[] wallet, int[] bill) {
        if (bill[0] <= wallet[0] && bill[1] <= wallet[1]) {
            return true;
        } else if (bill[0] <= wallet[1] && bill[1] <= wallet[0]) {
            return true;
        }
        
        return false;
    }
    
    private void foldBill(int[] bill) {
        if (bill[0] > bill[1]) {
            bill[0] /= 2;
        } else {
            bill[1] /= 2;
        }
    }
    
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        
        while (true) {
            if (checkValid(wallet, bill)) {
                break;
            }
            
            foldBill(bill);
            answer++;
        }
        
        return answer;
    }
}