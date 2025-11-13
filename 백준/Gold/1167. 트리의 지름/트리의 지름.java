import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<List<Edge>> graph;
    static int[] distance;
    static boolean[] visited;

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Edge edge : graph.get(cur)) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    queue.offer(edge.to);
                    distance[edge.to] = distance[cur] + edge.weight;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());

            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if (end == -1) break;
                int weight = Integer.parseInt(st.nextToken());
                graph.get(start).add(new Edge(end, weight));
            }
        }

        distance = new int[n + 1];
        visited = new boolean[n + 1];
        bfs(1);

        int farthestNode = 1;
        for (int i = 2; i <= n; i++) {
            if (distance[farthestNode] < distance[i]) farthestNode = i;
        }

        distance = new int[n + 1];
        visited = new boolean[n + 1];
        bfs(farthestNode);

        Arrays.sort(distance);
        System.out.println(distance[n]);
    }
}