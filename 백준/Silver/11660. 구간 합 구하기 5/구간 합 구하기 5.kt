import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())

    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val table = Array(n + 1) { IntArray(n + 1) }
    for (i in 1..n) {
        st = StringTokenizer(br.readLine())
        for (j in 1..n) {
            table[i][j] = table[i][j - 1] + table[i - 1][j] - table[i - 1][j - 1] + st.nextToken().toInt()
        }
    }

    repeat(m) {
        st = StringTokenizer(br.readLine())
        val x1 = st.nextToken().toInt()
        val y1 = st.nextToken().toInt()
        val x2 = st.nextToken().toInt()
        val y2 = st.nextToken().toInt()

        val result = table[x2][y2] - table[x1 - 1][y2] - table[x2][y1 - 1] + table[x1 - 1][y1 - 1]
        println(result)
    }
}