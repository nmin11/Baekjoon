import java.io.BufferedReader
import java.io.InputStreamReader

private lateinit var tree: Array<IntArray>

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    tree = Array(26) { IntArray(2) }

    repeat(n) {
        val (nodeStr, leftStr, rightStr) = br.readLine().split(" ")
        val node = nodeStr[0] - 'A'
        val left = leftStr[0]
        val right = rightStr[0]

        tree[node][0] = if (left == '.') -1 else left - 'A'
        tree[node][1] = if (right == '.') -1 else right - 'A'
    }

    val sb = StringBuilder()
    preOrder(0, sb)
    sb.append('\n')

    inOrder(0, sb)
    sb.append('\n')

    postOrder(0, sb)
    sb.append('\n')

    print(sb)
}

private fun preOrder(node: Int, sb: StringBuilder) {
    if (node == -1) return

    sb.append('A' + node)
    preOrder(tree[node][0], sb)
    preOrder(tree[node][1], sb)
}

private fun inOrder(node: Int, sb: StringBuilder) {
    if (node == -1) return

    inOrder(tree[node][0], sb)
    sb.append('A' + node)
    inOrder(tree[node][1], sb)
}

private fun postOrder(node: Int, sb: StringBuilder) {
    if (node == -1) return

    postOrder(tree[node][0], sb)
    postOrder(tree[node][1], sb)
    sb.append('A' + node)
}
