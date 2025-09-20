fun main() {
    val br = System.`in`.bufferedReader()

    val n = br.readLine().toInt()
    val nums = br.readLine().split(' ').map { it.toLong() }.toLongArray()

    nums.sort()
    var count = 0

    for (i in nums.indices) {
        val target = nums[i]
        var left = 0
        var right = n - 1

        while (left < right) {
            val sum = nums[left] + nums[right]

            when {
                sum == target -> {
                    when {
                        left == i -> left++
                        right == i -> right--
                        else -> {
                            count++
                            break
                        }
                    }
                }
                sum < target -> left++
                else -> right--
            }
        }
    }

    println(count)
}