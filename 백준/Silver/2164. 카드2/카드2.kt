import java.util.LinkedList
import java.util.Queue

fun main() {
    val n = readLine()!!.toInt()
    val q: Queue<Int> = LinkedList()
    
    for (i in 1..n) {
        q.add(i)
    }
    
    while (q.size > 1) {
        q.poll()
        q.add(q.poll())
    }
    
    println(q.poll())
}