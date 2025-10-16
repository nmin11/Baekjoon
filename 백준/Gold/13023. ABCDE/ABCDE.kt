import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

var isConnected = false
lateinit var graph: MutableList<MutableList<Int>>
lateinit var visited: BooleanArray

fun dfs(node: Int, depth: Int) {
    if (depth == 5 || isConnected) {
        isConnected = true
        return
    }

    visited[node] = true

    for (v in graph[node]) {
        if (!visited[v]) dfs(v, depth + 1)
    }

    visited[node] = false
}

fun addEdge(u: Int, v: Int) {
    graph[u].add(v)
    graph[v].add(u)
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    visited = BooleanArray(n)
    graph = MutableList(n) { mutableListOf() }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val u = st.nextToken().toInt()
        val v = st.nextToken().toInt()
        addEdge(u, v)
    }

    for (i in 0 until n) {
        dfs(i, 1)
        if (isConnected) break
    }

    println(if (isConnected) 1 else 0)
}