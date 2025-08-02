class Solution {
    private fun countDivisors(n: Int): Int {
        var count = 0
        val sqrtN = Math.sqrt(n.toDouble()).toInt()
        
        for (i in 1..sqrtN) {
            if (n % i == 0) {
                count++
                if (i != n / i) {
                    count++
                }
            }
        }
        
        return count
    }
    
    fun solution(number: Int, limit: Int, power: Int): Int {
        var answer: Int = 0
        
        for (i in 1..number) {
            var iron = countDivisors(i)
            if (iron > limit) iron = power
            answer += iron
        }
        
        return answer
    }
}