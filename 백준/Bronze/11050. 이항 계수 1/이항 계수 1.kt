import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, k) = br.readLine().split(" ").map { it.toInt() }
    val dp = Array(n + 1) { IntArray(n + 1) }

    for (i in 0..n) {
        dp[i][0] = 1
        dp[i][i] = 1
    }

    for (i in 2..n) {
        for (j in 1 until i) {
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
        }
    }

    println(dp[n][k])
}
