import java.util.*;

public class PG_20251207_과제_진행하기 {

    class Solution {

        public String[] solution(String[][] plans) {
            Assignment[] assignments = new Assignment[plans.length];
            for (int i = 0; i < plans.length; i++) {
                assignments[i] = new Assignment(
                        plans[i][0],
                        toIntTime(plans[i][1]),
                        Integer.parseInt(plans[i][2]));
            }

            String[] answer = new String[plans.length];
            int answerIdx = 0;
            Arrays.sort(assignments, (o1, o2) -> o1.startTime - o2.startTime);
            Deque<Assignment> stack = new ArrayDeque<>();
            for (int i = 0; i < assignments.length - 1; i++) {
                int expectedEndTime = assignments[i].startTime + assignments[i].remainingDuration;
                if (expectedEndTime > assignments[i + 1].startTime) {
                    int interval = assignments[i + 1].startTime - assignments[i].startTime;
                    assignments[i].remainingDuration -= interval;
                    stack.push(assignments[i]);
                    continue;
                }
                answer[answerIdx] = assignments[i].name;
                answerIdx++;
                int currentTime = assignments[i].startTime + assignments[i].remainingDuration;
                while (!stack.isEmpty()) {
                    Assignment undoneAssignment = stack.pop();
                    int interval = assignments[i + 1].startTime - currentTime;
                    if (undoneAssignment.remainingDuration > interval) {
                        undoneAssignment.remainingDuration -= interval;
                        stack.push(undoneAssignment);
                        break;
                    }
                    answer[answerIdx] = undoneAssignment.name;
                    answerIdx++;
                    currentTime += undoneAssignment.remainingDuration;
                }
            }

            Assignment lastAssignment = assignments[assignments.length - 1];
            answer[answerIdx] = lastAssignment.name;
            answerIdx++;
            while (!stack.isEmpty()) {
                Assignment a = stack.pop();
                answer[answerIdx] = a.name;
                answerIdx++;
            }
            return answer;
        }

        private int toIntTime(String s) {
            String[] splitted = s.split(":");
            return Integer.parseInt(splitted[0]) * 60 + Integer.parseInt(splitted[1]);
        }

        private static class Assignment {

            final String name;
            final int startTime;
            int remainingDuration;

            public Assignment(String name, int startTime, int duration) {
                this.name = name;
                this.startTime = startTime;
                this.remainingDuration = duration;
            }

        }

    }

}
