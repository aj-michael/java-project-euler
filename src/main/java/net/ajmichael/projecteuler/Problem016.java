package net.ajmichael.projecteuler;

import java.math.BigInteger;

public class Problem016 {

  public static void main(String args[]) {
    System.out.println(solve());
  }

  public static int solve() {
    BigInteger two = BigInteger.valueOf(2L);
    BigInteger x = BigInteger.valueOf(1L);
    for (int i = 0; i < 1000; i++) {
      x = x.multiply(two);
    }
    int digitSum = 0;
    for (char c : x.toString().toCharArray()) {
      digitSum += Character.getNumericValue(c);
    }
    return digitSum;
  }
}
