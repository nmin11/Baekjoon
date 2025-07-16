class Solution {
    fun solution(n: Int, w: Int, num: Int): Int {
        var depth = 0
        var currentNum = num
        var numRow = if (num % w == 0) (num / w - 1) else (num / w)
        val originDirection = numRow % 2
        val base = ((num + w - 1) / w) * w
        val step = (base - num) * 2 + 1

        while (currentNum <= n) {
            depth++

            currentNum += if (numRow % 2 == originDirection) {
                step
            } else {
                (w * 2) - step
            }

            numRow++
        }

        return depth
    }
}