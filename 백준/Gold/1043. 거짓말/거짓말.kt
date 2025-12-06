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
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val insiderTokens = br.readLine().split(" ").map { it.toInt() }
    val insiderCount = insiderTokens[0]
    val insiders = if (insiderCount > 0) {
        insiderTokens.drop(1).take(insiderCount)
    } else {
        emptyList()
    }

    parent = IntArray(n + 1) { it }

    val parties = List(m) {
        val tokens = br.readLine().split(" ").map { it.toInt() }
        val partySize = tokens[0]
        tokens.drop(1).take(partySize)
    }

    parties.forEach { party ->
        if (party.isNotEmpty()) {
            val first = party[0]
            party.drop(1).forEach { person ->
                union(first, person)
            }
        }
    }

    val result = parties.count { party ->
        if (party.isEmpty()) {
            true
        } else {
            val partyRoot = find(party[0])
            insiders.none { find(it) == partyRoot }
        }
    }

    println(result)
}