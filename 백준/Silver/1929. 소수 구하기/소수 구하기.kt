import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    val m = st.nextToken().toInt()
    val n = st.nextToken().toInt()

    val isPrime = BooleanArray(n + 1) { true }
    isPrime[0] = false
    isPrime[1] = false

    var i = 2
    while (i * i <= n) {
        if (!isPrime[i]) {
            i++
            continue
        }

        var j = i * i
        while (j <= n) {
            isPrime[j] = false
            j += i
        }

        i++
    }

    val sb = StringBuilder()
    for (num in m..n) {
        if (isPrime[num]) sb.append(num).append('\n')
    }

    print(sb)
}