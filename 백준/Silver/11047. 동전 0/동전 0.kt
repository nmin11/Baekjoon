import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var (n, k) = br.readLine().split(' ').map { it.toInt() }
    val coins = IntArray(n) { br.readLine().toInt() }
    val count = coins.reversedArray().sumOf { coin ->
        (k / coin).also { k %= coin }
    }

    println(count)
}