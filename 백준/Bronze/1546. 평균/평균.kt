fun main() {
    val n = readLine()!!.toInt()
    var maxScore = 0
    var sum = 0
    val nums = readLine()!!.split(" ")
    for (i in 0 until n) {
        val score = nums[i].toInt()
        if (score > maxScore) maxScore = score
        sum += score
    }
    
    println(sum * 100.0 / maxScore / n)
}