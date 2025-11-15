import java.util.*;

public class PG_20251115_우박수열_정적분 {

    class Solution {

        public double[] solution(int k, int[][] ranges) {
            List<Long> seq = new ArrayList<>();
            long a = k;
            seq.add(a);
            while (a != 1L) {
                if ((a & 1L) == 0L) {
                    a >>= 1L;
                } else {
                    a = (a * 3L) + 1L;
                }
                seq.add(a);
            }
            // System.out.println("seq: " + seq);

            double[] sums = new double[seq.size()];
            for (int i = 1; i < sums.length; i++) {
                long prevA = seq.get(i - 1);
                long currentA = seq.get(i);
                sums[i] = Math.min(prevA, currentA)
                        + Math.abs(prevA - currentA) * 0.5;
            }
            // System.out.println("sums: " + Arrays.toString(sums));
            for (int i = 1; i < sums.length; i++) {
                sums[i] += sums[i - 1];
            }
            // System.out.println("sums: " + Arrays.toString(sums));

            double[] result = new double[ranges.length];
            for (int i = 0; i < result.length; i++) {
                if (ranges[i][0] < 0 || sums.length <= ranges[i][0]) {
                    result[i] = -1;
                    continue;
                }
                int endIdx = sums.length + ranges[i][1] - 1;
                if (ranges[i][0] > endIdx) {
                    result[i] = -1;
                    continue;
                }
                result[i] = sums[endIdx] - sums[ranges[i][0]];
            }
            return result;
        }

    }

}
