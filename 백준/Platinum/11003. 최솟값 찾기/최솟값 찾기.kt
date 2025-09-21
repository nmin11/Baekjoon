import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val l = st.nextToken().toInt()

    st = StringTokenizer(br.readLine())
    val deque = ArrayDeque<IntArray>()
    for (i in 0 until n) {
        val cur = st.nextToken().toInt()
        while (deque.isNotEmpty() && deque.last()[0] > cur) deque.removeLast()
        deque.addLast(intArrayOf(cur, i))
        if (deque.first()[1] <= i - l) deque.removeFirst()
        bw.write(deque.first()[0].toString())
        if (i < n - 1) bw.write(" ")
    }

    bw.flush()
    br.close()
    bw.close()
}