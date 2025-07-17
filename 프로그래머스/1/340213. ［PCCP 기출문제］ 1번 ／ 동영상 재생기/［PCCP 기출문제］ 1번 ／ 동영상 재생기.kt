class Solution {
    private var len = 0
    private var now = 0
    private var opStart = 0
    private var opEnd = 0
    
    private fun toSeconds(time: String): Int = time.split(":").let { it[0].toInt() * 60 + it[1].toInt() }
    
    private fun toTime(seconds: Int): String {
        val m = seconds / 60
        val s = seconds % 60
        
        return "%02d:%02d".format(m, s)
    }
    
    private fun skipOp() {
        if (now in opStart until opEnd) {
            now = opEnd
        }
    }
    
    private fun runCommand(command: String) {
        when (command) {
            "prev" -> now = maxOf(0, now - 10)
            "next" -> now = minOf(len, now + 10)
        }
    }
    
    fun solution(video_len: String, pos: String, op_start: String, op_end: String, commands: Array<String>): String {
        len = toSeconds(video_len)
        now = toSeconds(pos)
        opStart = toSeconds(op_start)
        opEnd = toSeconds(op_end)

        skipOp()
        for (command in commands) {
            runCommand(command)
            skipOp()
        }

        return toTime(now)
    }
}