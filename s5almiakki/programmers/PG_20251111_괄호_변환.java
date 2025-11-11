
public class PG_20251111_괄호_변환 {

    class Solution {

        public String solution(String p) {
            return makeValid(p).toString();
        }

        private CharSequence makeValid(CharSequence w) {
            if (w.length() == 0) {
                return w;
            }
            StringBuilder u = new StringBuilder();
            StringBuilder v = new StringBuilder(w).reverse();
            for (int i = 0; i < w.length(); i++) {
                u.append(v.charAt(v.length() - 1));
                v.deleteCharAt(v.length() - 1);
                if (isBalanced(u)) {
                    break;
                }
            }
            v.reverse();
            if (isValid(u)) {
                return u.toString() + makeValid(v);
            }
            StringBuilder result = new StringBuilder();
            result.append('(')
                    .append(makeValid(v))
                    .append(')');
            StringBuilder newU = new StringBuilder();
            for (int i = 1; i < u.length() - 1; i++) {
                newU.append(u.charAt(i) == '(' ? ')' : '(');
            }
            return result.append(newU);
        }

        private boolean isBalanced(CharSequence cs) {
            int leftCount = 0;
            int rightCount = 0;
            for (int i = 0; i < cs.length(); i++) {
                if (cs.charAt(i) == '(') {
                    leftCount++;
                } else {
                    rightCount++;
                }
            }
            return leftCount == rightCount;
        }

        private boolean isValid(CharSequence cs) {
            int leftCount = 0;
            for (int i = 0; i < cs.length(); i++) {
                if (cs.charAt(i) == '(') {
                    leftCount++;
                } else {
                    if (leftCount == 0) {
                        return false;
                    }
                    leftCount--;
                }
            }
            return leftCount == 0;
        }

    }

}
