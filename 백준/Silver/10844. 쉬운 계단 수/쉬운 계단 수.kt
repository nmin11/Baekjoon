import java.io.BufferedReader
import java.io.InputStreamReader

private const val MOD = 1_000_000_000L

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val dp = Array(n + 1) { LongArray(10) }

    for (digit in 1..9) {
        dp[1][digit] = 1
    }

    for (len in 2..n) {
        dp[len][0] = dp[len - 1][1]
        dp[len][9] = dp[len - 1][8]

        for (digit in 1..8) {
            dp[len][digit] = (dp[len - 1][digit - 1] + dp[len - 1][digit + 1]) % MOD
        }
    }

    val answer = dp[n].sum() % MOD
    println(answer)
}
