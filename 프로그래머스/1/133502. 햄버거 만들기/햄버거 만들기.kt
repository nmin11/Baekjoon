class Solution {
    fun solution(ingredient: IntArray): Int {
        val stack = mutableListOf<Int>()
        var answer = 0
        
        for (i in ingredient) {
            stack.add(i)
            if (stack.size >= 4 &&
                stack[stack.size - 4] == 1 &&
                stack[stack.size - 3] == 2 &&
                stack[stack.size - 2] == 3 &&
                stack[stack.size - 1] == 1) {
                repeat(4) { stack.removeAt(stack.size - 1) }
                answer++
            }
        }
        
        return answer
    }
}