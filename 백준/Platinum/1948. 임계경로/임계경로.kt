import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

data class Edge(val to: Int, val time: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val indegree = IntArray(n + 1)

    val graph = List(n + 1) { mutableListOf<Edge>() }
    val reverseGraph = List(n + 1) { mutableListOf<Edge>() }

    repeat(m) {
        val (from, to, time) = br.readLine().split(" ").map { it.toInt() }
        graph[from].add(Edge(to, time))
        reverseGraph[to].add(Edge(from, time))
        indegree[to]++
    }

    val (start, end) = br.readLine().split(" ").map { it.toInt() }
    val queue: Queue<Int> = LinkedList()
    val maxTime = IntArray(n + 1)
    queue.offer(start)

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (edge in graph[cur]) {
            indegree[edge.to]--
            maxTime[edge.to] = maxOf(maxTime[edge.to], maxTime[cur] + edge.time)

            if (indegree[edge.to] == 0) queue.offer(edge.to)
        }
    }

    var criticalEdgeCount = 0
    val visited = BooleanArray(n + 1)
    queue.offer(end)
    visited[end] = true

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (edge in reverseGraph[cur]) {
            if (maxTime[edge.to] + edge.time == maxTime[cur]) {
                criticalEdgeCount++

                if (!visited[edge.to]) {
                    visited[edge.to] = true
                    queue.offer(edge.to)
                }
            }
        }
    }

    println(maxTime[end])
    println(criticalEdgeCount)
}
