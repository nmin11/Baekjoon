import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] capacity = new int[3];
    static boolean[][] visited;
    static boolean[] possible;
    static int[] sender = {0, 0, 1, 1, 2, 2};
    static int[] receiver = {1, 2, 0, 2, 0, 1};

    static class State {
        int a, b;

        State(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private static void bfs() {
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(0, 0));
        visited[0][0] = true;
        possible[capacity[2]] = true;

        while (!queue.isEmpty()) {
            State cur = queue.poll();
            int a = cur.a;
            int b = cur.b;
            int c = capacity[2] - a - b;

            for (int i = 0; i < 6; i++) {
                int[] next = {a, b, c};
                next[receiver[i]] += next[sender[i]];
                next[sender[i]] = 0;

                if (next[receiver[i]] > capacity[receiver[i]]) {
                    next[sender[i]] = next[receiver[i]] - capacity[receiver[i]];
                    next[receiver[i]] = capacity[receiver[i]];
                }

                if (!visited[next[0]][next[1]]) {
                    visited[next[0]][next[1]] = true;
                    queue.offer(new State(next[0], next[1]));

                    if (next[0] == 0) {
                        possible[next[2]] = true;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        capacity[0] = Integer.parseInt(st.nextToken());
        capacity[1] = Integer.parseInt(st.nextToken());
        capacity[2] = Integer.parseInt(st.nextToken());

        visited = new boolean[201][201];
        possible = new boolean[201];

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= capacity[2]; i++) {
            if (possible[i]) {
                sb.append(i).append(' ');
            }
        }

        System.out.print(sb);
    }
}
