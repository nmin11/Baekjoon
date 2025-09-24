import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val a = Array(n) { i ->
        br.readLine().toInt() to i
    }

    a.sortBy { it.first }

    var maxMove = 0
    for (i in a.indices) {
        maxMove = maxOf(maxMove, a[i].second - i)
    }
    
    println(maxMove + 1)
}