import java.io.BufferedReader
import java.io.InputStreamReader

private fun canFit(lessons: IntArray, size: Int, maxCount: Int): Boolean {
    var curSum = 0
    var blueRayCount = 1

    for (lesson in lessons) {
        if (curSum + lesson > size) {
            blueRayCount++
            curSum = lesson

            if (blueRayCount > maxCount) return false
        } else {
            curSum += lesson
        }
    }

    return true
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = br.readLine().split(' ').map { it.toInt() }
    val lessons = br.readLine().split(' ').map { it.toInt() }.toIntArray()

    var left = lessons.maxOrNull() ?: 0
    var right = lessons.sum()
    var answer = right

    while (left <= right) {
        val mid = (left + right) / 2

        if (canFit(lessons, mid, m)) {
            answer = mid
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    println(answer)
}