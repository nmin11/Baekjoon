import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val n = br.readLine().toInt()

    val graph = List(n + 1) { mutableListOf<Int>() }
    val indegree = IntArray(n + 1)
    val buildTime = IntArray(n + 1)

    for (i in 1..n) {
        val tokens = br.readLine().split(" ").map { it.toInt() }
        buildTime[i] = tokens[0]

        for (j in 1 until tokens.size - 1) {
            val prerequisite = tokens[j]
            graph[prerequisite].add(i)
            indegree[i]++
        }
    }

    val queue: Queue<Int> = LinkedList()
    val totalTime = IntArray(n + 1)
    for (i in 1..n) {
        if (indegree[i] == 0) {
            queue.offer(i)
            totalTime[i] = buildTime[i]
        }
    }

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        for (next in graph[cur]) {
            indegree[next]--
            totalTime[next] = maxOf(totalTime[next], totalTime[cur] + buildTime[next])

            if (indegree[next] == 0) queue.offer(next)
        }
    }

    for (i in 1..n) {
        sb.append(totalTime[i]).append('\n')
    }

    print(sb)
}
