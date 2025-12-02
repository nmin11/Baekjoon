import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

lateinit var graph: List<MutableList<Int>>
lateinit var visited: BooleanArray
lateinit var count: IntArray

private fun bfs(start: Int) {
    val queue: Queue<Int> = LinkedList()
    queue.offer(start)
    visited[start] = true

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (next in graph[cur]) {
            if (!visited[next]) {
                visited[next] = true
                count[next]++
                queue.offer(next)
            }
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    graph = List(n + 1) { mutableListOf() }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        graph[a].add(b)
    }

    count = IntArray(n + 1)

    for (i in 1..n) {
        visited = BooleanArray(n + 1)
        bfs(i)
    }

    val max = count.maxOrNull() ?: 0

    val result = (1..n).filter { count[it] == max }
    println(result.joinToString(" "))
}