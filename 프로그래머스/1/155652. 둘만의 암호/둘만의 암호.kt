class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        val alphabet = ('a' .. 'z')
            .filterNot { it in skip }
        val size = alphabet.size
        val map = alphabet.withIndex().associate { it.value to it.index }
        
        return s.map { c ->
            alphabet[(map[c]!! + index) % size]
        }.joinToString("")
    }
}