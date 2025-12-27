import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int totalLength = 0;
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < n; j++) {
                int length = charToLength(line[j]);
                totalLength += length;

                if (i != j && length > 0) {
                    pq.offer(new Edge(i, j, length));
                }
            }
        }

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int mstCost = 0;
        int edgeCount = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                mstCost += edge.length;
                edgeCount++;
            }
        }

        if (edgeCount == n - 1) {
            System.out.println(totalLength - mstCost);
        } else {
            System.out.println(-1);
        }
    }

    private static int charToLength(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 27;
        }

        return 0;
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
