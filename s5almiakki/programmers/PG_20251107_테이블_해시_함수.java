import java.util.*;

public class PG_20251107_테이블_해시_함수 {

    class Solution {

        public int solution(int[][] data, int col, int row_begin, int row_end) {
            Arrays.sort(data, (o1, o2) -> {
                if (o1[col - 1] == o2[col - 1]) {
                    return Integer.compare(o2[0], o1[0]);
                }
                return Integer.compare(o1[col - 1], o2[col - 1]);
            });
            int[] sis = new int[data.length];
            for (int i = 0; i < data.length; i++) {
                int divisor = i + 1;
                for (int cell : data[i]) {
                    sis[i] += cell % divisor;
                }
            }
            int answer = 0;
            for (int i = row_begin - 1; i < row_end; i++) {
                answer ^= sis[i];
            }
            return answer;
        }

    }

}
