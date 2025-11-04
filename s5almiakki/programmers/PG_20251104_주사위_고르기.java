import java.util.*;

public class PG_20251104_주사위_고르기 {

    class Solution {

        private List<Map<Integer, Integer>> dices = new ArrayList<>();
        private int diceCount;
        private int answerMask;
        private int maxWinningCaseCount = 0;
        private Set<Integer> checkedDiceIndicesMasks = new HashSet<>();

        public int[] solution(int[][] dice) {
            for (int[] d : dice) {
                Map<Integer, Integer> numToFreqMap = new HashMap<>();
                for (int num : d) {
                    numToFreqMap.put(num, numToFreqMap.getOrDefault(num, 0) + 1);
                }
                dices.add(numToFreqMap);
            };
            diceCount = dices.size();
            dfs(0, 0, 0);
            int[] answer = new int[diceCount >> 1];
            int answerIdx = 0;
            for (int i = 0; i < diceCount; i++) {
                int mask = 1 << i;
                if ((answerMask & mask) != 0) {
                    answer[answerIdx] = i + 1;
                    answerIdx++;
                }
            }
            return answer;
        }

        private void dfs(int chosenDiceIndicesMask, int choiceCount, int startDiceIdx) {
            if (checkedDiceIndicesMasks.contains(chosenDiceIndicesMask)) {
                return;
            }
            checkedDiceIndicesMasks.add(chosenDiceIndicesMask);
            int unchosenDiceIndicesMask = chosenDiceIndicesMask;
            for (int i = 0; i < diceCount; i++) {
                unchosenDiceIndicesMask ^= 1 << i;
            }
            checkedDiceIndicesMasks.add(unchosenDiceIndicesMask);
            if (choiceCount == diceCount >> 1) {
                computeCaseCount(chosenDiceIndicesMask, unchosenDiceIndicesMask);
                return;
            }
            for (int diceIdx = startDiceIdx; diceIdx < diceCount; diceIdx++) {
                int diceIdxMask = 1 << diceIdx;
                dfs(chosenDiceIndicesMask | diceIdxMask, choiceCount + 1, diceIdx + 1);
            }
        }

        private void computeCaseCount(int chosenDiceIndicesMask, int unchosenDiceIndicesMask) {
            List<Map<Integer, Integer>> chosenDices = new ArrayList<>();
            List<Map<Integer, Integer>> unchosenDices = new ArrayList<>();
            for (int diceIdx = 0; diceIdx < diceCount; diceIdx++) {
                int diceIdxMask = 1 << diceIdx;
                if ((chosenDiceIndicesMask & diceIdxMask) != 0) {
                    chosenDices.add(dices.get(diceIdx));
                } else {
                    unchosenDices.add(dices.get(diceIdx));
                }
            }
            Map<Integer, Integer> sumToCountMap1 = new HashMap<>();
            Map<Integer, Integer> sumToCountMap2 = new HashMap<>();
            computeCountsBySums(sumToCountMap1, chosenDices, new ArrayList<>());
            computeCountsBySums(sumToCountMap2, unchosenDices, new ArrayList<>());
            int winCount1 = 0;
            int winCount2 = 0;
            for (Map.Entry<Integer, Integer> entry1 : sumToCountMap1.entrySet()) {
                for (Map.Entry<Integer, Integer> entry2 : sumToCountMap2.entrySet()) {
                    int key1 = entry1.getKey();
                    int key2 = entry2.getKey();
                    int count = entry1.getValue() * entry2.getValue();
                    if (key1 > key2) {
                        winCount1 += count;
                    } else if (key1 < key2) {
                        winCount2 += count;
                    }
                }
            }
            int targetWinCount;
            int targetMask;
            if (winCount1 > winCount2) {
                targetWinCount = winCount1;
                targetMask = chosenDiceIndicesMask;
            } else {
                targetWinCount = winCount2;
                targetMask = unchosenDiceIndicesMask;
            }
            if (maxWinningCaseCount < targetWinCount) {
                maxWinningCaseCount = targetWinCount;
                answerMask = targetMask;
            }
        }

        private void computeCountsBySums(
                Map<Integer, Integer> result,
                List<Map<Integer, Integer>> dices,
                List<Map.Entry<Integer, Integer>> chosenEntries
                ) {
            if (chosenEntries.size() == dices.size()) {
                int sum = 0;
                int caseCount = 1;
                for (Map.Entry<Integer, Integer> entry : chosenEntries) {
                    sum += entry.getKey();
                    caseCount *= entry.getValue();
                }
                result.put(sum, result.getOrDefault(sum, 0) + caseCount);
                return;
            }
            for (Map.Entry<Integer, Integer> entry : dices.get(chosenEntries.size()).entrySet()) {
                chosenEntries.add(entry);
                computeCountsBySums(result, dices, chosenEntries);
                chosenEntries.remove(chosenEntries.size() - 1);
            }
        }

    }

}
