
public class PG_20251105_정수_제곱근_판별 {

    class Solution {

        public long solution(long n) {
            long root = 1L;
            long square = 1L;
            while (square <= n) {
                if (square == n) {
                    root++;
                    return root * root;
                }
                root++;
                square = root * root;
            }
            return -1L;
        }

    }

}
