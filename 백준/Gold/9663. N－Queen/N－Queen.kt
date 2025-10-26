import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val board = IntArray(n)
    var count = 0

    fun isValid(row: Int): Boolean {
        return (0 until row).all {
            board[it] != board[row] && abs(row - it) != abs(board[row] - board[it])
        }
    }

    fun backtracking(row: Int) {
        if (row == n) {
            count++
            return
        }

        for (col in 0 until n) {
            board[row] = col
            if (isValid(row)) backtracking(row + 1)
        }
    }

    backtracking(0)
    println(count)
}