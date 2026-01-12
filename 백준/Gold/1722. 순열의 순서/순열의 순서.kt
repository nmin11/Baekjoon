import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val used = BooleanArray(n + 1)
    val factorial = LongArray(n + 1)
    factorial[0] = 1
    for (i in 1..n) {
        factorial[i] = factorial[i - 1] * i
    }

    val input = br.readLine().split(" ")
    val command = input[0].toInt()

    val sb = StringBuilder()
    if (command == 1) {
        var k = input[1].toLong()
        val result = IntArray(n + 1)

        for (i in 1..n) {
            var count = 1

            for (j in 1..n) {
                if (used[j]) continue

                if (k <= count * factorial[n - i]) {
                    k -= (count - 1) * factorial[n - i]
                    result[i] = j
                    used[j] = true
                    break
                }

                count++
            }
        }

        sb.append(result.slice(1..n).joinToString(" "))
    } else {
        val permutation = input.drop(1).map { it.toInt() }
        var order = 1L

        for (i in 0 until n) {
            var smallerCount = 0L

            for (j in 1 until permutation[i]) {
                if (!used[j]) smallerCount++
            }

            order += smallerCount * factorial[n - i - 1]
            used[permutation[i]] = true
        }

        sb.append(order)
    }

    print(sb)
}
