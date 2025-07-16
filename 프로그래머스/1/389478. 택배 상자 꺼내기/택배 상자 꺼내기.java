class Solution {
    public int solution(int n, int w, int num) {
        int depth = 0;

        int startRow = (num % w == 0) ? (num / w - 1) : (num / w);
        int originalParity = startRow % 2;

        int base = ((num + w - 1) / w) * w;
        int forwardStep = (base - num) * 2 + 1;

        while (num <= n) {
            depth++;

            if (startRow % 2 == originalParity) {
                num += forwardStep;
            } else {
                num += (w * 2) - forwardStep;
            }

            startRow++;
        }

        return depth;
    }
}