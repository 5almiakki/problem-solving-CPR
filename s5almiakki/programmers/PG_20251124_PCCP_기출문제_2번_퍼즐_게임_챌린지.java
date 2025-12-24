
public class PG_20251124_PCCP_기출문제_2번_퍼즐_게임_챌린지 {

    class Solution {

        public int solution(int[] diffs, int[] times, long limit) {
            int low = 1;
            int high = diffs[0];
            long totalTime = times[0];
            for (int i = 1; i < diffs.length; i++) {
                high = Math.max(high, diffs[i]);
                totalTime += times[i];
            }

            int answer = high;
            do {
                int mid = (high + low) >> 1;
                // System.out.print("low: " + low + ", high: " + high + ", mid: " + mid + " - ");
                if (canSolveAll(totalTime, diffs, times, limit, mid)) {
                    // System.out.println("can");
                    answer = mid;
                    high = mid;
                } else {
                    // System.out.println("cannot");
                    low = mid + 1;
                }
            } while (high > low);
            return answer;
        }

        private boolean canSolveAll(long totalTime, int[] diffs, int[] times, long limit, int level) {
            long duration = totalTime;
            for (int puzzle = 1; puzzle < diffs.length; puzzle++) {
                if (level >= diffs[puzzle]) {
                    continue;
                }
                duration += (diffs[puzzle] - level) * (times[puzzle] + times[puzzle - 1]);
                if (duration > limit) {
                    return false;
                }
            }
            return true;
        }

    }


}
