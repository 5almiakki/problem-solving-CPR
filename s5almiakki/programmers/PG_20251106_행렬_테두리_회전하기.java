import java.util.*;

public class PG_20251106_행렬_테두리_회전하기 {

    class Solution {

        private static final int[][] DIRECTIONS = {
                { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }
        };

        public int[] solution(int rows, int columns, int[][] queries) {
            int[][] matrix = new int[rows][columns];
            int cellValue = 1;
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < columns; col++) {
                    matrix[row][col] = cellValue++;
                }
            }
            int[] answer = new int[queries.length];
            int answerIdx = 0;
            Queue<Integer> queue = new ArrayDeque<>();
            for (int[] query : queries) {
                for (int i = 0; i < query.length; i++) {
                    query[i]--;
                }
                addAllToQueue(matrix, queue, query[0], query[1], query[2], query[3]);
                answer[answerIdx++] = putAllToMatrix(matrix, queue, query[0], query[1], query[2], query[3]);
            }
            return answer;
        }

        private void addAllToQueue(
                int[][] matrix,
                Queue<Integer> queue,
                int startRow,
                int startCol,
                int endRow,
                int endCol
                ) {
            int row = startRow;
            int col = startCol;
            queue.add(matrix[row][col]);
            int direction = 0;
            for (;;) {
                row += DIRECTIONS[direction][0];
                col += DIRECTIONS[direction][1];
                if (row == startRow && col == startCol) {
                    break;
                }
                queue.add(matrix[row][col]);
                if (isCorner(row, col, startRow, startCol, endRow, endCol)) {
                    direction++;
                }
            }
        }

        private int putAllToMatrix(
                int[][] matrix,
                Queue<Integer> queue,
                int startRow,
                int startCol,
                int endRow,
                int endCol
                ) {
            int minCellValue = Integer.MAX_VALUE;
            int row = startRow;
            int col = startCol + 1;
            matrix[row][col] = queue.remove();
            minCellValue = Math.min(minCellValue, matrix[row][col]);
            int direction = 0;
            for (;;) {
                if (isCorner(row, col, startRow, startCol, endRow, endCol)) {
                    direction++;
                }
                row += DIRECTIONS[direction][0];
                col += DIRECTIONS[direction][1];
                matrix[row][col] = queue.remove();
                minCellValue = Math.min(minCellValue, matrix[row][col]);
                if (row == startRow && col == startCol) {
                    break;
                }
            }
            return minCellValue;
        }

        private boolean isCorner(int row, int col, int startRow, int startCol, int endRow, int endCol) {
            return (row == startRow || row == endRow) && (col == startCol || col == endCol);
        }

    }

}
