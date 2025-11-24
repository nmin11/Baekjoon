import java.io.BufferedReader
import java.io.InputStreamReader

private fun isPalindrome(num: Int): Boolean {
    val s = num.toString()
    var left = 0
    var right = s.length - 1

    while (left < right) {
        if (s[left] != s[right]) return false

        left++
        right--
    }

    return true
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val n = br.readLine().toInt()

    val limit = 10000000
    val isPrime = BooleanArray(limit + 1) { it >= 2 }

    var i = 2
    while (i * i <= limit) {
        if (!isPrime[i]) {
            i++
            continue
        }

        var j = i * i
        while (j <= limit) {
            isPrime[j] = false
            j += i
        }

        i++
    }

    for (num in n..limit) {
        if (isPrime[num] && isPalindrome(num)) {
            println(num)
            break
        }
    }
}
