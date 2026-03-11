import java.util.ArrayDeque

class Solution {
    private var n = 0
    private var m = 0
    private lateinit var grid: Array<CharArray>
    private val dx = intArrayOf(0, 0, 1, -1)
    private val dy = intArrayOf(1, -1, 0, 0)

    fun solution(storage: Array<String>, requests: Array<String>): Int {
        n = storage.size
        m = storage[0].length
        grid = Array(n + 2) { CharArray(m + 2) { '.' } }

        for (i in 0 until n)
            for (j in 0 until m)
                grid[i + 1][j + 1] = storage[i][j]

        for (request in requests) {
            val target = request[0]
            if (request.length == 2) {
                for (i in 1..n)
                    for (j in 1..m)
                        if (grid[i][j] == target) grid[i][j] = '#'
            } else {
                var removed = true
                while (removed) {
                    removed = false
                    for (i in 1..n) {
                        for (j in 1..m) {
                            if (grid[i][j] == target) {
                                for (dir in 0..3) {
                                    if (grid[i + dx[dir]][j + dy[dir]] == '.') {
                                        grid[i][j] = '#'
                                        removed = true
                                        break
                                    }
                                }
                            }
                        }
                    }
                }
            }
            bfs()
        }

        return (1..n).sumOf { i ->
            (1..m).count { j -> grid[i][j] != '.' && grid[i][j] != '#' }
        }
    }

    private fun bfs() {
        val queue = ArrayDeque<IntArray>()
        for (i in 0 until n + 2)
            for (j in 0 until m + 2)
                if (grid[i][j] == '.') queue.add(intArrayOf(i, j))

        while (queue.isNotEmpty()) {
            val (x, y) = queue.poll()
            for (dir in 0..3) {
                val nx = x + dx[dir]
                val ny = y + dy[dir]
                if (nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2) continue
                if (grid[nx][ny] == '#') {
                    grid[nx][ny] = '.'
                    queue.add(intArrayOf(nx, ny))
                }
            }
        }
    }
}