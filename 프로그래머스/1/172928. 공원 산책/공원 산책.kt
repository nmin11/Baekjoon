class Solution {
    private var width = 0
    private var length = 0
    
    private fun move(route: String, cur: IntArray, matrix: Array<CharArray>) {
        val (op, nStr) = route.split(" ")
        val n = nStr.toInt()
        var dy = 0
        var dx = 0
        
        when (op) {
            "N" -> dy -= 1
            "S" -> dy += 1
            "W" -> dx -= 1
            "E" -> dx += 1
        }
        
        val ny = cur[0] + dy * n
        val nx = cur[1] + dx * n
        
        if (ny !in 0 until length || nx !in 0 until width) return
        
        for (i in 1..n) {
            val y = cur[0] + dy * i
            val x = cur[1] + dx * i
            if (matrix[y][x] == 'X') return
        }
        
        cur[0] = ny
        cur[1] = nx
    }
    
    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        length = park.size
        width = park[0].length
        
        var cur = IntArray(2)
        val matrix = park.map { it.toCharArray() }.toTypedArray()
        
        for (i in 0 until length) {
            for (j in 0 until width) {
                if (matrix[i][j] == 'S') {
                    cur[0] = i
                    cur[1] = j
                }
            }
        }
        
        for (route in routes) {
            move(route, cur, matrix)   
        }
        
        return cur
    }
}