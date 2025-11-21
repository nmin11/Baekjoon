import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val positive = PriorityQueue<Int>(reverseOrder())
    val negative = PriorityQueue<Int>()
    var ones = 0
    var zeros = 0

    repeat(n) {
        when (val num = br.readLine().toInt()) {
            in 2..Int.MAX_VALUE -> positive.offer(num)
            1 -> ones++
            0 -> zeros++
            else -> negative.offer(num)
        }
    }

    var sum = 0

    while (positive.size > 1) {
        sum += positive.poll() * positive.poll()
    }
    if (positive.isNotEmpty()) sum += positive.poll()

    while (negative.size > 1) {
        sum += negative.poll() * negative.poll()
    }
    if (negative.isNotEmpty() && zeros == 0) sum += negative.poll()

    sum += ones

    println(sum)
}
