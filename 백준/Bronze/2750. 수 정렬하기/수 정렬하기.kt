import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val nums = IntArray(n) { br.readLine().toInt() }
    
    /* Bubble Sort */
    for (i in nums.indices) {
        for (j in 0 until nums.size - 1 - i) {
            if (nums[j] > nums[j + 1]) {
                nums[j] = nums[j + 1].also { nums[j + 1] = nums[j] }
            }
        }
    }
    
    nums.forEach { println(it) }
}