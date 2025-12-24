import java.util.*;

public class PG_20251127_멀쩡한_사각형 {

    class Solution1 {

        public long solution(int w, int h) {
            boolean[] isPrime = new boolean[w + 1];
            Arrays.fill(isPrime, true);
            int unitW = w;
            int unitH = h;
            int ratio = 1;
            for (int i = 2; i < isPrime.length; i++) {
                if (!isPrime[i]) {
                    continue;
                }
                for (int j = i << 1; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
                while (unitW % i == 0 && unitH % i == 0) {
                    unitW /= i;
                    unitH /= i;
                    ratio *= i;
                }
            }
            return (long) w * (long) h - w - h + ratio;
        }

    }

    class Solution2 {

        private static final Set<Integer> PRIMES = new HashSet<>();
        private static final boolean[] isPrime = new boolean[10_000_001];

        static {
            Arrays.fill(isPrime, true);
            for (int i = 2; i < isPrime.length; i++) {
                if (!isPrime[i]) {
                    continue;
                }
                PRIMES.add(i);
                for (int j = i << 1; j < isPrime.length; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        public long solution(int w, int h) {
            int unitW = w;
            int unitH = h;
            int ratio = 1;
            for (int prime : PRIMES) {
                while (unitW % prime == 0 && unitH % prime == 0) {
                    unitW /= prime;
                    unitH /= prime;
                    ratio *= prime;
                }
            }
            return (long) w * (long) h - w - h + ratio;
        }

    }

    class Solution3 {

        public long solution(int w, int h) {
            if (w < h) {
                int t = w;
                w = h;
                h = t;
            }
            return (long) w * (long) h - w - h + gcd(w, h);
        }

        private int gcd(int a, int b) {
            int r = a % b;
            if (r == 0) {
                return b;
            }
            return gcd(b, r);
        }

    }

}
