
public class PG_20251104_하샤드_수 {

    class Solution {

        public boolean solution(int x) {
            int xCopy = x;
            int divisor = 0;
            while (xCopy > 0) {
                divisor += xCopy % 10;
                xCopy /= 10;
            }
            return x % divisor == 0;
        }

    }

}
