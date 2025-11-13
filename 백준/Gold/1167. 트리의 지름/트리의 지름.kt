import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

data class Edge(val to: Int, val weight: Int)

lateinit var graph: MutableList<MutableList<Edge>>
lateinit var distance: IntArray
lateinit var visited: BooleanArray

fun bfs(start: Int){
    val queue: Queue<Int> = LinkedList()
    queue.offer(start)
    visited[start] = true

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        for ((to, weight) in graph[cur]) {
            if (!visited[to]) {
                visited[to] = true
                queue.offer(to)
                distance[to] = distance[cur] + weight
            }
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    graph = MutableList(n + 1) { mutableListOf() }

    repeat(n) {
        val st = StringTokenizer(br.readLine())
        val start = st.nextToken().toInt()

        while (true) {
            val end = st.nextToken().toInt()
            if (end == -1) break
            val weight = st.nextToken().toInt()
            graph[start].add(Edge(end, weight))
        }
    }

    distance = IntArray(n + 1)
    visited = BooleanArray(n + 1)
    bfs(1)

    val farthestNode = distance.indices.maxByOrNull { distance[it] } ?: 1

    distance = IntArray(n + 1)
    visited = BooleanArray(n + 1)
    bfs(farthestNode)

    println(distance.maxOrNull())
}