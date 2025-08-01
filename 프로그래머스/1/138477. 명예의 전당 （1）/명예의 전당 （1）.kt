import java.util.*;

class Solution {
    fun solution(k: Int, score: IntArray): IntArray {
        val minHeap = PriorityQueue<Int>()
        val answer = IntArray(score.size)
        
        for (i in score.indices) {
            minHeap.offer(score[i])
            if (minHeap.size > k) {
                minHeap.poll()
            }
            answer[i] = minHeap.peek()
        }
        
        return answer
    }
}