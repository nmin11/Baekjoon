import java.io.BufferedReader
import java.io.InputStreamReader

private val unreachable = Long.MIN_VALUE
private val infiniteProfit = Long.MAX_VALUE

data class Edge(val from: Int, val to: Int, val cost: Int)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, start, end, m) = br.readLine().split(" ").map { it.toInt() }
    val edges = List(m) {
        val (from, to, cost) = br.readLine().split(" ").map { it.toInt() }
        Edge(from, to, cost)
    }
    val cityProfit = br.readLine().split(" ").map { it.toLong() }.toLongArray()

    val maxProfit = LongArray(n) { unreachable }
    maxProfit[start] = cityProfit[start]

    repeat(n - 1) {
        for (edge in edges) {
            if (maxProfit[edge.from] == unreachable) continue

            val newProfit = maxProfit[edge.from] + cityProfit[edge.to] - edge.cost
            if (maxProfit[edge.to] < newProfit) {
                maxProfit[edge.to] = newProfit
            }
        }
    }

    repeat(100) {
        for (edge in edges) {
            if (maxProfit[edge.from] == unreachable) continue

            if (maxProfit[edge.from] == infiniteProfit) {
                maxProfit[edge.to] = infiniteProfit
            } else if (maxProfit[edge.to] < maxProfit[edge.from] + cityProfit[edge.to] - edge.cost) {
                maxProfit[edge.to] = infiniteProfit
            }
        }
    }

    when (maxProfit[end]) {
        unreachable -> println("gg")
        infiniteProfit -> println("Gee")
        else -> println(maxProfit[end])
    }
}
