import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)
    dp[1] = 0
    val prev = IntArray(n + 1)

    for (i in 2..n) {
        dp[i] = dp[i - 1] + 1
        prev[i] = i - 1

        if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
            dp[i] = dp[i / 2] + 1
            prev[i] = i / 2
        }

        if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
            dp[i] = dp[i / 3] + 1
            prev[i] = i / 3
        }
    }

    println(dp[n])

    val sb = StringBuilder()
    var cur = n
    while (cur >= 1) {
        sb.append(cur).append(' ')
        cur = prev[cur]
    }

    println(sb)
}
