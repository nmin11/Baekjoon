import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

private fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val a = st.nextToken().toLong()
    val b = st.nextToken().toLong()

    val gcd = gcd(a, b)
    println("1".repeat(gcd.toInt()))
}
