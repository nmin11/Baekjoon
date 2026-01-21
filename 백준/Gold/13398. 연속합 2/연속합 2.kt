import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val arr = br.readLine().split(" ").map { it.toInt() }.toIntArray()

    val dp = Array(n) { IntArray(2) }
    dp[0][0] = arr[0]
    dp[0][1] = arr[0]

    var answer = arr[0]
    for (i in 1 until n) {
        dp[i][0] = maxOf(arr[i], dp[i - 1][0] + arr[i])
        dp[i][1] = maxOf(dp[i - 1][0], dp[i - 1][1] + arr[i])

        answer = maxOf(answer, maxOf(dp[i][0], dp[i][1]))
    }

    println(answer)
}
