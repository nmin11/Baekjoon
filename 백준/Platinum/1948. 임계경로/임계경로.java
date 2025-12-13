import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Edge {
        int to;
        int time;

        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] indegree = new int[n + 1];

        List<List<Edge>> graph = new ArrayList<>();
        List<List<Edge>> reverseGraph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.get(from).add(new Edge(to, time));
            reverseGraph.get(to).add(new Edge(from, time));
            indegree[to]++;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        int[] maxTime = new int[n + 1];
        queue.offer(start);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Edge edge : graph.get(cur)) {
                indegree[edge.to]--;
                maxTime[edge.to] = Math.max(maxTime[edge.to], maxTime[cur] + edge.time);

                if (indegree[edge.to] == 0) queue.offer(edge.to);
            }
        }

        int criticalEdgeCount = 0;
        boolean[] visited = new boolean[n + 1];
        queue.offer(end);
        visited[end] = true;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Edge edge : reverseGraph.get(cur)) {
                if (maxTime[edge.to] + edge.time == maxTime[cur]) {
                    criticalEdgeCount++;

                    if (!visited[edge.to]) {
                        visited[edge.to] = true;
                        queue.offer(edge.to);
                    }
                }
            }
        }

        System.out.println(maxTime[end]);
        System.out.println(criticalEdgeCount);
    }
}
