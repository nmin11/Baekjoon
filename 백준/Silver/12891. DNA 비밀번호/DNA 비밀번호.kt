import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

var checkArr = IntArray(4)
var curArr = IntArray(4)
var checkSecret = 0

fun addCount(c: Char) {
    when (c) {
        'A' -> {
            curArr[0]++
            if (curArr[0] == checkArr[0]) checkSecret++
        }
        'C' -> {
            curArr[1]++
            if (curArr[1] == checkArr[1]) checkSecret++
        }
        'G' -> {
            curArr[2]++
            if (curArr[2] == checkArr[2]) checkSecret++
        }
        'T' -> {
            curArr[3]++
            if (curArr[3] == checkArr[3]) checkSecret++
        }
    }
}

fun minusCount(c: Char) {
    when (c) {
        'A' -> {
            if (curArr[0] == checkArr[0]) checkSecret--
            curArr[0]--
        }
        'C' -> {
            if (curArr[1] == checkArr[1]) checkSecret--
            curArr[1]--
        }
        'G' -> {
            if (curArr[2] == checkArr[2]) checkSecret--
            curArr[2]--
        }
        'T' -> {
            if (curArr[3] == checkArr[3]) checkSecret--
            curArr[3]--
        }
    }
}

fun main() {
    val br = BufferedReader(InputStreamReader(System.`in`))
    var st = StringTokenizer(br.readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val dna = br.readLine().toCharArray()
    
    var count = 0
    checkSecret = 0
    
    st = StringTokenizer(br.readLine())
    for (i in 0 until 4) {
        checkArr[i] = st.nextToken().toInt()
        if (checkArr[i] == 0) {
            checkSecret++
        }
    }
    
    for (i in 0 until m) {
        addCount(dna[i])
    }
    
    if (checkSecret == 4) count++
    
    for (i in m until n) {
        val j = i - m
        addCount(dna[i])
        minusCount(dna[j])
        if (checkSecret == 4) count++
    }
    
    println(count)
}