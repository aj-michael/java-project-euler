package net.ajmichael.projecteuler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class Problem121 {
  public static long solve() {
    BigDecimal total = BigDecimal.ZERO;
    int r = 8;
    int n = 15;
    BigInteger nFact = BigInteger.ONE;
    for (int i = 1; i <= n + 1; i++) {
      nFact = nFact.multiply(BigInteger.valueOf(i));
    }
    MathContext context = new MathContext(100, RoundingMode.HALF_DOWN);
    for (int m = r; m <= n; m++) {
      total = total.add(new BigDecimal(stirling(n + 1, m + 1)).divide(new BigDecimal(nFact), context));
    }
    return BigDecimal.ONE.divide(total, context).longValue();
  }

  private static BigInteger[][] stirling = {{BigInteger.ONE}};

  /**
   * Unsigned Stirling numbers of the first kind.
   */
  private static BigInteger stirling(int n, int k) {
    if ((n > 0 && k == 0) || k > n) {
      return BigInteger.ZERO;
    }
    if (stirling.length <= n) {
      BigInteger[][] newStirling = new BigInteger[n + 1][n + 1];
      for (int i = 0; i <= n; i++) {
        if (i < stirling.length) {
          System.arraycopy(stirling[i], 0, newStirling[i], 0, i + 1);
        } else {
          for (int j = 1; j <= i; j++) {
            if (newStirling[i - 1][j] == null) {
              newStirling[i - 1][j] = BigInteger.ZERO;
            }
            if (newStirling[i - 1][j - 1] == null) {
              newStirling[i - 1][j - 1] = BigInteger.ZERO;
            }
            newStirling[i][j] = newStirling[i - 1][j]
                .multiply(BigInteger.valueOf(i - 1))
                .add(newStirling[i - 1][j - 1]);
          }
        }
      }
      stirling = newStirling;
    }
    return stirling[n][k];
  }
}
