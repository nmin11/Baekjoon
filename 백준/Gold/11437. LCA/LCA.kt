import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

private lateinit var tree: MutableList<MutableList<Int>>
private lateinit var depth: IntArray
private lateinit var parent: IntArray
private lateinit var visited: BooleanArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    tree = MutableList(n + 1) { mutableListOf() }

    repeat(n - 1) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        tree[a].add(b)
        tree[b].add(a)
    }

    depth = IntArray(n + 1)
    parent = IntArray(n + 1)
    visited = BooleanArray(n + 1)

    bfs(1)

    val m = br.readLine().toInt()
    val sb = StringBuilder()
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
    parent[root] = 0

    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (next in tree[cur]) {
            if (!visited[next]) {
                visited[next] = true
                depth[next] = depth[cur] + 1
                parent[next] = cur
                queue.offer(next)
            }
        }
    }
}

private fun findLCA(a: Int, b: Int): Int {
    var nodeA = a
    var nodeB = b

    if (depth[nodeA] < depth[nodeB]) {
        nodeA = nodeB.also { nodeB = nodeA }
    }

    while (depth[nodeA] != depth[nodeB]) {
        nodeA = parent[nodeA]
    }

    while (nodeA != nodeB) {
        nodeA = parent[nodeA]
        nodeB = parent[nodeB]
    }

    return nodeA
}
