class Solution {
    fun solution(name: Array<String>, yearning: IntArray, photo: Array<Array<String>>): IntArray {
        val yearnMap = name.zip(yearning.toTypedArray()).toMap()
        
        return photo.map { people ->
            people.sumOf { person -> yearnMap[person] ?: 0 }
        }.toIntArray()
    }
}