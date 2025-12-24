
public class PG_20251113_콜라츠_추측 {

    class Solution {

        public int solution(int num) {
            int answer = 0;
            long n = num;
            while (n != 1) {
                if (n % 2 == 0) {
                    n >>= 1;
                } else {
                    n = n * 3 + 1;
                }
                answer++;
                if (answer == 500) {
                    return -1;
                }
            }
            return answer;
        }

    }

}
