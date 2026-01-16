import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private fun minCompute(n: Long): Int {
    if (n == 1L) return 0

    val queue: Queue<Pair<Long, Int>> = LinkedList()
    val visited = mutableMapOf<Long, Int>()

    queue.offer(n to 0)
    visited[n] = 0

    while (queue.isNotEmpty()) {
        val (num, cnt) = queue.poll()

        if (num == 1L) {
            return cnt
        }

        if (visited[num]!! < cnt) continue

        if (num % 3 == 0L) {
            val next = num / 3
            if (next !in visited || visited[next]!! > cnt + 1) {
                visited[next] = cnt + 1
                queue.offer(next to cnt + 1)
            }
        }

        if (num % 2 == 0L) {
            val next = num / 2
            if (next !in visited || visited[next]!! > cnt + 1) {
                visited[next] = cnt + 1
                queue.offer(next to cnt + 1)
            }
        }

        val next = num - 1
        if (next !in visited || visited[next]!! > cnt + 1) {
            visited[next] = cnt + 1
            queue.offer(next to cnt + 1)
        }
    }

    return -1
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toLong()

    println(minCompute(n))
}
