import java.io.BufferedReader
import java.io.InputStreamReader

private fun power(base: Long, exp: Long, mod: Long): Long {
    var result = 1L
    var b = base % mod
    var e = exp

    while (e > 0) {
        if (e % 2 == 1L) {
            result = (result * b) % mod
        }

        b = (b * b) % mod
        e /= 2
    }

    return result
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()

    val sb = StringBuilder()
    repeat(t) {
        val (a, b) = br.readLine().split(" ").map { it.toLong() }
        var result = power(a, b, 10)

        if (result == 0L) result = 10L

        sb.append(result).append('\n')
    }

    print(sb)
}
