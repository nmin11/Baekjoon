import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

private val inf = Int.MAX_VALUE

data class Edge(val to: Int, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = weight.compareTo(other.weight)
}

fun dijkstra(graph: List<List<Edge>>, n: Int, start: Int, end: Int): Int {
    val distance = IntArray(n + 1) { inf }
    distance[start] = 0

    val pq = PriorityQueue<Edge>()
    pq.offer(Edge(start, 0))
    while (pq.isNotEmpty()) {
        val (curNode, curCost) = pq.poll()

        if (curCost > distance[curNode]) continue

        if (curNode == end) break

        for (edge in graph[curNode]) {
            val nextNode = edge.to
            val newCost = distance[curNode] + edge.weight

            if (newCost < distance[nextNode]) {
                distance[nextNode] = newCost
                pq.offer(Edge(nextNode, newCost))
            }
        }
    }

    return distance[end]
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    val graph = List(n + 1) { mutableListOf<Edge>() }
    repeat(m) {
        val (from, to, cost) = br.readLine().split(" ").map { it.toInt() }
        graph[from].add(Edge(to, cost))
    }

    val (start, end) = br.readLine().split(" ").map { it.toInt() }

    val result = dijkstra(graph, n, start, end)
    println(result)
}
