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

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()

    parent = IntArray(n + 1) { it }

    repeat(n) { i ->
        val connections = br.readLine().split(" ").map { it.toInt() }
        connections.forEachIndexed { j, connected ->
            if (connected == 1) union(i + 1, j + 1)
        }
    }

    val route = br.readLine().split(" ").map { it.toInt() }
    val firstRoot = find(route[0])
    val isPossible = route.all { find(it) == firstRoot }

    println(if (isPossible) "YES" else "NO")
}