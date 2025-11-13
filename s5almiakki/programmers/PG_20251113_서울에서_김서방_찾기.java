
public class PG_20251113_서울에서_김서방_찾기 {

    class Solution {

        public String solution(String[] seoul) {
            int answer = -1;
            for (int i = 0; i < seoul.length; i++) {
                if ("Kim".equals(seoul[i])) {
                    answer = i;
                    break;
                }
            }
            return "김서방은 " + answer + "에 있다";
        }

    }

}
