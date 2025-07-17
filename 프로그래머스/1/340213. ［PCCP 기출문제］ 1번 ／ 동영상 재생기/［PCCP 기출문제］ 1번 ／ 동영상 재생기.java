class Solution {
    private int len, now, opStart, opEnd;
    
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int m = Integer.parseInt(parts[0]);
        int s = Integer.parseInt(parts[1]);
        
        return m * 60 + s;
    }
    
    private String toTime(int seconds) {
        int m = seconds / 60;
        int s = seconds % 60;
        
        return String.format("%02d:%02d", m, s);
    }
    
    private void skipOp() {
        if (opStart <= now && now < opEnd) {
            now = opEnd;
        }
    }
    
    private void runCommand(String command) {
        switch(command) {
            case "prev":
                now = Math.max(0, now - 10);
                break;
            case "next":
                now = Math.min(len, now + 10);
                break;
            default: return;
        }
    }
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        this.len = toSeconds(video_len);
        this.now = toSeconds(pos);
        this.opStart = toSeconds(op_start);
        this.opEnd = toSeconds(op_end);
        
        skipOp();
        for (int i = 0; i < commands.length; i++) {
            runCommand(commands[i]);
            skipOp();
        }
        
        return toTime(now);
    }
}