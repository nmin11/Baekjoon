import java.io.BufferedReader
import java.io.InputStreamReader

private const val MOD = 10_007

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = IntArray(n + 1)
    dp[0] = 1
    dp[1] = 1

    for (i in 2..n) {
        dp[i] = (dp[i - 1] + (2 * dp[i - 2])) % MOD
    }

    println(dp[n])
}
