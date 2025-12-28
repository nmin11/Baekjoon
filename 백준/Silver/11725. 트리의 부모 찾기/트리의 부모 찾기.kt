import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var tree: MutableList<MutableList<Int>>
private lateinit var parent: IntArray
private lateinit var visited: BooleanArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    tree = MutableList(n + 1) { mutableListOf() }
    parent = IntArray(n + 1)
    visited = BooleanArray(n + 1)
    repeat(n - 1) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }

    dfs(1)

    val sb = StringBuilder()
    for (i in 2..n) {
        sb.append(parent[i]).append('\n')
    }

    print(sb)
}

private fun dfs(node: Int) {
    visited[node] = true

    for (child in tree[node]) {
        if (!visited[child]) {
            parent[child] = node
            dfs(child)
        }
    }
}
