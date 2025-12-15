import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;

    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    private static int dijkstra(List<List<Edge>> graph, int n, int start, int end) {
        int[] distance = new int[n + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.to;
            int curCost = cur.weight;

            if (curCost > distance[curNode]) continue;

            if (curNode == end) break;

            for (Edge edge : graph.get(curNode)) {
                int nextNode = edge.to;
                int newCost = distance[curNode] + edge.weight;

                if (newCost < distance[nextNode]) {
                    distance[nextNode] = newCost;
                    pq.offer(new Edge(nextNode, newCost));
                }
            }
        }

        return distance[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int result = dijkstra(graph, n, start, end);
        System.out.println(result);
    }
}
