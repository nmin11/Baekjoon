import java.util.*;

class Solution {
    int n, m;
    char[][] grid;
    int[] dx = {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};

    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        grid = new char[n + 2][m + 2];

        for (char[] row : grid) Arrays.fill(row, '.');

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i + 1][j + 1] = storage[i].charAt(j);
            }
        }

        for (String request : requests) {
            char target = request.charAt(0);
            if (request.length() == 2) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= m; j++) {
                        if (grid[i][j] == target) grid[i][j] = '#';
                    }
                }
            } else {
                boolean removed = true;
                while (removed) {
                    removed = false;
                    for (int i = 1; i <= n; i++) {
                        for (int j = 1; j <= m; j++) {
                            if (grid[i][j] == target) {
                                for (int dir = 0; dir < 4; dir++) {
                                    int nx = i + dx[dir];
                                    int ny = j + dy[dir];
                                    if (grid[nx][ny] == '.') {
                                        grid[i][j] = '#';
                                        removed = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            bfs();
        }

        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (grid[i][j] != '.' && grid[i][j] != '#') count++;
            }
        }

        return count;
    }

    private void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < m + 2; j++) {
                if (grid[i][j] == '.') queue.add(new int[]{i, j});
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur[0] + dx[dir];
                int ny = cur[1] + dy[dir];
                if (nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2) continue;
                if (grid[nx][ny] == '#') {
                    grid[nx][ny] = '.';
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }
}