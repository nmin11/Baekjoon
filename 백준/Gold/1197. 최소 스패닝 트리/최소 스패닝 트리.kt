import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

private lateinit var parent: IntArray

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

data class Edge(val from: Int, val to: Int, val weight: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge) = weight.compareTo(other.weight)
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (v, e) = br.readLine().split(" ").map { it.toInt() }
    parent = IntArray(v + 1) { it }

    val pq = PriorityQueue<Edge>()
    repeat(e) {
        val (from, to, weight) = br.readLine().split(" ").map { it.toInt() }
        pq.offer(Edge(from, to, weight))
    }

    var totalCost = 0
    var edgeCount = 0
    while (edgeCount < v - 1) {
        val cur = pq.poll()
        if (find(cur.from) != find(cur.to)) {
            union(cur.from, cur.to)
            totalCost += cur.weight
            edgeCount++
        }
    }

    println(totalCost)
}
