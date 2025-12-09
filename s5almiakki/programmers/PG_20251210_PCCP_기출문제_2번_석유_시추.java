import java.util.*;

public class PG_20251210_PCCP_기출문제_2번_석유_시추 {

    class Solution {

        private static final int[][] DIRECTIONS = {
                { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }
        };

        public int solution(int[][] land) {
            Gasoline[][] gasolines = new Gasoline[land.length][land[0].length];
            int id = 0;
            for (int row = 0; row < land.length; row++) {
                for (int col = 0; col < land[row].length; col++) {
                    if (land[row][col] == 1 && gasolines[row][col] == null) {
                        bfs(land, gasolines, id, row, col);
                        id++;
                    }
                }
            }
            Set<Gasoline> collectedGasolines = new HashSet<>();
            int answer = 0;
            for (int col = 0; col < land[0].length; col++) {
                for (int row = 0; row < land.length; row++) {
                    if (gasolines[row][col] != null) {
                        collectedGasolines.add(gasolines[row][col]);
                    }
                }
                int totalVolume = 0;
                for (Gasoline gasoline : collectedGasolines) {
                    totalVolume += gasoline.volume;
                }
                answer = Math.max(answer, totalVolume);
                collectedGasolines.clear();
            }
            return answer;
        }

        private void bfs(int[][] land, Gasoline[][] gasolines, int id, int beginRow, int beginCol) {
            Queue<int[]> queue = new ArrayDeque<>();
            queue.add(new int[] { beginRow, beginCol });
            Gasoline g = new Gasoline(id, 1);
            gasolines[beginRow][beginCol] = g;
            do {
                int[] point = queue.remove();
                for (int[] direction : DIRECTIONS) {
                    int nextRow = point[0] + direction[0];
                    int nextCol = point[1] + direction[1];
                    if (isOutOfBounds(land, nextRow, nextCol)
                            || land[nextRow][nextCol] == 0
                            || gasolines[nextRow][nextCol] != null) {
                        continue;
                    }
                    queue.add(new int[] { nextRow, nextCol });
                    gasolines[nextRow][nextCol] = g;
                    g.volume++;
                }
            } while (!queue.isEmpty());
        }

        private boolean isOutOfBounds(int[][] land, int row, int col) {
            return row < 0 || land.length <= row
                    || col < 0 || land[row].length <= col;
        }

        private static class Gasoline {

            final int id;
            int volume;

            public Gasoline(int id, int volume) {
                this.id = id;
                this.volume = volume;
            }

            @Override
            public int hashCode() {
                return Objects.hash(id, volume);
            }

            @Override
            public boolean equals(Object o) {
                if (!(o instanceof Gasoline)) {
                    return false;
                }
                if (this == o) {
                    return true;
                }
                Gasoline other = (Gasoline) o;
                return this.id == other.id && this.volume == other.volume;
            }

        }

    }

}
