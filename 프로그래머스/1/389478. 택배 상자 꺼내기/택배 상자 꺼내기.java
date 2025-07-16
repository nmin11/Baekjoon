class Solution {
    public int solution(int n, int w, int num) {
        int depth = 0;
        int numRow = (num % w == 0)
            ? (num / w - 1)
            : (num / w);
        int originDirection = numRow % 2;
        int base = ((num + w - 1) / w) * w;
        int step = (base - num) * 2 + 1;

        while (num <= n) {
            depth++;

            if (numRow % 2 == originDirection) {
                num += step;
            } else {
                num += (w * 2) - step;
            }

            numRow++;
        }

        return depth;
    }
}