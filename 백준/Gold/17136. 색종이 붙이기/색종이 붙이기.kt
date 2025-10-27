import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

val board = Array(10) { IntArray(10) }
val remain = intArrayOf(0, 5, 5, 5, 5, 5)
var result = Int.MAX_VALUE

fun isValid(x: Int, y: Int, size: Int): Boolean {
    if (x + size > 10 || y + size > 10) return false

    for (i in y until y + size) {
        for (j in x until x + size) {
            if (board[i][j] == 0) return false
        }
    }

    return true
}

fun fill(x: Int, y: Int, size: Int, flag: Int) {
    for (i in y until y + size) {
        for (j in x until x + size) {
            board[i][j] = flag
        }
    }
}

fun backtracking(x: Int, y: Int, count: Int) {
    if (count >= result) return

    if (y == 10) {
        result = count
        return
    }

    val nextX = (x + 1) % 10
    val nextY = if (nextX == 0) y + 1 else y

    if (board[y][x] == 0) {
        backtracking(nextX, nextY, count)
        return
    }

    for (size in 5 downTo 1) {
        if (remain[size] == 0 || !isValid(x, y, size)) continue

        remain[size]--
        fill(x, y, size, 0)
        backtracking(nextX, nextY, count + 1)
        fill(x, y, size, 1)
        remain[size]++
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    for (i in 0 until 10) {
        val st = StringTokenizer(br.readLine())
        for (j in 0 until 10) {
            board[i][j] = st.nextToken().toInt()
        }
    }

    backtracking(0, 0, 0)

    println(if (result == Int.MAX_VALUE) -1 else result)
}