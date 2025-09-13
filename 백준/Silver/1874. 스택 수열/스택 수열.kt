import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.Stack

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val nums = IntArray(n)
    for (i in 0 until n) {
        nums[i] = br.readLine().toInt()
    }
    
    val stack = Stack<Int>()
    var num = 1
    val sb = StringBuilder()
    var isNoPrinted = false
    
    for (i in 0 until n) {
        val cur = nums[i]
        
        if (cur >= num) {
            while (cur >= num) {
                stack.push(num++)
                sb.append("+\n")
            }
            
            stack.pop()
            sb.append("-\n")
        } else {
            val pop = stack.pop()
            if (pop > cur) {
                println("NO")
                isNoPrinted = true
                break
            } else {
                sb.append("-\n")
            }
        }
    }
    
    if (!isNoPrinted) {
        print(sb.toString())
    }
}