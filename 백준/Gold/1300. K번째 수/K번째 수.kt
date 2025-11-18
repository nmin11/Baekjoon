import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val k = br.readLine().toInt()

    var left = 1L
    var right = k.toLong()

    while (left < right) {
        val mid = (left + right) / 2
        val count = (1..n).sumOf { minOf(mid / it, n.toLong()) }

        if (count < k) {
            left = mid + 1
        } else {
            right = mid
        }
    }

    println(left)
}