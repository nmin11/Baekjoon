import java.io.BufferedReader
import java.io.InputStreamReader

private val inf = Long.MAX_VALUE

data class Edge(val from: Int, val to: Int, val time: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val edges = List(m) {
        val (from, to, time) = br.readLine().split(" ").map { it.toInt() }
        Edge(from, to, time)
    }

    val distance = LongArray(n + 1) { inf }
    distance[1] = 0

    repeat(n - 1) {
        for (edge in edges) {
            if (distance[edge.from] != inf && distance[edge.to] > distance[edge.from] + edge.time) {
                distance[edge.to] = distance[edge.from] + edge.time
            }
        }
    }

    val hasNegativeCycle = edges.any { edge ->
        distance[edge.from] != inf && distance[edge.to] > distance[edge.from] + edge.time
    }

    val sb = StringBuilder()
    if (hasNegativeCycle) {
        sb.append(-1)
    } else {
        for (i in 2..n) {
            if (distance[i] == inf) {
                sb.append(-1).append('\n')
            } else {
                sb.append(distance[i]).append('\n')
            }
        }
    }

    print(sb)
}
