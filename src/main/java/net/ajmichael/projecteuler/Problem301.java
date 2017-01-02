package net.ajmichael.projecteuler;

import com.google.common.math.IntMath;

import java.util.stream.IntStream;

final class Problem301 {
  static long solve() {
    return IntStream.range(1, IntMath.pow(2, 30) + 1)
        .filter(n -> (n ^ 2 * n ^ 3 * n) == 0)
        .count();
  }
}
