class Solution {
    fun solution(number: IntArray): Int =
        number.indices.sumOf { i ->
            (i + 1 until number.size).sumOf { j ->
                (j + 1 until number.size).count { k ->
                    number[i] + number[j] + number[k] == 0
                }
            }
        }
}