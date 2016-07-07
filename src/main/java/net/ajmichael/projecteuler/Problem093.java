package net.ajmichael.projecteuler;

import java.util.HashSet;
import java.util.Set;

public class Problem093 {
  public static long solve() {
    int N = 10;
    int maxLength = 0;
    int[] maxLetters = new int[0];
    for (int a = 0; a < N; a++) {
      for (int b = a + 1; b < N; b++) {
        for (int c = b + 1; c < N; c++) {
          for (int d = c + 1; d < N; d++) {
            int[] things = {a, b, c, d};
            Set<Integer> values = new HashSet<>();
            {
              for (Operation first : Operation.values()) {
                for (Operation second : Operation.values()) {
                  for (Operation third : Operation.values()) {
                    for (int a1 = 0; a1 < 4; a1++) {
                      for (int a2 = 0; a2 < 4; a2++) {
                        if (a1 == a2) {
                          continue;
                        }
                        for (int a3 = 0; a3 < 4; a3++) {
                          if (a1 == a3 || a2 == a3) {
                            continue;
                          }
                          for (int a4 = 0; a4 < 4; a4++) {
                            if (a1 == a4 || a2 == a4 || a3 == a4) {
                              continue;
                            }
                            try {
                              double value = third.apply(
                                  second.apply(
                                      first.apply(things[a1], things[a2]),
                                      things[a3]),
                                  things[a4]);
                              int intValue = (int) Math.round(value);
                              if (Math.abs(intValue - value) < 0.01) {
                                if (value > 0) {
                                  values.add(intValue);
                                }
                              }
                            } catch (IllegalIntegerDivisionException e) {
                            }
                            try {
                              double value = second.apply(
                                  first.apply(things[a1], things[a2]),
                                  third.apply(things[a3], things[a4]));
                              int intValue = (int) Math.round(value);
                              if (Math.abs(intValue - value) < 0.01) {
                                if (value > 0) {
                                  values.add(intValue);
                                }
                              }
                            } catch (IllegalIntegerDivisionException e) {
                            }
                            try {
                              double value = third.apply(
                                  first.apply(
                                      things[a1],
                                      second.apply(things[a2], things[a3])),
                                  things[a4]);
                              int intValue = (int) Math.round(value);
                              if (Math.abs(intValue - value) < 0.01) {
                                if (value > 0) {
                                  values.add(intValue);
                                }
                              }
                            } catch (IllegalIntegerDivisionException e) {
                            }
                            try {
                              double value = first.apply(
                                  things[a1],
                                  second.apply(things[a2],
                                      third.apply(things[a3], things[a4])));
                              int intValue = (int) Math.round(value);
                              if (Math.abs(intValue - value) < 0.01) {
                                if (value > 0) {
                                  values.add(intValue);
                                }
                              }
                            } catch (IllegalIntegerDivisionException e) {
                            }
                            try {
                              double value = first.apply(
                                  things[a1],
                                  third.apply(
                                      second.apply(things[a2], things[a3]),
                                      things[a4]
                                  ));
                              int intValue = (int) Math.round(value);
                              if (Math.abs(intValue - value) < 0.01) {
                                if (value > 0) {
                                  values.add(intValue);
                                }
                              }
                            } catch (IllegalIntegerDivisionException e) {
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
            int i = 1;
            while (values.contains(i)) {
              i++;
            }
            if (i - 1 > maxLength) {
              maxLength = i - 1;
              maxLetters = things;
            }
          }
        }
      }
    }
    return 1000 * maxLetters[0] + 100 * maxLetters[1] + 10 * maxLetters[2] + maxLetters[3];
  }

  enum Operation {
    PLUS {
      double apply(double a, double b) {
        return a + b;
      }
    },
    MULTIPLY {
      double apply(double a, double b) {
        return a * b;
      }
    },
    SUBTRACT {
      double apply(double a, double b) {
        return a - b;
      }
    },
    DIVIDE {
      double apply(double a, double b) {
        if (b != 0) {
          return a / b;
        } else {
          throw new IllegalIntegerDivisionException();
        }
      }
    };

    abstract double apply(double a, double b);
  }

  static class IllegalIntegerDivisionException extends RuntimeException {
  }
}
