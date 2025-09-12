import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] checkArr;
    static int[] curArr;
    static int checkSecret;
    
    private static void addCount(char c) {
        switch (c) {
            case 'A':
                curArr[0]++;
                if (curArr[0] == checkArr[0]) checkSecret++;
                break;
            case 'C':
                curArr[1]++;
                if (curArr[1] == checkArr[1]) checkSecret++;
                break;
            case 'G':
                curArr[2]++;
                if (curArr[2] == checkArr[2]) checkSecret++;
                break;
            case 'T':
                curArr[3]++;
                if (curArr[3] == checkArr[3]) checkSecret++;
                break;
        }
    }
    
    private static void minusCount(char c) {
        switch (c) {
            case 'A':
                if (curArr[0] == checkArr[0]) checkSecret--;
                curArr[0]--;
                break;
            case 'C':
                if (curArr[1] == checkArr[1]) checkSecret--;
                curArr[1]--;
                break;
            case 'G':
                if (curArr[2] == checkArr[2]) checkSecret--;
                curArr[2]--;
                break;
            case 'T':
                if (curArr[3] == checkArr[3]) checkSecret--;
                curArr[3]--;
                break;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[] dna = br.readLine().toCharArray();
        
        int count = 0;
        checkArr = new int[4];
        curArr = new int[4];
        checkSecret = 0;
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) {
                checkSecret++;
            }
        }
        
        for (int i = 0; i < m; i++) {
            addCount(dna[i]);
        }
        
        if (checkSecret == 4) count++;
        
        for (int i = m; i < n; i++) {
            int j = i - m;
            addCount(dna[i]);
            minusCount(dna[j]);
            if (checkSecret == 4) count++;
        }
        
        System.out.println(count);
        
        br.close();
    }
}