import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        TrieNode root = new TrieNode();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            insert(root, word);
        }

        int count = 0;
        for (int i = 0; i < m; i++) {
            String word = br.readLine();
            if (search(root, word)) {
                count++;
            }
        }

        System.out.println(count);
    }

    private static void insert(TrieNode root, String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';

            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }

            cur = cur.children[index];
        }

        cur.isEndOfWord = true;
    }

    private static boolean search(TrieNode root, String word) {
        TrieNode cur = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';

            if (cur.children[index] == null) {
                return false;
            }

            cur = cur.children[index];
        }

        return cur.isEndOfWord;
    }

    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }
}
