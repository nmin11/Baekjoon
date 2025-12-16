import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Edge(to, weight));
        }

        List<PriorityQueue<Integer>> distQueue = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            distQueue.add(new PriorityQueue<>(Collections.reverseOrder()));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        distQueue.get(1).offer(0);
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int curNode = cur.to;
            int curWeight = cur.weight;

            for (Edge edge : graph.get(curNode)) {
                int nextNode = edge.to;
                int newWeight = curWeight + edge.weight;

                PriorityQueue<Integer> nextQueue = distQueue.get(nextNode);
                if (nextQueue.size() < k) {
                    nextQueue.offer(newWeight);
                    pq.offer(new Edge(nextNode, newWeight));
                } else if (nextQueue.peek() > newWeight) {
                    nextQueue.poll();
                    nextQueue.offer(newWeight);
                    pq.offer(new Edge(nextNode, newWeight));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            PriorityQueue<Integer> queue = distQueue.get(i);
            if (queue.size() == k) {
                sb.append(queue.peek()).append('\n');
            } else {
                sb.append(-1).append('\n');
            }
        }

        System.out.print(sb);
    }
}
