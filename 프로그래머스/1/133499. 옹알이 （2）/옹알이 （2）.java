class Solution {
    public int solution(String[] babbling) {
        int answer = 0;

        for (String word : babbling) {
            word = word
                .replace("aya", "1")
                .replace("ye", "2")
                .replace("woo", "3")
                .replace("ma", "4");
            
            if (word.contains("11") ||
                word.contains("22") ||
                word.contains("33") ||
                word.contains("44")) {
                continue;
            }
            
            if (word.matches("[1234]+")) {
                answer++;
            }
        }

        return answer;
    }
}