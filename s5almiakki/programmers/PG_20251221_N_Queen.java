import java.util.*;

public class PG_20251221_N_Queen {

    class Solution1 {

        private int boardSize;
        private int answer = 0;

        public int solution(int n) {
            boardSize = n;
            dfs(new ArrayList<>());
            return answer;
        }

        private void dfs(List<Integer> columns) {
            if (columns.size() == boardSize) {
                answer++;
                return;
            }
            int newRow = columns.size();
            for (int newColumn = 0; newColumn < boardSize; newColumn++) {
                boolean canAttack = false;
                for (int row = 0; row < columns.size(); row++) {
                    if (canAttack(row, columns.get(row), newRow, newColumn)) {
                        canAttack = true;
                        break;
                    }
                }
                if (canAttack) {
                    continue;
                }
                columns.add(newColumn);
                dfs(columns);
                columns.remove(columns.size() - 1);
            }
        }

        private boolean canAttack(int sRow, int sColumn, int oRow, int oColumn) {
            return sRow == oRow || sColumn == oColumn
                    || Math.abs(sRow - oRow) == Math.abs(sColumn - oColumn);
        }

    }

    class Solution2 {

        private int boardSize;
        private boolean[] colUsed;
        private boolean[] ruDiagonalUsed;
        private boolean[] rdDiagonalUsed;

        private int answer = 0;

        public int solution(int n) {
            boardSize = n;
            colUsed = new boolean[n];
            ruDiagonalUsed = new boolean[(n << 1) - 1];
            rdDiagonalUsed = new boolean[(n << 1) - 1];
            dfs(0);
            return answer;
        }

        private void dfs(int row) {
            if (row == boardSize) {
                answer++;
                return;
            }
            for (int col = 0; col < boardSize; col++) {
                if (colUsed[col]
                        || ruDiagonalUsed[computeRuIdx(row, col)]
                        || rdDiagonalUsed[computeRdIdx(row, col)]) {
                    continue;
                }
                incrementUsedCount(row, col, true);
                dfs(row + 1);
                incrementUsedCount(row, col, false);
            }
        }

        private int computeRuIdx(int row, int col) {
            return row + col;
        }

        private int computeRdIdx(int row, int col) {
            return boardSize + row - col - 1;
        }

        private void incrementUsedCount(int row, int col, boolean used) {
            colUsed[col] = used;
            ruDiagonalUsed[computeRuIdx(row, col)] = used;
            rdDiagonalUsed[computeRdIdx(row, col)] = used;
        }

    }

}
