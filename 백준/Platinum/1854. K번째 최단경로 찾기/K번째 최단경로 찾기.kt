import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

data class Edge(val to: Int, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = weight.compareTo(other.weight)
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }

    val graph = List(n + 1) { mutableListOf<Edge>() }
    repeat(m) {
        val (from, to, weight) = br.readLine().split(" ").map { it.toInt() }
        graph[from].add(Edge(to, weight))
    }

    val distQueue = List(n + 1) { PriorityQueue<Int>(reverseOrder()) }
    val pq = PriorityQueue<Edge>()
    pq.offer(Edge(1, 0))
    distQueue[1].offer(0)
    while (pq.isNotEmpty()) {
        val (curNode, curWeight) = pq.poll()
        for (edge in graph[curNode]) {
            val nextNode = edge.to
            val newWeight = curWeight + edge.weight
            val nextQueue = distQueue[nextNode]

            if (nextQueue.size < k) {
                nextQueue.offer(newWeight)
                pq.offer(Edge(nextNode, newWeight))
            } else if (nextQueue.peek() > newWeight) {
                nextQueue.poll()
                nextQueue.offer(newWeight)
                pq.offer(Edge(nextNode, newWeight))
            }
        }
    }

    val sb = StringBuilder()
    for (i in 1..n) {
        val queue = distQueue[i]
        if (queue.size == k) {
            sb.append(queue.peek()).append('\n')
        } else {
            sb.append(-1).append('\n')
        }
    }

    print(sb)
}
