import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

var a = IntArray(0)
var tmp = IntArray(0)

fun mergeSort(start: Int, end: Int) {
    if (end - start < 1) return

    val mid = start + (end - start) / 2

    mergeSort(start, mid)
    mergeSort(mid + 1, end)

    for (i in start..end) {
        tmp[i] = a[i]
    }

    var k = start
    var left = start
    var right = mid + 1

    while (left <= mid && right <= end) {
        if (tmp[left] > tmp[right]) {
            a[k] = tmp[right]
            k++
            right++
        } else {
            a[k] = tmp[left]
            k++
            left++
        }
    }

    while (left <= mid) {
        a[k] = tmp[left]
        k++
        left++
    }

    while (right <= end) {
        k++
        right++
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val k = st.nextToken().toInt()

    a = IntArray(n + 1)
    tmp = IntArray(n + 1)

    st = StringTokenizer(br.readLine())
    for (i in 1..n) {
        a[i] = st.nextToken().toInt()
    }

    mergeSort(1, n)

    println(a[k])
}