import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

lateinit var graph: MutableList<MutableList<Int>>
lateinit var visited: BooleanArray

fun dfs(node: Int) {
    print("$node ")
    visited[node] = true

    for (neighbor in graph[node]) {
        if (!visited[neighbor]) dfs(neighbor)
    }
}

fun bfs(node: Int) {
    val queue: Queue<Int> = LinkedList()
    queue.offer(node)
    visited[node] = true

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        print("$cur ")

        for (neighbor in graph[cur]) {
            if (!visited[neighbor]) {
                visited[neighbor] = true
                queue.offer(neighbor)
            }
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, start) = br.readLine().split(' ').map { it.toInt() }
    graph = MutableList(n + 1) { mutableListOf() }

    repeat(m) {
        val (u, v) = br.readLine().split(' ').map { it.toInt() }
        graph[u].add(v)
        graph[v].add(u)
    }

    for (i in 1..n) {
        graph[i].sort()
    }

    visited = BooleanArray(n + 1)
    dfs(start)
    println()

    visited = BooleanArray(n + 1)
    bfs(start)
    println()
}