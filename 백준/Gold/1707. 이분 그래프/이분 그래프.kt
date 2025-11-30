import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

lateinit var graph: List<MutableList<Int>>
lateinit var visited: IntArray
var isBipartite: Boolean = false

fun dfs(node: Int, checkNum: Int) {
    visited[node] = checkNum

    for (next in graph[node]) {
        if (visited[next] == -1) {
            dfs(next, 1 - checkNum)
        } else if (visited[next] == checkNum) {
            isBipartite = false
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val k = br.readLine().toInt()

    val sb = StringBuilder()
    repeat(k) {
        var st = StringTokenizer(br.readLine())
        val v = st.nextToken().toInt()
        val e = st.nextToken().toInt()

        graph = List(v + 1) { mutableListOf() }

        repeat(e) {
            st = StringTokenizer(br.readLine())
            val a = st.nextToken().toInt()
            val b = st.nextToken().toInt()
            graph[a].add(b)
            graph[b].add(a)
        }

        visited = IntArray(v + 1) { -1 }
        isBipartite = true

        for (i in 1..v) {
            if (visited[i] == -1) {
                dfs(i, 0)
                if (!isBipartite) break
            }
        }

        sb.append(if (isBipartite) "YES" else "NO").append('\n')
    }

    print(sb)
}
