import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    private static int n, m;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // step 1: 섬 번호 매기기 (BFS)
        int islandCount = 1;
        List<List<Point>> islands = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    List<Point> island = markIsland(i, j, islandCount);
                    islands.add(island);
                    islandCount++;
                }
            }
        }

        // step 2: 가능한 모든 다리 탐색
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (List<Point> island : islands) {
            for (Point point : island) {
                for (int dir = 0; dir < 4; dir++) {
                    Edge bridge = buildBridge(point.row, point.col, dir);
                    if (bridge != null) pq.offer(bridge);
                }
            }
        }

        // step 3: MST 구성
        parent = new int[islandCount];
        for (int i = 0; i < islandCount; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int edgeCount = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalCost += edge.length;
                edgeCount++;
            }
        }

        // 모든 섬의 연결 여부 확인 및 결과 반환
        if (edgeCount == islandCount - 2) {
            System.out.println(totalCost);
        } else {
            System.out.println(-1);
        }
    }

    // 섬 영역 지정 (BFS)
    private static List<Point> markIsland(int startRow, int startCol, int islandNum) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> island = new ArrayList<>();
        queue.offer(new Point(startRow, startCol));
        visited[startRow][startCol] = true;
        map[startRow][startCol] = islandNum;
        island.add(new Point(startRow, startCol));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nr = cur.row + DR[dir];
                int nc = cur.col + DC[dir];

                if (nr >= 0 && nr < n && nc >= 0 && nc < m && !visited[nr][nc] && map[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    map[nr][nc] = islandNum;
                    queue.offer(new Point(nr, nc));
                    island.add(new Point(nr, nc));
                }
            }
        }

        return island;
    }

    // 특정 지점에서 한 방향으로 다리 건설 시도
    private static Edge buildBridge(int row, int col, int dir) {
        int fromIsland = map[row][col];
        int length = 0;
        int nr = row + DR[dir];
        int nc = col + DC[dir];

        while (nr >= 0 && nr < n && nc >= 0 && nc < m) {
            if (map[nr][nc] == fromIsland) return null;

            if (map[nr][nc] != 0) {
                if (length >= 2) {
                    return new Edge(fromIsland, map[nr][nc], length);
                }

                return null;
            }

            length++;
            nr += DR[dir];
            nc += DC[dir];
        }

        return null;
    }

    private static int find(int x) {
        if (parent[x] == x) return x;

        return parent[x] = find(parent[x]);
    }

    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    private static class Point {
        int row, col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int from, to, length;

        public Edge(int from, int to, int length) {
            this.from = from;
            this.to = to;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.length, o.length);
        }
    }
}
