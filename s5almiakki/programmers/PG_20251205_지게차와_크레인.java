import java.util.*;

public class PG_20251205_지게차와_크레인 {

    class Solution {

        private static final char EMPTY = '\0';
        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        public int solution(String[] storage, String[] requests) {
            char[][] grid = new char[storage.length + 2][storage[0].length() + 2];
            for (int i = 0; i < storage.length; i++) {
                for (int j = 0; j < storage[0].length(); j++) {
                    grid[i + 1][j + 1] = storage[i].charAt(j);
                }
            }

            int answer = storage.length * storage[0].length();
            for (String request : requests) {
                if (request.length() == 1) {
                    answer -= removeWithForkLift(grid, request.charAt(0));
                } else {
                    answer -= removeWithCrane(grid, request.charAt(0));
                }
            }
            return answer;
        }

        private int removeWithForkLift(char[][] grid, char target) {
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            queue.add(new int[] { 0, 0 });
            visited[0][0] = true;
            int removalCount = 0;
            do {
                int[] currentPoint = queue.remove();
                for (int[] direction : DIRECTIONS) {
                    int newRow = currentPoint[0] + direction[0];
                    int newCol = currentPoint[1] + direction[1];
                    if (isOutOfBounds(grid, newRow, newCol) || visited[newRow][newCol]) {
                        continue;
                    }
                    if (grid[newRow][newCol] == target) {
                        grid[newRow][newCol] = EMPTY;
                        removalCount++;
                    } else if (grid[newRow][newCol] == EMPTY) {
                        queue.add(new int[] { newRow, newCol });
                    }
                    visited[newRow][newCol] = true;
                }
            } while (!queue.isEmpty());
            return removalCount;
        }

        private boolean isOutOfBounds(char[][] grid, int row, int col) {
            return row < 0 || grid.length <= row
                    || col < 0 || grid[row].length <= col;
        }

        private int removeWithCrane(char[][] grid, char target) {
            List<int[]> removedPoints = new ArrayList<>();
            for (int row = 1; row < grid.length - 1; row++) {
                for (int col = 1; col < grid[row].length - 1; col++) {
                    if (grid[row][col] == target) {
                        removedPoints.add(new int[] { row, col });
                    }
                }
            }
            for (int[] removedPoint : removedPoints) {
                grid[removedPoint[0]][removedPoint[1]] = EMPTY;
            }
            return removedPoints.size();
        }

    }

}
