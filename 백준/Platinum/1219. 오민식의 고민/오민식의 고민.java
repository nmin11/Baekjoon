import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final long UNREACHABLE = Long.MIN_VALUE;
    static final long INFINITE_PROFIT = Long.MAX_VALUE;

    static class Edge {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, cost));
        }

        long[] cityProfit = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            cityProfit[i] = Long.parseLong(st.nextToken());
        }

        long[] maxProfit = new long[n];
        Arrays.fill(maxProfit, UNREACHABLE);
        maxProfit[start] = cityProfit[start];

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                if (maxProfit[edge.from] == UNREACHABLE) continue;

                long newProfit = maxProfit[edge.from] + cityProfit[edge.to] - edge.cost;
                if (maxProfit[edge.to] < newProfit) {
                    maxProfit[edge.to] = newProfit;
                }
            }
        }

        for (int i = 0; i < 100; i++) {
            for (Edge edge : edges) {
                if (maxProfit[edge.from] == UNREACHABLE) continue;

                if (maxProfit[edge.from] == INFINITE_PROFIT) {
                    maxProfit[edge.to] = INFINITE_PROFIT;
                } else if (maxProfit[edge.to] < maxProfit[edge.from] + cityProfit[edge.to] - edge.cost) {
                    maxProfit[edge.to] = INFINITE_PROFIT;
                }
            }
        }

        if (maxProfit[end] == UNREACHABLE) {
            System.out.println("gg");
        } else if (maxProfit[end] == INFINITE_PROFIT) {
            System.out.println("Gee");
        } else {
            System.out.println(maxProfit[end]);
        }
    }
}
