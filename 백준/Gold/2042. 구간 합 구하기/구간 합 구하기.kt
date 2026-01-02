import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

private lateinit var tree: LongArray
private var leafStartIndex = 0

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }

    var treeHeight = 0
    var length = n
    while (length > 0) {
        length /= 2
        treeHeight++
    }

    val treeSize = 2.0.pow((treeHeight + 1).toDouble()).toInt()
    leafStartIndex = treeSize / 2
    tree = LongArray(treeSize)
    repeat(n) { i ->
        tree[leafStartIndex + i] = br.readLine().toLong()
    }

    buildTree()

    val sb = StringBuilder()
    repeat(m + k) {
        val input = br.readLine().split(" ")
        val command = input[0].toInt()
        val left = input[1].toInt()
        val right = input[2].toLong()

        when (command) {
            1 -> update(left - 1, right)
            2 -> sb.append(query(left - 1, right.toInt() - 1)).append('\n')
        }
    }

    print(sb)
}

private fun buildTree() {
    for (i in leafStartIndex - 1 downTo 1) {
        tree[i] = tree[i * 2] + tree[i * 2 + 1]
    }
}

private fun update(index: Int, value: Long) {
    var treeIndex = leafStartIndex + index
    val diff = value - tree[treeIndex]

    while (treeIndex > 0) {
        tree[treeIndex] += diff
        treeIndex /= 2
    }
}

private fun query(left: Int, right: Int): Long {
    var leftIndex = leafStartIndex + left
    var rightIndex = leafStartIndex + right
    var sum = 0L

    while (leftIndex <= rightIndex) {
        if (leftIndex % 2 == 1) {
            sum += tree[leftIndex]
            leftIndex++
        }

        if (rightIndex % 2 == 0) {
            sum += tree[rightIndex]
            rightIndex--
        }

        leftIndex /= 2
        rightIndex /= 2
    }

    return sum
}
