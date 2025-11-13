import java.util.*;

public class PG_20251113_나누어_떨어지는_숫자_배열 {

    class Solution1 {

        public int[] solution(int[] arr, int divisor) {
            int[] result = Arrays.stream(arr)
                    .filter(i -> i % divisor == 0)
                    .sorted()
                    .toArray();
            return result.length == 0
                    ? new int[] { -1 }
                    : result;
        }

    }

    class Solution2 {

        public int[] solution(int[] arr, int divisor) {
            int[] result = new int[arr.length];
            int resultLength = 0;
            for (int dividend : arr) {
                if (dividend % divisor == 0) {
                    result[resultLength++] = dividend;
                }
            }
            if (resultLength == 0) {
                return new int[] { -1 };
            }
            int[] answer = Arrays.copyOf(result, resultLength);
            Arrays.sort(answer);
            return answer;
        }

    }

}
