class Solution {
    fun solution(keymap: Array<String>, targets: Array<String>): IntArray {
        val MAX_LEN = 100
        val keyCount = mutableMapOf<Char, Int>()
        
        for (key in keymap) {
            key.forEachIndexed { idx, c ->
                keyCount[c] = minOf(keyCount.getOrDefault(c, MAX_LEN), idx + 1)
            }
        }
        
        return targets.map { target ->
            var subtotal = 0
            for (c in target) {
                val press = keyCount[c]
                if (press == null) {
                    return@map -1
                }
                subtotal += press
            }
            subtotal
        }.toIntArray()
    }
}