import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[][] visited;
    static int[][] graph;
    static int n, m;

    private static void bfs(int x, int y) {
        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> cord = new ArrayList<>(2);
        cord.add(x);
        cord.add(y);
        queue.offer(cord);
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            List<Integer> cur = queue.poll();

            for (int k = 0; k < 4; k++) {
                int cx = cur.get(0) + dx[k];
                int cy = cur.get(1) + dy[k];
                if (cx >= 0 && cy >= 0 && cx < n && cy < m) {
                    if (graph[cx][cy] != 0 && !visited[cx][cy]) {
                        visited[cx][cy] = true;
                        graph[cx][cy] = graph[cur.get(0)][cur.get(1)] + 1;

                        List<Integer> next = new ArrayList<>(2);
                        next.add(cx);
                        next.add(cy);
                        queue.add(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String line = st.nextToken();
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(line.substring(j, j + 1));
            }
        }

        bfs(0, 0);

        System.out.println(graph[n - 1][m - 1]);
    }
}