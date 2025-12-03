import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

val capacity = IntArray(3)
lateinit var visited: Array<BooleanArray>
lateinit var possible: BooleanArray
val sender = intArrayOf(0, 0, 1, 1, 2, 2)
val receiver = intArrayOf(1, 2, 0, 2, 0, 1)

data class State(val a: Int, val b: Int)

private fun bfs() {
    val queue: Queue<State> = LinkedList()
    queue.offer(State(0, 0))
    visited[0][0] = true
    possible[capacity[2]] = true

    while (queue.isNotEmpty()) {
        val (a, b) = queue.poll()
        val c = capacity[2] - a - b

        for (i in 0 until 6) {
            val next = intArrayOf(a, b, c)
            next[receiver[i]] += next[sender[i]]
            next[sender[i]] = 0

            if (next[receiver[i]] > capacity[receiver[i]]) {
                next[sender[i]] = next[receiver[i]] - capacity[receiver[i]]
                next[receiver[i]] = capacity[receiver[i]]
            }

            if (!visited[next[0]][next[1]]) {
                visited[next[0]][next[1]] = true
                queue.offer(State(next[0], next[1]))

                if (next[0] == 0) {
                    possible[next[2]] = true
                }
            }
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    capacity[0] = st.nextToken().toInt()
    capacity[1] = st.nextToken().toInt()
    capacity[2] = st.nextToken().toInt()

    visited = Array(201) { BooleanArray(201) }
    possible = BooleanArray(201)

    bfs()

    val result = (0..capacity[2]).filter { possible[it] }
    println(result.joinToString(" "))
}