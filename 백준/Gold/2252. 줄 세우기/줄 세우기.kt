import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val sb = StringBuilder()
    val (n, m) = br.readLine().split(" ").map { it.toInt() }
    val graph = List(n + 1) { mutableListOf<Int>() }
    val indegree = IntArray(n + 1)

    repeat(m) {
        val (from, to) = br.readLine().split(" ").map { it.toInt() }
        graph[from].add(to)
        indegree[to]++
    }

    val queue: Queue<Int> = LinkedList()
    for (i in 1..n) {
        if (indegree[i] == 0) queue.offer(i)
    }

    while (queue.isNotEmpty()) {
        val cur = queue.poll()
        sb.append(cur).append(' ')

        for (next in graph[cur]) {
            indegree[next]--
            if (indegree[next] == 0) queue.offer(next)
        }
    }

    println(sb)
}
