import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val dp = Array(31) { IntArray(31) }

    for (i in 0..30) {
        dp[i][0] = 1
        dp[i][i] = 1
    }

    for (i in 2..30) {
        for (j in 1 until i) {
            dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]
        }
    }
    
    val sb = StringBuilder()
    repeat(t) {
        val (n, m) = br.readLine().split(" ").map { it.toInt() }
        sb.append(dp[m][n]).append('\n')
    }

    print(sb)
}
