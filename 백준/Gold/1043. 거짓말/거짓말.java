import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static int[] parent;

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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int insidersCount = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        int[] insiders = new int[insidersCount];
        if (insidersCount > 0) {
            for (int i = 0; i < insidersCount; i++) {
                insiders[i] = Integer.parseInt(st.nextToken());
            }
        }

        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int partySize = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();
            for (int j = 0; j < partySize; j++) {
                party.add(Integer.parseInt(st.nextToken()));
            }
            parties.add(party);
        }

        for (List<Integer> party : parties) {
            if (!party.isEmpty()) {
                int first = party.get(0);
                for (int j = 1; j < party.size(); j++) {
                    union(first, party.get(j));
                }
            }
        }

        int result = 0;
        for (List<Integer> party : parties) {
            boolean canLie = true;

            if (!party.isEmpty()) {
                int partyRoot = find(party.get(0));

                for (int insider : insiders) {
                    if (find(insider) == partyRoot) {
                        canLie = false;
                        break;
                    }
                }
            }

            if (canLie) result++;
        }

        System.out.println(result);
    }
}
