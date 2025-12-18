import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final long INF = Long.MAX_VALUE;

    static class Edge {
        int from;
        int to;
        int time;

        Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, time));
        }

        long[] distance = new long[n + 1];
        Arrays.fill(distance, INF);
        distance[1] = 0;

        for (int i = 1; i < n; i++) {
            for (Edge edge : edges) {
                if (distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.time) {
                    distance[edge.to] = distance[edge.from] + edge.time;
                }
            }
        }

        boolean hasNegativeCycle = false;
        for (Edge edge : edges) {
            if (distance[edge.from] != INF && distance[edge.to] > distance[edge.from] + edge.time) {
                hasNegativeCycle = true;
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (hasNegativeCycle) {
            sb.append(-1);
        } else {
            for (int i = 2; i <= n; i++) {
                if (distance[i] == INF) {
                    sb.append(-1).append('\n');
                } else {
                    sb.append(distance[i]).append('\n');
                }
            }
        }

        System.out.print(sb);
    }
}
