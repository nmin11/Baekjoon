import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

private lateinit var parent: IntArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val pq = PriorityQueue<Edge>()
    var totalLength = 0
    repeat(n) { i ->
        val line = br.readLine()
        line.forEachIndexed { j, c ->
            val length = charToLength(c)
            totalLength += length

            if (i != j && length > 0) {
                pq.offer(Edge(i, j, length))
            }
        }
    }

    parent = IntArray(n) { it }

    var mstCost = 0
    var edgeCount = 0
    while (pq.isNotEmpty()) {
        val edge = pq.poll()
        if (find(edge.from) != find(edge.to)) {
            union(edge.from, edge.to)
            mstCost += edge.length
            edgeCount++
        }
    }

    if (edgeCount == n - 1) {
        println(totalLength - mstCost)
    } else {
        println(-1)
    }
}

private fun charToLength(c: Char): Int {
    return when (c) {
        in 'a'..'z' -> c - 'a' + 1
        in 'A'..'Z' -> c - 'A' + 27
        else -> 0
    }
}

private fun find(x: Int): Int {
    if (parent[x] == x) return x
    parent[x] = find(parent[x])
    return parent[x]
}

private fun union(x: Int, y: Int) {
    val rootX = find(x)
    val rootY = find(y)

    if (rootX != rootY) {
        parent[rootY] = rootX
    }
}

private data class Edge(val from: Int, val to: Int, val length: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge) = length.compareTo(other.length)
}
