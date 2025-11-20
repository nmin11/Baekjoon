import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val pq = PriorityQueue<Int>()
    repeat(n) {
        pq.offer(br.readLine().toInt())
    }

    var total = 0
    while (pq.size > 1) {
        val sum = pq.poll() + pq.poll()
        total += sum
        pq.offer(sum)
    }

    println(total)
}
