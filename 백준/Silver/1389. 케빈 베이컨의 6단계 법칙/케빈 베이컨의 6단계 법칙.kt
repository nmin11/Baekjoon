import java.io.BufferedReader
import java.io.InputStreamReader

private val inf = 10_000_000

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val distance = Array(n + 1) { i ->
        IntArray(n + 1) { j ->
            when {
                i == j -> 0
                else -> inf
            }
        }
    }

    repeat(m) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        distance[a][b] = 1
        distance[b][a] = 1
    }

    for (k in 1..n) {
        for (i in 1..n) {
            for (j in 1..n) {
                if (distance[i][j] > distance[i][k] + distance[k][j]) {
                    distance[i][j] = distance[i][k] + distance[k][j]
                }
            }
        }
    }

    val answer = (1..n).minByOrNull { i ->
        (1..n).sumOf { j -> distance[i][j] }
    } ?: -1

    println(answer)
}
