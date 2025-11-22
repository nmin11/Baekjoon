import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val meetings = Array(n) {
        val st = StringTokenizer(br.readLine())
        intArrayOf(st.nextToken().toInt(), st.nextToken().toInt())
    }

    meetings.sortWith(compareBy({ it[1] }, { it[0] }))

    var count = 0
    var endTime = 0

    for ((start, end) in meetings) {
        if (start >= endTime) {
            endTime = end
            count++
        }
    }

    println(count)
}
