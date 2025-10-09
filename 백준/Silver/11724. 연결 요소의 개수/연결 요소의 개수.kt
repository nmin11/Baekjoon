import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

lateinit var graph: MutableList<MutableList<Int>>
lateinit var visited: BooleanArray

fun addEdge(head: Int, tail: Int) {
    graph[head].add(tail)
    graph[tail].add(head)
}

fun dfs(v: Int) {
    if (visited[v]) return

    visited[v] = true
    for (i in graph[v]) {
        if (!visited[i]) dfs(i)
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    graph = MutableList(n + 1) { mutableListOf() }
    visited = BooleanArray(n + 1)

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val head = st.nextToken().toInt()
        val tail = st.nextToken().toInt()
        addEdge(head, tail)
    }

    var count = 0
    for (i in 1..n) {
        if (!visited[i]) {
            count++
            dfs(i)
        }
    }

    println(count)
}