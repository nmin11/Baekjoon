class Solution {
    companion object {
        private const val YES = "Yes"
        private const val NO = "No"
    }
    
    fun solution(cards1: Array<String>, cards2: Array<String>, goal: Array<String>): String {
        var pointer1 = 0
        var pointer2 = 0
        
        for (word in goal) {
            if (pointer1 < cards1.size && cards1[pointer1] == word) {
                pointer1++
            } else if (pointer2 < cards2.size && cards2[pointer2] == word) {
                pointer2++
            } else {
                return NO
            }
        }
        
        return YES
    }
}