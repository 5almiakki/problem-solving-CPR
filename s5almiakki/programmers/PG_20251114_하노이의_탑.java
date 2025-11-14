
public class PG_20251114_하노이의_탑 {

    class Solution1 {

        private static final int[] CONVERTER1 = { 0, 1, 3, 2 };
        private static final int[] CONVERTER2 = { 0, 2, 1, 3 };

        public int[][] solution(int n) {
            int[][] previousMovements = { { 1, 3 } };
            for (int plateCount = 2; plateCount <= n; plateCount++) {
                int[][] currentMovements = new int[1 + (previousMovements.length << 1)][2];
                for (int i = 0; i < previousMovements.length; i++) {
                    currentMovements[i][0] = CONVERTER1[previousMovements[i][0]];
                    currentMovements[i][1] = CONVERTER1[previousMovements[i][1]];
                }
                currentMovements[previousMovements.length][0] = 1;
                currentMovements[previousMovements.length][1] = 3;
                for (int i = 0; i < previousMovements.length; i++) {
                    int idx = i + previousMovements.length + 1;
                    currentMovements[idx][0] = CONVERTER2[previousMovements[i][0]];
                    currentMovements[idx][1] = CONVERTER2[previousMovements[i][1]];
                }
                previousMovements = currentMovements;
            }
            return previousMovements;
        }

    }

    class Solution2 {

        private static final int[] CONVERTER1 = { 0, 1, 3, 2 };
        private static final int[] CONVERTER2 = { 0, 2, 3, 1 };

        public int[][] solution(int n) {
            int[][] result = new int[(1 << n) - 1][2];
            for (int plateCount = 1; plateCount <= n; plateCount++) {
                int mid = (1 << (plateCount - 1)) - 1;
                for (int i = 0; i < mid; i++) {
                    result[i][0] = CONVERTER1[result[i][0]];
                    result[i][1] = CONVERTER1[result[i][1]];
                }
                result[mid][0] = 1;
                result[mid][1] = 3;
                for (int i = 0; i < mid; i++) {
                    result[i + mid + 1][0] = CONVERTER2[result[i][0]];
                    result[i + mid + 1][1] = CONVERTER2[result[i][1]];
                }
            }
            return result;
        }

    }

}
