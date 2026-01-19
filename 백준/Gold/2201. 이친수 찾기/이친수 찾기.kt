import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val k = br.readLine().toLong()

    if (k == 1L) {
        println(1)
        return
    }

    if (k == 2L) {
        println(10)
        return
    }

    val fibonacci = LongArray(100)
    fibonacci[0] = 1
    fibonacci[1] = 2
    fibonacci[2] = 3

    var len = 2
    while (true) {
        fibonacci[len] = fibonacci[len - 1] + fibonacci[len - 2]
        if (fibonacci[len] > k) break
        len++
    }

    var remaining = k
    var digit = len - 1
    val result = buildString {
        while (digit >= 0) {
            if (fibonacci[digit] <= remaining) {
                append('1')
                remaining -= fibonacci[digit]
            } else {
                append('0')
            }

            digit--
        }
    }

    println(result)
}
