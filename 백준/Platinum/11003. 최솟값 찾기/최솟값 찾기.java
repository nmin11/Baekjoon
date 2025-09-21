import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        public int value;
        public int index;

        Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Deque<Node> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            int cur = Integer.parseInt(st.nextToken());
            while (!deque.isEmpty() && deque.getLast().value > cur) deque.removeLast();
            deque.addLast(new Node(cur, i));
            if (deque.getFirst().index <= i - l) deque.removeFirst();
            bw.write(deque.getFirst().value + " ");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}