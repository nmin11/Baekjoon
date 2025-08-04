class Solution {
    fun solution(food: IntArray): String {
        val left = buildString {
            for (i in 1 until food.size) {
                repeat(food[i] / 2) { append(i) }
            }
        }
        
        return left + "0" + left.reversed()
    }
}