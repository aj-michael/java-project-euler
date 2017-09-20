package net.ajmichael.projecteuler;

import static java.lang.Math.log;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

final class Problem267 {

  public static void main(String[] args) {
    System.out.println(solve());
  }

  static BigDecimal solve() {
    /*
     * final = (1 + 2f)^X * (1 - f)^(N-X)
     * X has a binomial distribution
     * P(final > goal) = P(X > k) for some k
     *
     * 1000000000 = (1+2f)^k * (1-f)^(1000-k)
     * log(1000000000) = k*log(1+2f) + (1000-k)log(1-f)
     * log(1000000000) - 1000log(1-f) = (log(1+2f) - log(1-f)) * k
     * k = (log(1000000000) - 1000log(1-f)) / (log(1+2f) - log(1-f))
     *
     * What value of f minimizes X? Using Wolfram Alpha,
     * f = 0.146883922440941
     */
    double f = 0.146883922440941;
    int k = (int) Math.ceil((log(1000000000) - 1000*log(1-f)) / (log(1+2 * f) - log(1-f)));

    // We need the sum of (1000, k) to (1000, 1000)
    return probAtLeast(1000, k);
  }

  private static BigDecimal probAtLeast(int n, int r) {
    BigInteger total = BigInteger.ONE;
    for (int i = r; i <= n; i++) {
      total = total.add(binomialCoef(n, i));
    }
    return new BigDecimal(total).divide(new BigDecimal(2).pow(n), 12, BigDecimal.ROUND_HALF_UP);
  }

  private static Map<Long, BigInteger> coefMap = new HashMap<>();
  private static BigInteger binomialCoef(long n, long r) {
    if (r > n - r) {
      r = n - r;
    }
    if (coefMap.containsKey(r)) {
      return coefMap.get(r);
    }
    BigInteger coef = BigInteger.ONE;
    for (long i = 1, m = n; i <= r; i++, m--) {
      coef = coef.multiply(BigInteger.valueOf(m)).divide(BigInteger.valueOf(i));
      coefMap.put(i, coef);
    }
    return coef;
  }
}
