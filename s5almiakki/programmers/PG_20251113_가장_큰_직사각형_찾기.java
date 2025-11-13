
public class PG_20251113_가장_큰_직사각형_찾기 {

    class Solution {

        public int solution(int[][] board) {
            int answer = board[0][0];
            for (int col = 1; col < board[0].length; col++) {
                answer = Math.max(answer, board[0][col]);
            }
            for (int row = 1; row < board.length; row++) {
                answer = Math.max(answer, board[row][0]);
                for (int col = 1; col < board[row].length; col++) {
                    if (board[row][col] == 0) {
                        continue;
                    }
                    board[row][col] += Math.min(
                            board[row - 1][col - 1],
                            Math.min(board[row - 1][col], board[row][col - 1]));
                    answer = Math.max(answer, board[row][col]);
                }
            }
            return answer * answer;
        }

    }

}
