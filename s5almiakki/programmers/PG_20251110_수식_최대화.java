import java.util.*;

public class PG_20251110_수식_최대화 {

    class Solution {

        private static final String[] OPERATOR_ORDERS = {
                "+-*", "+*-", "-+*", "-*+", "*+-", "*-+"
        };

        public long solution(String expression) {
            List<String> splittedExpr = splitExpression(expression);
            long answer = Long.MIN_VALUE;
            for (String operatorOrder : OPERATOR_ORDERS) {
                answer = Math.max(answer,
                        Math.abs(evaluate(new LinkedList<>(splittedExpr), operatorOrder)));
            }
            return answer;
        }

        private List<String> splitExpression(String expression) {
            List<String> splittedExpr = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < expression.length(); i++) {
                char letter = expression.charAt(i);
                if (letter != '+' && letter != '-' && letter != '*') {
                    sb.append(letter);
                    continue;
                }
                splittedExpr.add(sb.toString());
                sb.delete(0, sb.length());
                splittedExpr.add(String.valueOf(letter));
            }
            splittedExpr.add(sb.toString());
            return splittedExpr;
        }

        private long evaluate(List<String> splittedExpr, String operatorOrder) {
            for (int operatorIdx = 0; operatorIdx < 3; operatorIdx++) {
                String operator = String.valueOf(operatorOrder.charAt(operatorIdx));
                for (int exprIdx = 0; exprIdx < splittedExpr.size(); exprIdx++) {
                    if (!splittedExpr.get(exprIdx).equals(operator)) {
                        continue;
                    }
                    long result = calculate(
                            Long.parseLong(splittedExpr.get(exprIdx - 1)),
                            operator,
                            Long.parseLong(splittedExpr.get(exprIdx + 1)));
                    exprIdx--;
                    for (int i = 0; i < 3; i++) {
                        splittedExpr.remove(exprIdx);
                    }
                    splittedExpr.add(exprIdx, String.valueOf(result));
                }
            }
            return Long.parseLong(splittedExpr.get(0));
        }

        private long calculate(long operand1, String operator, long operand2) {
            switch (operator) {
                case "+":
                    return operand1 + operand2;
                case "-":
                    return operand1 - operand2;
                case "*":
                    return operand1 * operand2;
            }
            return 0L;
        }

    }

}
