import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val m = br.readLine().toInt()
    val nums = IntArray(n)
    val st = StringTokenizer(br.readLine())
    for (i in 0 until n) {
        nums[i] = st.nextToken().toInt()
    }
    
    var left = 0
    var right = n - 1
    var count = 0
    
    nums.sort()
    
    while (left < right) {
        val sum = nums[left] + nums[right]
        when {
            sum < m -> left++
            sum > m -> right--
            else -> {
                count++
                left++
                right--
            }
        }
    }

    println(count)
}