import java.util.*;

public class PG_20251211_후보키 {

    class Solution {

        public int solution(String[][] relation) {
            int columnCount = relation[0].length;
            Set<Integer> chosenColumns = new HashSet<>();
            Set<Set<Integer>> candidateKeys = new HashSet<>();
            for (int targetChoiceCount = 1; targetChoiceCount <= columnCount; targetChoiceCount++) {
                // System.out.println("targetChoiceCount: " + targetChoiceCount);
                dfs(relation, targetChoiceCount, chosenColumns, 0, candidateKeys);
                // System.out.println();
            }
            return candidateKeys.size();
        }

        private void dfs(
                String[][] relation,
                int targetChoiceCount,
                Set<Integer> chosenColumns,
                int beginIdx,
                Set<Set<Integer>> candidateKeys
                ) {
            // StringBuilder sb = new StringBuilder();
            // for (int i = chosenColumns.size() - 1; i >= 0; i--) {
            //     sb.append("  ");
            // }
            // String indentation = sb.toString();
            // System.out.println(indentation + chosenColumns);

            if (targetChoiceCount == chosenColumns.size()) {
                // System.out.println(
                //     indentation + "  " +
                //     (isCandidateKey(relation, chosenColumns) ? "" : "not ") +
                //     "candidate key"
                // );
                if (hasUniqueness(relation, chosenColumns)) {
                    candidateKeys.add(new HashSet<>(chosenColumns));
                }
                return;
            }

            int endIdx = relation[0].length - targetChoiceCount + chosenColumns.size();
            for (int i = beginIdx; i <= endIdx; i++) {
                Integer element = i;
                chosenColumns.add(element);
                if (hasMinimality(candidateKeys, chosenColumns)) {
                    dfs(relation, targetChoiceCount, chosenColumns, i + 1, candidateKeys);
                }
                chosenColumns.remove(element);
            }
        }

        private boolean hasUniqueness(String[][] relation, Set<Integer> chosenColumns) {
            Set<List<String>> subRows = new HashSet<>();
            for (String[] row : relation) {
                List<String> subRow = new ArrayList<>();
                for (int chosenColumn : chosenColumns) {
                    subRow.add(row[chosenColumn]);
                }
                if (subRows.contains(subRow)) {
                    return false;
                }
                subRows.add(subRow);
            }
            return true;
        }

        private boolean hasMinimality(Set<Set<Integer>> candidateKeys, Set<Integer> chosenColumns) {
            for (Set<Integer> candidateKey : candidateKeys) {
                if (chosenColumns.containsAll(candidateKey)) {
                    return false;
                }
            }
            return true;
        }

    }

}
