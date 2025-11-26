import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private fun gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()

    val sb = StringBuilder()
    repeat(t) {
        val st = StringTokenizer(br.readLine())
        val a = st.nextToken().toInt()
        val b = st.nextToken().toInt()

        val lcm = a.toLong() * b / gcd(a, b)
        sb.append(lcm).append('\n')
    }

    print(sb)
}
