import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    
    val sumArr = LongArray(n + 1)
    st = StringTokenizer(br.readLine())
    for (i in 1..n) {
        sumArr[i] = sumArr[i - 1] + st.nextToken().toLong()
    }
    
    repeat(m) {
        st = StringTokenizer(br.readLine())
        val first = st.nextToken().toInt()
        val last = st.nextToken().toInt()
        
        println(sumArr[last] - sumArr[first - 1])
    }
}