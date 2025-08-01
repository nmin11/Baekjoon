import java.util.*;

class Solution {
    public int[] solution(int k, int[] score) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int n = score.length;
        int[] answer = new int[n];
        
        for (int i = 0; i < n; i++) {
            minHeap.offer(score[i]);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
            answer[i] = minHeap.peek();
        }
        
        return answer;
    }
}