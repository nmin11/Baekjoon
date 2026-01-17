import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val task = IntArray(n + 1)
    val profit = IntArray(n + 1)

    repeat(n) { i ->
        val (t, p) = br.readLine().split(" ").map { it.toInt() }
        task[i + 1] = t
        profit[i + 1] = p
    }

    val dp = IntArray(n + 2)

    for (day in n downTo 1) {
        val finishDay = day + task[day]
        dp[day] = if (finishDay > n + 1) {
            dp[day + 1]
        } else {
            maxOf(dp[day + 1], profit[day] + dp[finishDay])
        }
    }

    println(dp[1])
}
