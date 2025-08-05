class Solution {
    fun solution(babbling: Array<String>): Int {
        var answer = 0
        
        for (word in babbling) {
            var replaced = word
                .replace("aya", "1")
                .replace("ye", "2")
                .replace("woo", "3")
                .replace("ma", "4")
                
            if (replaced.contains("11") ||
                replaced.contains("22") ||
                replaced.contains("33") ||
                replaced.contains("44")) {
                continue
            }

            if (replaced.matches(Regex("[1234]+"))) answer++
        }
        
        return answer
    }
}