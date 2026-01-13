import java.io.BufferedReader
import java.io.InputStreamReader

private const val max = 1_000_000_000

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m, k) = br.readLine().split(" ").map { it.toInt() }
    val combination = Array(n + m + 1) { IntArray(n + m + 1) }

    for (i in 0..n + m) {
        for (j in 0..i) {
            combination[i][j] = when {
                j == 0 || j == i -> 1
                else -> {
                    val sum = combination[i - 1][j - 1] + combination[i - 1][j]
                    if (sum > max) max + 1 else sum
                }
            }
        }
    }

    if (combination[n + m][m] < k) {
        println(-1)
        return
    }

    var remainA = n
    var remainZ = m
    var remainK = k

    val sb = StringBuilder()
    while (remainA > 0 || remainZ > 0) {
        when {
            remainA == 0 -> {
                sb.append('z')
                remainZ--
            }
            remainZ == 0 -> {
                sb.append('a')
                remainA--
            }
            else -> {
                val count = combination[remainA - 1 + remainZ][remainZ]

                if (count >= remainK) {
                    sb.append('a')
                    remainA--
                } else {
                    sb.append('z')
                    remainK -= count
                    remainZ--
                }
            }
        }
    }

    println(sb)
}
