import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var tree: MutableList<MutableList<Int>>
private lateinit var visited: BooleanArray
private var deletedNode = 0
private var leafCount = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    tree = MutableList(n) { mutableListOf() }
    visited = BooleanArray(n)
    val parents = br.readLine().split(" ").map { it.toInt() }

    var root = -1
    for (i in 0 until n) {
        val parent = parents[i]

        if (parent == -1) {
            root = i
        } else {
            tree[parent].add(i)
        }
    }

    deletedNode = br.readLine().toInt()

    if (deletedNode == root) {
        println(0)
    } else {
        dfs(root)
        println(leafCount)
    }
}

private fun dfs(node: Int) {
    if (node == deletedNode) return

    visited[node] = true
    var childCount = 0
    for (child in tree[node]) {
        if (!visited[child] && child != deletedNode) {
            childCount++
            dfs(child)
        }
    }

    if (childCount == 0) leafCount++
}
