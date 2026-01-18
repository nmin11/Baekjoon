import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    if (n == 1) {
        println(1)
        return
    }

    var (prevZero, prevOne) = 0L to 1L

    repeat(n - 1) {
        val curZero = prevZero + prevOne
        val curOne = prevZero

        prevZero = curZero
        prevOne = curOne
    }

    println(prevZero + prevOne)
}
