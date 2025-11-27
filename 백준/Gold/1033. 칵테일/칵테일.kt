import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

data class Edge(val to: Int, val p: Int, val q: Int)

lateinit var graph: List<MutableList<Edge>>
lateinit var visited: BooleanArray
lateinit var amounts: LongArray

private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

private fun dfs(node: Int) {
    visited[node] = true

    for ((to, p, q) in graph[node]) {
        if (!visited[to]) {
            amounts[to] = amounts[node] * q / p
            dfs(to)
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    graph = List(n) { mutableListOf() }
    visited = BooleanArray(n)
    amounts = LongArray(n)

    var lcm = 1L
    repeat(n - 1) {
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()
        val p = st.nextToken().toInt()
        val q = st.nextToken().toInt()

        graph[a].add(Edge(b, p, q))
        graph[b].add(Edge(a, q, p))

        lcm *= p * q / gcd(p.toLong(), q.toLong())
    }

    amounts[0] = lcm
    dfs(0)

    var commonGcd = amounts[0]
    for (i in 1 until n) {
        commonGcd = gcd(commonGcd, amounts[i])
    }

    println(amounts.joinToString(" ") { (it / commonGcd).toString() })
}