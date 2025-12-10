
public class PG_20251210_점_찍기 {

    class Solution {

        public long solution(int k, int d) {
            long ld = d;
            long answer = 0L;
            for (long x = 0L; x <= ld; x += k) {
                answer += (long) Math.sqrt(-x * x + ld * ld) / k + 1L;
                // System.out.println("x: " + x + ", y: " + Math.sqrt(-x * x + ld * ld));
            }
            return answer;
        }

        // x^2 + y^2 = d^2
        // y^2 = -x^2 + d^2
        // y = sqrt(-x^2 + d^2)

    }

}
