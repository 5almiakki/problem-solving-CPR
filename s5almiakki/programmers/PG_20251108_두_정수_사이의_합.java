
public class PG_20251108_두_정수_사이의_합 {

    class Solution1 {

        public long solution(int a, int b) {
            int start = Math.min(a, b);
            int end = Math.max(a, b);
            long answer = 0;
            for (int i = start; i <= end; i++) {
                answer += i;
            }
            return answer;
        }

    }

    class Solution2 {

        public long solution(int a, int b) {
            return ((Math.abs(a - b) + 1L) * (a + b)) >> 1L;
        }

    }

}
