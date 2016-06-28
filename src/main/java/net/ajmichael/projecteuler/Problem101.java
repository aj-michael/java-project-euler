package net.ajmichael.projecteuler;

import Jama.Matrix;
import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;

import java.util.List;
import java.util.stream.Collectors;

public class Problem101 {
  public static long solve() {
    long total = 0;
    for (int i = 1; i < 11; i++) {
      long[] values = seqOf(i);
      total += evaluate(fitPolynomial(values));
    }
    return total;
  }

  private static long evaluate(List<Long> polynomial) {
    int x = polynomial.size() + 1;
    long total = 0;
    for (long coeff : polynomial) {
      total = x * total + coeff;
    }
    return total;
  }

  private static List<Long> fitPolynomial(long[] values) {
    int n = values.length;
    double[][] a = new double[n][n];
    double[][] output = new double[n][1];
    for (int x = 1; x <= n; x++) {
      output[x-1][0] = values[x-1];
      int entry = 1;
      for (int col = 0; col < n; col++, entry = entry * x) {
        a[x-1][col] = entry;
      }
    }
    double[] x = new Matrix(a).solve(new Matrix(output)).transpose().getArray()[0];
    return Lists.reverse(Doubles.asList(x))
        .stream()
        .map(d -> Math.round(d))
        .collect(Collectors.toList());
  }

  private static long[] seqOf(int n) {
    long[] seq = new long[n];
    for (int i = 0; i < n; i++) {
      seq[i] = seq(i+1);
    }
    return seq;
  }

  private static long seq(int n) {
    long total = 0;
    for (int i = 0; i < 11; i++) {
      total += Math.pow(-1 * n, i);
    }
    return total;
  }
}
