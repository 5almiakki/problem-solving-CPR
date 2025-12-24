
public class PG_20251209_공_이동_시뮬레이션 {

    class Solution {

        private static final int LEFT = 0;
        private static final int RIGHT = 1;
        private static final int UP = 2;
        private static final int DOWN = 3;

        public long solution(int n, int m, int x, int y, int[][] queries) {
            int minRow = 0;
            int minCol = 0;
            int maxRow = n - 1;
            int maxCol = m - 1;
            int currentMinRow = x;
            int currentMinCol = y;
            int currentMaxRow = x;
            int currentMaxCol = y;
            for (int i = queries.length - 1; i >= 0; i--) {
                // System.out.println("xyxy: " + currentMinRow + ", " + currentMinCol + ", " + currentMaxRow
                //         + ", " + currentMaxCol);
                switch (queries[i][0]) {
                    case LEFT:
                        // System.out.print("left ");
                        currentMaxCol = Math.min(maxCol, currentMaxCol + queries[i][1]);
                        if (currentMinCol != minCol) {
                            currentMinCol += queries[i][1];
                            if (currentMinCol > maxCol) {
                                return 0L;
                            }
                        }
                        break;
                    case RIGHT:
                        // System.out.print("right ");
                        currentMinCol = Math.max(minCol, currentMinCol - queries[i][1]);
                        if (currentMaxCol != maxCol) {
                            currentMaxCol -= queries[i][1];
                            if (currentMaxCol < minCol) {
                                return 0L;
                            }
                        }
                        break;
                    case UP:
                        // System.out.print("up ");
                        currentMaxRow = Math.min(maxRow, currentMaxRow + queries[i][1]);
                        if (currentMinRow != minRow) {
                            currentMinRow += queries[i][1];
                            if (currentMinRow > maxRow) {
                                return 0L;
                            }
                        }
                        break;
                    case DOWN:
                        // System.out.print("down ");
                        currentMinRow = Math.max(minRow, currentMinRow - queries[i][1]);
                        if (currentMaxRow != maxRow) {
                            currentMaxRow -= queries[i][1];
                            if (currentMaxRow < minRow) {
                                return 0L;
                            }
                        }
                        break;
                }
                // System.out.println(queries[i][1]);
            }
            return (1L + currentMaxRow - currentMinRow) * (1L + currentMaxCol - currentMinCol);
        }

    }

}
