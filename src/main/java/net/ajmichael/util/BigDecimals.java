package net.ajmichael.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimals {
  private BigDecimals() {
  }

  private static final BigDecimal SQRT_DIG = new BigDecimal(1000);
  private static final BigDecimal SQRT_PRE = new BigDecimal(10).pow(SQRT_DIG.intValue());

  private static BigDecimal sqrtNewton(BigDecimal c, BigDecimal xn, BigDecimal precision) {
    BigDecimal fx = xn.pow(2).add(c.negate());
    BigDecimal fpx = xn.multiply(new BigDecimal(2));
    BigDecimal xn1 = fx.divide(fpx, 2 * SQRT_DIG.intValue(), RoundingMode.HALF_DOWN);
    xn1 = xn.add(xn1.negate());
    BigDecimal currentSquare = xn1.pow(2);
    BigDecimal currentPrecision = currentSquare.subtract(c);
    currentPrecision = currentPrecision.abs();
    if (currentPrecision.compareTo(precision) <= -1) {
      return xn1;
    }
    return sqrtNewton(c, xn1, precision);
  }

  public static BigDecimal sqrt(BigDecimal c) {
    return sqrtNewton(c, new BigDecimal(1), new BigDecimal(1).divide(SQRT_PRE));
  }
}
