import java.util.*;

public class PG_20251224_요격_시스템 {

    class Solution {

        public int solution(int[][] targets) {
            Arrays.sort(targets, (o1, o2) -> o1[0] - o2[0]);
            int prevS = targets[0][0];
            int prevE = targets[0][1];
            int answer = 1;
            for (int i = 1; i < targets.length; i++) {
                int[] currentTarget = targets[i];
                if (prevE <= currentTarget[0]) {
                    prevS = currentTarget[0];
                    prevE = currentTarget[1];
                    answer++;
                    continue;
                }
                if (prevS < currentTarget[0]) {
                    prevS = currentTarget[0];
                }
                if (currentTarget[1] < prevE) {
                    prevE = currentTarget[1];
                }
            }
            return answer;
        }

    }

}
