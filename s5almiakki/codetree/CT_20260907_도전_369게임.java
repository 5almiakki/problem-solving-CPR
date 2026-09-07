import java.io.*;

public class CT_20260907_도전_369게임 {

	public class Main {

		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String input = br.readLine();
			int digitCount = input.length();
			long divisor = 1_000_000_007L;
			long[] power10 = new long[digitCount];
			power10[0] = 1L;
			for (int i = 1; i < digitCount; i++) {
				power10[i] = (power10[i - 1] * 10L) % divisor;
			}

			long answer = 0L;
			int inputDigit = input.charAt(0) - '0';
			boolean multiple3Present = isMultiple3(inputDigit);
			int digitSum = inputDigit;
			long[][] dp = new long[digitCount][3];
			for (int digit = 0; digit < inputDigit; digit++) {
				if (isMultiple3(digit)) {
					answer = (answer + power10[digitCount - 1]) % divisor;
				} else {
					dp[0][digit % 3]++;
				}
			}
			for (int idx = 1; idx < digitCount; idx++) {
				for (int digit = 0; digit <= 9; digit++) {
					if (isMultiple3(digit)) {
						long temp = (dp[idx - 1][0] + dp[idx - 1][1] + dp[idx - 1][2]) % divisor;
						answer = (answer + temp * power10[digitCount - 1 - idx]) % divisor;
						continue;
					}
					for (int prevRemainder = 0; prevRemainder < 3; prevRemainder++) {
						int remainder = (prevRemainder + digit) % 3;
						dp[idx][remainder] = (dp[idx][remainder] + dp[idx - 1][prevRemainder]) % divisor;
					}
				}
				inputDigit = input.charAt(idx) - '0';
				for (int digit = 0; digit < inputDigit; digit++) {
					if (multiple3Present || isMultiple3(digit)) {
						answer = (answer + power10[digitCount - 1 - idx]) % divisor;
					} else {
						dp[idx][(digitSum + digit) % 3] = (dp[idx][(digitSum + digit) % 3] + 1L) % divisor;
					}
				}
				if (isMultiple3(inputDigit)) {
					multiple3Present = true;
				} else {
					digitSum += inputDigit;
				}
			}
			if (multiple3Present) {
				answer = (answer + 1L) % divisor;
			} else {
				dp[digitCount - 1][digitSum % 3] = (dp[digitCount - 1][digitSum % 3] + 1L) % divisor;
			}
			System.out.print((answer + dp[digitCount - 1][0] + divisor - 1L) % divisor);
		}

		static boolean isMultiple3(int n) {
			return n > 0 && n % 3 == 0;
		}

	}

}
