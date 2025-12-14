import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

private val inf = Int.MAX_VALUE

data class Edge(val to: Int, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int = weight.compareTo(other.weight)
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (v, e) = br.readLine().split(" ").map { it.toInt() }
    val start = br.readLine().toInt()

    val graph = List(v + 1) { mutableListOf<Edge>() }
    repeat(e) {
        val (from, to, weight) = br.readLine().split(" ").map { it.toInt() }
        graph[from].add(Edge(to, weight))
    }

    val distance = IntArray(v + 1) { inf }
    distance[start] = 0

    val pq = PriorityQueue<Edge>()
    pq.offer(Edge(start, 0))
    while (pq.isNotEmpty()) {
        val (curNode, curDist) = pq.poll()

        if (curDist > distance[curNode]) continue

        for (edge in graph[curNode]) {
            val nextNode = edge.to
            val newDist = distance[curNode] + edge.weight

            if (newDist < distance[nextNode]) {
                distance[nextNode] = newDist
                pq.offer(Edge(nextNode, newDist))
            }
        }
    }

    val sb = StringBuilder()
    for (i in 1..v) {
        if (distance[i] == inf) {
            sb.append("INF\n")
        } else {
            sb.append(distance[i]).append('\n')
        }
    }

    print(sb)
}
