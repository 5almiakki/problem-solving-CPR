import java.util.*;

public class PG_20251104_정수_내림차순으로_배치하기 {

    class Solution {

        public long solution(long n) {
            char[] c = String.valueOf(n).toCharArray();
            Arrays.sort(c);
            long answer = 0;
            for (int i = c.length - 1; i >= 0; i--) {
                answer *= 10;
                answer += c[i] - '0';
            }
            return answer;
        }

    }

}
