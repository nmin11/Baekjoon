import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val st = StringTokenizer(br.readLine())
    
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    
    val prefixSum = LongArray(n + 1)
    val count = LongArray(m)
    
    val nums = StringTokenizer(br.readLine())
    for (i in 1..n) {
        val num = nums.nextToken().toInt()
        prefixSum[i] = (prefixSum[i - 1] + num) % m
        if (prefixSum[i] < 0) {
            prefixSum[i] = prefixSum[i] + m
        }
        count[prefixSum[i].toInt()]++
    }
    
    var result = 0L
    result += count[0]

    for (i in 0 until m) {
        if (count[i] >= 2) {
            result += count[i] * (count[i] - 1) / 2
        }
    }
    
    println(result)
}