import java.io.*;
import java.util.*;

public class CT_20260904_회전_마법진 {

	public class Main {


		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int seqLength = Integer.parseInt(br.readLine());
			int[] seq = new int[seqLength];
			int[] targetSeq = new int[seqLength];
			String input1 = br.readLine();
			String input2 = br.readLine();
			seq[0] = input1.charAt(0) - '0';
			targetSeq[0] = input2.charAt(0) - '0';
			for (int i = 1; i < seqLength; i++) {
				seq[i] = input1.charAt(i) - '0';
				targetSeq[i] = input2.charAt(i) - '0';
			}

			int[][] dp = new int[seqLength][10]; // dp[depth][incrementAmount] = minTotalRotationCount
			for (int[] i : dp) {
				Arrays.fill(i, Integer.MAX_VALUE);
			}
			dp[0][(targetSeq[0] - seq[0] + 10) % 10] = (targetSeq[0] - seq[0] + 10) % 10;
			dp[0][0] = (seq[0] - targetSeq[0] + 10) % 10;
			for (int depth = 1; depth < seqLength; depth++) {
				for (int prevIncAmt = 0; prevIncAmt < 10; prevIncAmt++) {
					if (dp[depth - 1][prevIncAmt] == Integer.MAX_VALUE) {
						continue;
					}
					int currentElement = (seq[depth] + prevIncAmt) % 10;
					// clockwise - decrement
					int rotationCount = (currentElement - targetSeq[depth] + 10) % 10;
					int newTotalRotationCount = dp[depth - 1][prevIncAmt] + rotationCount;
					dp[depth][prevIncAmt] = Math.min(dp[depth][prevIncAmt], newTotalRotationCount);
					// counter-clockwise - increment
					rotationCount = (targetSeq[depth] - currentElement + 10) % 10;
					int newIncAmt = (prevIncAmt + rotationCount) % 10;
					newTotalRotationCount = dp[depth - 1][prevIncAmt] + rotationCount;
					dp[depth][newIncAmt] = Math.min(dp[depth][newIncAmt], newTotalRotationCount);
				}
			}
			int answer = Integer.MAX_VALUE;
			for (int i : dp[seqLength - 1]) {
				answer = Math.min(answer, i);
			}
			System.out.print(answer);
		}

	}

}
