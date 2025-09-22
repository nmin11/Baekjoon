import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val nums = IntArray(n)
    val results = IntArray(n)

    val st = StringTokenizer(br.readLine())
    for (i in 0 until n) nums[i] = st.nextToken().toInt()
    br.close()

    val stack = ArrayDeque<Int>()
    stack.addLast(0)

    for (i in 1 until n) {
        while (stack.isNotEmpty() && nums[stack.last()] < nums[i]) {
            results[stack.removeLast()] = nums[i]
        }
        stack.addLast(i)
    }

    while (stack.isNotEmpty()) results[stack.removeLast()] = -1

    val bw = BufferedWriter(OutputStreamWriter(System.out))
    for (i in 0 until n) bw.write("${results[i]} ")
    bw.write("\n")
    bw.flush()
    bw.close()
}