import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var parent: IntArray

private fun find(x: Int): Int {
    if (parent[x] == x) return x
    parent[x] = find(parent[x])
    return parent[x]
}

private fun union(x: Int, y: Int) {
    val rootX = find(x)
    val rootY = find(y)

    if (rootX != rootY) {
        parent[rootY] = rootX
    }
}

private fun isSameSet(x: Int, y: Int): Boolean {
    return find(x) == find(y)
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    parent = IntArray(n + 1) { it }

    repeat(m) {
        val (op, a, b) = br.readLine().split(" ").map { it.toInt() }

        when (op) {
            0 -> union(a, b)
            else -> sb.append(if (isSameSet(a, b)) "YES" else "NO").append('\n')
        }
    }

    print(sb)
}