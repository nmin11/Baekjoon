import java.io.BufferedReader
import java.io.InputStreamReader

private const val MOD = 1_000_000_000L

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = LongArray(n + 1)
    dp[0] = 1

    if (n >= 1) dp[1] = 0
    if (n >= 2) dp[2] = 1

    for (i in 3..n) {
        dp[i] = ((i - 1) * (dp[i - 1] + dp[i - 2])) % MOD
    }

    println(dp[n])
}
