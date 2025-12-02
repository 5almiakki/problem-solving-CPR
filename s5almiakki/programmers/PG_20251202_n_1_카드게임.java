
public class PG_20251202_n_1_카드게임 {

    class Solution {

        public int solution(int coin, int[] cards) {
            int targetSum = cards.length + 1;
            int initialCardCount = cards.length / 3;
            boolean[] holdingCards = new boolean[targetSum];
            int playableCardCount = 0;
            for (int i = 0; i < initialCardCount; i++) {
                holdingCards[cards[i]] = true;
                int pairCard = targetSum - cards[i];
                if (holdingCards[pairCard]) {
                    playableCardCount += 2;
                }
            }

            boolean[] tempCards = new boolean[targetSum];
            int tempPlayableCardCount = 0;
            int answer = 1;
            for (int base = initialCardCount; base < cards.length; base += 2) {
                for (int offset = 0; offset < 2; offset++) {
                    int card = cards[base + offset];
                    int pairCard = targetSum - card;

                    if (holdingCards[pairCard] && coin > 0) {
                        holdingCards[card] = true;
                        playableCardCount += 2;
                        coin--;
                    } else if (tempCards[pairCard]) {
                        tempPlayableCardCount += 2;
                    } else {
                        tempCards[card] = true;
                    }
                }

                if (playableCardCount == 0) {
                    if (coin < 2 || tempPlayableCardCount == 0) {
                        break;
                    }
                    coin -= 2;
                    tempPlayableCardCount -= 2;
                    playableCardCount += 2;
                }

                playableCardCount -= 2;
                answer++;
            }
            return answer;
        }

    }

}
