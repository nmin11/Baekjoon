import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue
import java.util.StringTokenizer

lateinit var graph: List<MutableList<Int>>
lateinit var distance: IntArray

private fun bfs(start: Int) {
    val queue: Queue<Int> = LinkedList()
    queue.offer(start)
    distance[start] = 0

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (next in graph[cur]) {
            if (distance[next] == -1) {
                distance[next] = distance[cur] + 1
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
    val k = st.nextToken().toInt()
    val x = st.nextToken().toInt()

    graph = List(n + 1) { mutableListOf() }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        graph[a].add(b)
    }

    distance = IntArray(n + 1) { -1 }

    bfs(x)

    val result = mutableListOf<Int>()
    for (i in 1..n) {
        if (distance[i] == k) {
            result.add(i)
        }
    }

    if (result.isEmpty()) {
        println(-1)
    } else {
        result.sort()
        result.forEach { println(it) }
    }
}