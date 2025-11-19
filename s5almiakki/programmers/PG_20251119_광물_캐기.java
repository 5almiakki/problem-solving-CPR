
public class PG_20251119_광물_캐기 {

    class Solution {

        private static final int[][] FATIGUE_TABLE = {
                { 1, 1, 1 }, { 5, 1, 1 }, { 25, 5, 1 }
        };

        private int totalPickCount = 0;
        private int answer = Integer.MAX_VALUE;

        public int solution(int[] picks, String[] minerals) {
            for (int pick : picks) {
                totalPickCount += pick;
            }
            int[] mineralNums = new int[minerals.length];
            for (int i = 0; i < minerals.length; i++) {
                switch (minerals[i]) {
                    case "diamond":
                        mineralNums[i] = 0;
                        break;
                    case "iron":
                        mineralNums[i] = 1;
                        break;
                    case "stone":
                        mineralNums[i] = 2;
                        break;
                }
            }
            int[][] fatiguesByMinerals = new int[(minerals.length - 1) / 5 + 1][3];
            for (int base = 0; base < minerals.length; base += 5) {
                for (int offset = 0; offset < 5; offset++) {
                    if (base + offset == minerals.length) {
                        break;
                    }
                    for (int pick = 0; pick < 3; pick++) {
                        fatiguesByMinerals[base / 5][pick] += FATIGUE_TABLE[pick][mineralNums[base + offset]];
                    }
                }
            }

            dfs(picks, fatiguesByMinerals, 0, 0);
            return answer;
        }

        private void dfs(int[] picks, int[][] fatiguesByMinerals, int depth, int fatigueSum) {
            if (depth == fatiguesByMinerals.length || depth == totalPickCount) {
                answer = Math.min(answer, fatigueSum);
                return;
            }
            for (int pick = 0; pick < picks.length; pick++) {
                if (picks[pick] == 0) {
                    continue;
                }
                picks[pick]--;
                dfs(picks, fatiguesByMinerals, depth + 1, fatigueSum + fatiguesByMinerals[depth][pick]);
                picks[pick]++;
            }
        }

    }

}
