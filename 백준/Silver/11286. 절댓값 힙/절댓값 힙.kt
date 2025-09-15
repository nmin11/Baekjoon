import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import kotlin.math.abs

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()
    val pq = PriorityQueue<Int> { o1, o2 ->
        val firstAbs = abs(o1)
        val secondAbs = abs(o2)
        if (firstAbs == secondAbs) {
            o1 - o2
        } else {
            firstAbs - secondAbs
        }
    }
    
    repeat(n) {
        val num = br.readLine().toInt()
        if (num == 0) {
            if (pq.isEmpty()) println("0")
            else println(pq.poll())
        } else {
            pq.add(num)
        }
    }
}