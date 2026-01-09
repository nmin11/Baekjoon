import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val t = br.readLine().toInt()
    val dp = Array(15) { IntArray(15) }

    for (i in 0 until 15) {
        dp[0][i] = i
        dp[i][1] = 1
    }

    for (i in 1 until 15) {
        for (j in 2 until 15) {
            dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
        }
    }

    val sb = StringBuilder()
    repeat(t) {
        val k = br.readLine().toInt()
        val n = br.readLine().toInt()
        sb.append(dp[k][n]).append('\n')
    }
    
    print(sb)
}
