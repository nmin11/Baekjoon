import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(" ").map { it.toInt() }

    val root = TrieNode()

    repeat(n) {
        val word = br.readLine()
        insert(root, word)
    }

    var count = 0
    repeat(m) {
        val word = br.readLine()
        if (search(root, word)) {
            count++
        }
    }

    println(count)
}

private fun insert(root: TrieNode, word: String) {
    var cur = root

    for (c in word) {
        val index = c - 'a'

        if (cur.children[index] == null) {
            cur.children[index] = TrieNode()
        }

        cur = cur.children[index]!!
    }

    cur.isEndOfWord = true
}

private fun search(root: TrieNode, word: String): Boolean {
    var cur = root

    for (c in word) {
        val index = c - 'a'

        if (cur.children[index] == null) {
            return false
        }

        cur = cur.children[index]!!
    }

    return cur.isEndOfWord
}

class TrieNode {
    val children = arrayOfNulls<TrieNode>(26)
    var isEndOfWord = false
}
