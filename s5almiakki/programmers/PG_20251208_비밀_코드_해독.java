import java.util.*;

public class PG_20251208_비밀_코드_해독 {

    class Solution {

        private int maxNum;
        private int[][] queries;
        private int[] answers;
        private Set<Integer> chosenNums = new HashSet<>();

        private int caseCount = 0;

        public int solution(int n, int[][] q, int[] ans) {
            this.maxNum = n;
            this.queries = q;
            this.answers = ans;

            dfs(0, 1);
            return caseCount;
        }

        private void dfs(int depth, int startNum) {
            if (depth == 5) {
                if (isCandidate()) {
                    caseCount++;
                }
                return;
            }

            int endNum = maxNum - 4 + depth;
            for (int num = startNum; num <= endNum; num++) {
                Integer element = num;
                chosenNums.add(element);
                dfs(depth + 1, num + 1);
                chosenNums.remove(element);
            }
        }

        private boolean isCandidate() {
            for (int i = 0; i < queries.length; i++) {
                int count = 0;
                for (int element : queries[i]) {
                    if (chosenNums.contains(element)) {
                        count++;
                    }
                }
                if (count != answers[i]) {
                    return false;
                }
            }
            return true;
        }

    }

}
