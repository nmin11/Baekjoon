import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val m = br.readLine().toInt()
    val count = br.readLine().split(" ").map { it.toInt() }
    val total = count.sum()
    val k = br.readLine().toInt()

    var answer = 0.0
    for (i in 0 until m) {
        if (count[i] >= k) {
            var probability = 1.0

            for (j in 0 until k) {
                probability *= (count[i] - j).toDouble() / (total - j)
            }

            answer += probability
        }
    }

    println(answer)
}
