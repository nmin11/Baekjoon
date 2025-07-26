class Solution {
    fun solution(wallpaper: Array<String>): IntArray {
        var lux = wallpaper.size - 1
        var luy = wallpaper[0].length - 1
        var rdx = 0
        var rdy = 0
        
        for (i in wallpaper.indices) {
            for (j in wallpaper[i].indices) {
                if (wallpaper[i][j] == '#') {
                    lux = minOf(lux, i)
                    luy = minOf(luy, j)
                    rdx = maxOf(rdx, i + 1)
                    rdy = maxOf(rdy, j + 1)
                }
            }
        }
        
        return intArrayOf(lux, luy, rdx, rdy)
    }
}