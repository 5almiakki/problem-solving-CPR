
public class PG_20251115_가운데_글자_가져오기 {

    class Solution {

        public String solution(String s) {
            int beginIdx = ((s.length() + 1) >> 1) - 1;
            int endIdx = ((s.length() + 2) >> 1);
            return s.substring(beginIdx, endIdx);
        }

    }

}
