import java.io.BufferedReader
import java.io.InputStreamReader

fun sumNumbers(group: String): Int {
    return group.split("+").sumOf { it.toInt() }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val groups = br.readLine().split("-")

    var result = sumNumbers(groups[0])
    for (i in 1 until groups.size) {
        result -= sumNumbers(groups[i])
    }

    println(result)
}