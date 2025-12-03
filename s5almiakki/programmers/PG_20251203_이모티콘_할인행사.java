
public class PG_20251203_이모티콘_할인행사 {

    class Solution {

        private int[][] users;
        private int[] emoticons;
        private int[] effectiveRates;
        private int[] answer = new int[2];

        public int[] solution(int[][] users, int[] emoticons) {
            this.users = users;
            this.emoticons = emoticons;
            this.effectiveRates = new int[emoticons.length];
            dfs(0);
            return answer;
        }

        private void dfs(int depth) {
            if (depth == emoticons.length) {
                computeAnswer();
                return;
            }
            for (int effectiveRate = 60; effectiveRate <= 90; effectiveRate += 10) {
                effectiveRates[depth] = effectiveRate;
                dfs(depth + 1);
            }
        }

        private void computeAnswer() {
            int plusMemberCount = 0;
            int totalSpentMoney = 0;
            for (int[] user : users) {
                int spentMoney = 0;
                for (int i = 0; i < emoticons.length; i++) {
                    if (user[0] > 100 - effectiveRates[i]) {
                        continue;
                    }
                    spentMoney += emoticons[i] * effectiveRates[i] / 100;
                }
                if (spentMoney < user[1]) {
                    totalSpentMoney += spentMoney;
                } else {
                    plusMemberCount++;
                }
            }
            if (answer[0] < plusMemberCount) {
                answer[0] = plusMemberCount;
                answer[1] = totalSpentMoney;
            } else if (answer[0] == plusMemberCount && answer[1] < totalSpentMoney) {
                answer[1] = totalSpentMoney;
            }
        }

    }

}
