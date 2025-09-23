import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`)
    val s = sc.next()
    val n = s.length
    val nums = IntArray(n)
    
    for (i in 0 until n) {
        nums[i] = s[i] - '0'
    }
    
    /* Selection Sort */
    for (i in 0 until n) {
        var max = i
        for (j in i + 1 until n) {
            if (nums[j] > nums[max]) max = j
        }
        
        if (nums[i] < nums[max]) {
            nums[i] = nums[max].also { nums[max] = nums[i] }
        }
    }
    
    for (i in 0 until n) {
        print(nums[i])
    }
}