
public class PG_20251109_없는_숫자_더하기 {

    class Solution {

        public int solution(int[] numbers) {
            int answer = 45;
            for (int number : numbers) {
                answer -= number;
            }
            return answer;
        }

    }

}
