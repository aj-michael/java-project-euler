package net.ajmichael.projecteuler;

final class Problem179 {
  static long solve() {
    int CUTOFF = 10000000;
    int[] divisors = new int[CUTOFF + 1];
    for (int i = 1; i <= CUTOFF; i++) {
      for (int m = 1; i * m <= CUTOFF; m++) {
        divisors[m * i] = divisors[m * i] + 1;
      }
    }
    int total = 0;
    for (int n = 2; n < CUTOFF; n++) {
      if (divisors[n] == divisors[n+1]) {
        total++;
      }
    }
    return total;
  }
}
