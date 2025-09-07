fun main() {
    val n = readLine()!!.toInt()
    val s = readLine()!!

    println(s.sumOf { it - '0' })
}