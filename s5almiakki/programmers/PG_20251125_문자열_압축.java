
public class PG_20251125_문자열_압축 {

    class Solution {

        private static final int[] NUMBER_COUNTS = new int[1001];
        static {
            for (int i = 1; i < 10; i++) {
                NUMBER_COUNTS[i] = 1;
            }
            for (int i = 10; i < 100; i++) {
                NUMBER_COUNTS[i] = 2;
            }
            for (int i = 100; i < 1000; i++) {
                NUMBER_COUNTS[i] = 3;
            }
            NUMBER_COUNTS[1000] = 4;
        }

        private int answer;

        public int solution(String s) {
            answer = s.length();
            for (int blockLength = 1; blockLength < s.length(); blockLength++) {
                computeCompressedStringLength(s, blockLength);
            }
            return answer;
        }

        private void computeCompressedStringLength(String s, int blockLength) {
            int result = 0;
            int blockCount = 0;
            String prevBlock = s.substring(0, blockLength);
            StringBuilder currentBlock = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                currentBlock.append(s.charAt(i));
                if (currentBlock.length() != blockLength) {
                    continue;
                }
                if (currentBlock.toString().equals(prevBlock)) {
                    blockCount++;
                    currentBlock.delete(0, blockLength);
                    continue;
                }
                if (blockCount > 1) {
                    result += computeNumberCount(blockCount);
                }
                result += blockLength;
                if (answer <= result) {
                    return;
                }
                blockCount = 1;
                prevBlock = currentBlock.toString();
                currentBlock.delete(0, blockLength);
            }
            if (blockCount > 1) {
                result += computeNumberCount(blockCount);
            }
            result += blockLength + currentBlock.length();
            if (answer > result) {
                answer = result;
            }
        }

        private int computeNumberCount(int i) {
            return NUMBER_COUNTS[i];
        }

    }

}
