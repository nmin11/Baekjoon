import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(' ').map { it.toInt() }
    val graph = Array(n) { br.readLine().map { it - '0' }.toIntArray() }
    val visited = Array(n) { BooleanArray(m) }
    val directions = listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)

    val queue: Queue<Pair<Int, Int>> = LinkedList()
    queue.offer(0 to 0)
    visited[0][0] = true

    while (queue.isNotEmpty()) {
        val (x, y) = queue.poll()

        directions.forEach { (dx, dy) ->
                            val nx = x + dx
                            val ny = y + dy

                            if (nx in 0 until n && ny in 0 until m && graph[nx][ny] != 0 && !visited[nx][ny]) {
                                visited[nx][ny] = true
                                graph[nx][ny] = graph[x][y] + 1
                                queue.offer(nx to ny)
                            }
                           }
    }

    println(graph[n - 1][m - 1])
}