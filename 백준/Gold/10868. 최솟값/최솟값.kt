import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

private lateinit var tree: IntArray
private var leafStartIndex = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    var treeHeight = 0
    var length = n
    while (length > 0) {
        length /= 2
        treeHeight++
    }

    val treeSize = 2.0.pow((treeHeight + 1)).toInt()
    leafStartIndex = treeSize / 2
    tree = IntArray(treeSize) { Int.MAX_VALUE }
    repeat(n) { i ->
        tree[leafStartIndex + i] = br.readLine().toInt()
    }

    buildTree()

    val sb = StringBuilder()
    repeat(m) {
        val (a, b) = br.readLine().split(" ").map { it.toInt() }
        sb.append(query(a - 1, b - 1)).append('\n')
    }

    print(sb)
}

private fun buildTree() {
    for (i in leafStartIndex - 1 downTo 1) {
        tree[i] = minOf(tree[i * 2], tree[i * 2 + 1])
    }
}

private fun query(left: Int, right: Int): Int {
    var leftIndex = leafStartIndex + left
    var rightIndex = leafStartIndex + right
    var min = Int.MAX_VALUE

    while (leftIndex <= rightIndex) {
        if (leftIndex % 2 == 1) {
            min = minOf(min, tree[leftIndex])
            leftIndex++
        }

        if (rightIndex % 2 == 0) {
            min = minOf(min, tree[rightIndex])
            rightIndex--
        }

        leftIndex /= 2
        rightIndex /= 2
    }

    return min
}
