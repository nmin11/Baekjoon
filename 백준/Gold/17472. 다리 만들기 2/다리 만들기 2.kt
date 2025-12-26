import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Queue

private val dr = intArrayOf(-1, 0, 1, 0)
private val dc = intArrayOf(0, 1, 0, -1)
private var n = 0
private var m = 0
private lateinit var map: Array<IntArray>
private lateinit var visited: Array<BooleanArray>
private lateinit var parent: IntArray

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val input = br.readLine().split(" ").map { it.toInt() }
    n = input[0]
    m = input[1]

    map = Array(n) { br.readLine().split(" ").map { it.toInt() }.toIntArray() }
    visited = Array(n) { BooleanArray(m) }

    // step 1: 섬 번호 매기기 (BFS)
    var islandCount = 1
    val islands = mutableListOf<List<Point>>()
    for (i in 0 until n) {
        for (j in 0 until m) {
            if (map[i][j] == 1 && !visited[i][j]) {
                val island = markIsland(i, j, islandCount)
                islands.add(island)
                islandCount++
            }
        }
    }

    // step 2: 가능한 모든 다리 탐색
    val pq = PriorityQueue<Edge>()
    for (island in islands) {
        for (point in island) {
            for (dir in 0 until 4) {
                buildBridge(point.row, point.col, dir)?.let { pq.offer(it) }
            }
        }
    }

    // step 3: MST 구성
    parent = IntArray(islandCount) { it }
    var totalCost = 0
    var edgeCount = 0
    while (pq.isNotEmpty()) {
        val edge = pq.poll()

        if (find(edge.from) != find(edge.to)) {
            union(edge.from, edge.to)
            totalCost += edge.length
            edgeCount++
        }
    }

    // 모든 섬의 연결 여부 확인 및 결과 반환
    if (edgeCount == islandCount - 2) {
        println(totalCost)
    } else {
        println(-1)
    }
}

// 섬 영역 지정 (BFS)
private fun markIsland(startRow: Int, startCol: Int, islandNum: Int): List<Point> {
    val queue: Queue<Point> = LinkedList()
    val island = mutableListOf<Point>()
    queue.offer(Point(startRow, startCol))
    visited[startRow][startCol] = true
    map[startRow][startCol] = islandNum
    island.add(Point(startRow, startCol))

    // BFS를 활용해서 섬 영역 지정
    while (queue.isNotEmpty()) {
        val cur = queue.poll()

        for (dir in 0 until 4) {
            val nr = cur.row + dr[dir]
            val nc = cur.col + dc[dir]

            if (nr in 0 until n && nc in 0 until m && !visited[nr][nc] && map[nr][nc] == 1) {
                visited[nr][nc] = true
                map[nr][nc] = islandNum
                queue.offer(Point(nr, nc))
                island.add(Point(nr, nc))
            }
        }
    }

    return island
}

// 특정 지점에서 한 방향으로 다리 건설 시도
private fun buildBridge(row: Int, col: Int, dir: Int): Edge? {
    val fromIsland = map[row][col]
    var length = 0
    var nr = row + dr[dir]
    var nc = col + dc[dir]

    while (nr in 0 until n && nc in 0 until m) {
        if (map[nr][nc] != 0) {
            return if (length >= 2) {
                Edge(fromIsland, map[nr][nc], length)
            } else null
        }

        length++
        nr += dr[dir]
        nc += dc[dir]
    }

    return null
}

private fun find(x: Int): Int {
    if (parent[x] == x) return x
    parent[x] = find(parent[x])
    return parent[x]
}

private fun union(x: Int, y: Int) {
    val rootX = find(x)
    val rootY = find(y)

    if (rootX != rootY) {
        parent[rootY] = rootX
    }
}

data class Point(val row: Int, val col: Int)

data class Edge(val from: Int, val to: Int, val length: Int) : Comparable<Edge> {
    override fun compareTo(other: Edge) = length.compareTo(other.length)
}
