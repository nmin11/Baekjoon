import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val peoples = IntArray(n)

    val st = StringTokenizer(br.readLine())
    for (i in 0 until n) {
        peoples[i] = st.nextToken().toInt()
    }

    peoples.sort()

    var totalSum = 0
    var cumulativeSum = 0

    for (i in 0 until n) {
        cumulativeSum += peoples[i]
        totalSum += cumulativeSum
    }

    println(totalSum)
}