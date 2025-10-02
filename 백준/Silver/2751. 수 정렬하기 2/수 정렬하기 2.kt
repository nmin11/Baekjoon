import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

var nums = IntArray(0)
var tmp = IntArray(0)

private fun mergeSort(start: Int, end: Int) {
    if (end - start < 1) return

    val mid = start + (end - start) / 2
    mergeSort(start, mid)
    mergeSort(mid + 1, end)

    for (i in start..end) {
        tmp[i] = nums[i]
    }

    var k = start
    var left = start
    var right = mid + 1

    while (left <= mid && right <= end) {
        if (tmp[left] > tmp[right]) {
            nums[k] = tmp[right]
            k++
            right++
        } else {
            nums[k] = tmp[left]
            k++
            left++
        }
    }

    while (left <= mid) {
        nums[k] = tmp[left]
        k++
        left++
    }

    while (right <= end) {
        nums[k] = tmp[right]
        k++
        right++
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    val n = br.readLine().toInt()
    nums = IntArray(n + 1)
    tmp = IntArray(n + 1)

    for (i in 1..n) {
        nums[i] = br.readLine().toInt()
    }
    br.close()

    mergeSort(1, n)

    for (i in 1..n) {
        bw.write("${nums[i]}\n")
    }
    bw.flush()
    bw.close()
}