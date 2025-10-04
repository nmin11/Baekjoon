import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun radixSort(nums: IntArray) {
    val result = IntArray(nums.size)
    var digit = 1

    repeat(5) {
        val queue = IntArray(10)

        for (num in nums) {
            queue[num / digit % 10]++
        }

        for (i in 1..9) {
            queue[i] += queue[i - 1]
        }

        for (i in nums.size - 1 downTo 0) {
            val digitValue = nums[i] / digit % 10
            result[queue[digitValue] - 1] = nums[i]
            queue[digitValue]--
        }

        for (i in nums.indices) {
            nums[i] = result[i]
        }

        digit *= 10
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    val nums = IntArray(n) { br.readLine().toInt() }
    br.close()

    radixSort(nums)

    for (num in nums) {
        bw.write("$num\n")
    }
    bw.flush()
    bw.close()
}