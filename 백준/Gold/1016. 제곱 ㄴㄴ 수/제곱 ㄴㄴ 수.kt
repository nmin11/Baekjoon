import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val min = st.nextToken().toLong()
    val max = st.nextToken().toLong()

    val isSquare = BooleanArray((max - min + 1).toInt())

    var i = 2L
    while (i * i <= max) {
        val square = i * i

        var start = min / square
        if (min % square != 0L) start++

        var j = start
        while (square * j <= max) {
            isSquare[(square * j - min).toInt()] = true
            j++
        }

        i++
    }

    println(isSquare.count { !it })
}