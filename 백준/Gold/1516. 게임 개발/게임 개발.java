import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        int[] indegree = new int[n + 1];
        int[] buildTime = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            buildTime[i] = Integer.parseInt(st.nextToken());

            while (true) {
                int prerequisite = Integer.parseInt(st.nextToken());
                if (prerequisite == -1) break;

                graph.get(prerequisite).add(i);
                indegree[i]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] totalTime = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
                totalTime[i] = buildTime[i];
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int next : graph.get(cur)) {
                indegree[next]--;
                totalTime[next] = Math.max(totalTime[next], totalTime[cur] + buildTime[next]);

                if (indegree[next] == 0) queue.offer(next);
            }
        }

        for (int i = 1; i <= n; i++) {
            sb.append(totalTime[i]).append('\n');
        }

        System.out.print(sb);
    }
}
