class Solution {
    private int width, length;
    
    private void move(String route, int[] cur, char[][] matrix) {
        String[] routeParts = route.split(" ");
        String op = routeParts[0];
        int n = Integer.parseInt(routeParts[1]);
        int dy = 0;
        int dx = 0;
        
        switch (op) {
            case "N" -> dy -= 1;
            case "S" -> dy += 1;
            case "W" -> dx -= 1;
            case "E" -> dx += 1;
        }
        
        int ny = cur[0] + dy * n;
        int nx = cur[1] + dx * n;
        
        if (ny < 0 || ny >= length || nx < 0 || nx >= width) return;
        
        for (int i = 1; i <= n; i++) {
            int y = cur[0] + dy * i;
            int x = cur[1] + dx * i;
            
            if (matrix[y][x] == 'X') return;
        }
        
        cur[0] = ny;
        cur[1] = nx;
    }
    
    public int[] solution(String[] park, String[] routes) {
        this.length = park.length;
        this.width = park[0].length();
        
        int[] cur = new int[2];
        char[][] matrix = new char[length][width];

        for (int i = 0; i < length; i++) {
            matrix[i] = park[i].toCharArray();
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 'S') {
                    cur[0] = i;
                    cur[1] = j;   
                }
            }
        }
        
        for (String route : routes) {
            move(route, cur, matrix);
        }
        
        return cur;
    }
}