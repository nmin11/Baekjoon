import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.sqrt

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    fun isPrime(num: Int): Boolean {
        if (num < 2) return false
        if (num == 2) return true
        if (num % 2 == 0) return false

        for (i in 3..sqrt(num.toDouble()).toInt() step 2) {
            if (num % i == 0) return false
        }

        return true
    }

    fun dfs(num: Int, digit: Int) {
        if (digit == n) {
            println(num)
            return
        }

        for (i in 1..9 step 2) {
            val nextNum = num * 10 + i
            if (isPrime(nextNum)) dfs(nextNum, digit + 1)
        }
    }

    listOf(2, 3, 5, 7).forEach { dfs(it, 1) }
}