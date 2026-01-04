import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.pow

private val mod = 1_000_000_007
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

    val treeSize = 2.0.pow((treeHeight + 1)).toInt()
    leafStartIndex = treeSize / 2
    tree = LongArray(treeSize) { 1 }

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
        tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % mod
    }
}

private fun update(index: Int, value: Long) {
    var treeIndex = leafStartIndex + index
    tree[treeIndex] = value

    while (treeIndex > 1) {
        treeIndex /= 2
        tree[treeIndex] = (tree[treeIndex * 2] * tree[treeIndex * 2 + 1]) % mod
    }
}

private fun query(left: Int, right: Int): Long {
    var leftIndex = leafStartIndex + left
    var rightIndex = leafStartIndex + right
    var product = 1L

    while (leftIndex <= rightIndex) {
        if (leftIndex % 2 == 1) {
            product = (product * tree[leftIndex]) % mod
            leftIndex++
        }

        if (rightIndex % 2 == 0) {
            product = (product * tree[rightIndex]) % mod
            rightIndex--
        }

        leftIndex /= 2
        rightIndex /= 2
    }

    return product
}
