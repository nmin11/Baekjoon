import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    br.readLine()

    val arr = br.readLine().split(' ').map { it.toInt() }.toIntArray()
    arr.sort()

    br.readLine()
    val targets = br.readLine().split(' ')

    val result = buildString {
        for (target in targets) {
            append(if (arr.binarySearch(target.toInt()) >= 0) '1' else '0')
            append('\n')
        }
    }

    print(result)
}