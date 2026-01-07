import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private lateinit var tree: MutableList<MutableList<Int>>
private lateinit var depth: IntArray
private lateinit var parent: Array<IntArray>
private lateinit var visited: BooleanArray
private var maxLevel = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    tree = MutableList(n + 1) { mutableListOf() }

    repeat(n - 1) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }

    maxLevel = 0
    var tmp = 1
    while (tmp <= n) {
        tmp = tmp shl 1
        maxLevel++
    }

    depth = IntArray(n + 1)
    parent = Array(maxLevel + 1) { IntArray(n + 1) }
    visited = BooleanArray(n + 1)

    bfs(1)

    for (k in 1..maxLevel) {
        for (node in 1..n) {
            parent[k][node] = parent[k - 1][parent[k - 1][node]]
        }
    }

    val sb = StringBuilder()
    val m = br.readLine().toInt()
    repeat(m) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        val lca = findLCA(a, b)
        sb.append(lca).append('\n')
    }

    print(sb)
}

private fun bfs(root: Int) {
    val queue: Queue<Int> = LinkedList()
    queue.offer(root)
    visited[root] = true
    depth[root] = 0
    parent[0][root] = 0

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (next in tree[cur]) {
            if (!visited[next]) {
                visited[next] = true
                depth[next] = depth[cur] + 1
                parent[0][next] = cur
                queue.offer(next)
            }
        }
    }
}

private fun findLCA(a: Int, b: Int): Int {
    var nodeA = a
    var nodeB = b

    if (depth[nodeA] > depth[nodeB]) {
        nodeA = nodeB.also { nodeB = nodeA }
    }

    val diff = depth[nodeB] - depth[nodeA]
    for (k in 0..maxLevel) {
        if ((diff and (1 shl k)) != 0) {
            nodeB = parent[k][nodeB]
        }
    }

    if (nodeA == nodeB) return nodeA

    for (k in maxLevel downTo 0) {
        if (parent[k][nodeA] != parent[k][nodeB]) {
            nodeA = parent[k][nodeA]
            nodeB = parent[k][nodeB]
        }
    }

    return parent[0][nodeA]
}
