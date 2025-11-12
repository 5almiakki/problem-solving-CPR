import java.util.*;

public class PG_20251112_거리두기_확인하기 {

    class Solution {

        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            for (int placeIdx = 0; placeIdx < places.length; placeIdx++) {
                // System.out.println("place: " + placeIdx);
                answer[placeIdx] = checkPlace(places[placeIdx]);
            }
            return answer;
        }

        private int checkPlace(String[] place) {
            Queue<int[]> queue = new ArrayDeque<>();
            for (int row = 0; row < 5; row++) {
                for (int col = 0; col < 5; col++) {
                    if (place[row].charAt(col) == 'P' && !isSafe(place, row, col, queue)) {
                        return 0;
                    }
                }
            }
            return 1;
        }

        private boolean isSafe(String[] place, int startRow, int startCol, Queue<int[]> queue) {
            // System.out.println("  start: " + startRow + ", " + startCol);
            boolean[][] visited = new boolean[5][5];
            queue.add(new int[] { startRow, startCol, 0 });
            visited[startRow][startCol] = true;
            do {
                int[] currentPoint = queue.remove();
                // System.out.println("  current: " + currentPoint[0] + ", " + currentPoint[1]);
                for (int[] direction : DIRECTIONS) {
                    int newRow = currentPoint[0] + direction[0];
                    int newCol = currentPoint[1] + direction[1];
                    if (isOutOfBounds(newRow, newCol) || visited[newRow][newCol]) {
                        continue;
                    }
                    char newCell = place[newRow].charAt(newCol);
                    if (newCell == 'X') {
                        continue;
                    }
                    if (newCell == 'P') {
                        queue.clear();
                        // System.out.println("  false");
                        return false;
                    }
                    if (currentPoint[2] == 0) {
                        queue.add(new int[] { newRow, newCol, currentPoint[2] + 1 });
                        visited[newRow][newCol] = true;
                    }
                }
            } while (!queue.isEmpty());
            queue.clear();
            // System.out.println("  true");
            return true;
        }

        private boolean isOutOfBounds(int row, int col) {
            return row < 0 || 5 <= row
                    || col < 0 || 5 <= col;
        }

    }

}
