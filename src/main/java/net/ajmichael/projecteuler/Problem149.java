package net.ajmichael.projecteuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem149 {
  public static long solve() {
    long[][] matrix = matrix();
    long max = 0;
    for (int i = 0; i < matrix.length; i++) {
      List<Long> row = Arrays.stream(matrix[i]).boxed().collect(Collectors.toList());
      long rowMax = maxSubsequence(row);
      max = Math.max(max, rowMax);
    }
    for (int i = 0; i < matrix.length; i++) {
      List<Long> col = new ArrayList<>();
      for (int j = 0; j < matrix.length; j++) {
        col.add(matrix[j][i]);
      }
      long colMax = maxSubsequence(col);
      max = Math.max(max, colMax);
    }
    for (int i = 0; i < matrix.length; i++) {
      List<Long> diag1 = new ArrayList<>();
      List<Long> diag2 = new ArrayList<>();
      List<Long> diag3 = new ArrayList<>();
      List<Long> diag4 = new ArrayList<>();
      for (int j = 0; i + j < matrix.length; j++) {
        diag1.add(matrix[i+j][j]);
        diag2.add(matrix[j][i+j]);
        diag3.add(matrix[matrix.length-1-j][i+j]);
        diag4.add(matrix[matrix.length-1-i-j][j]);
      }
      long diag1Max = maxSubsequence(diag1);
      long diag2Max = maxSubsequence(diag2);
      long diag3Max = maxSubsequence(diag3);
      long diag4Max = maxSubsequence(diag4);
      max = Math.max(max, diag1Max);
      max = Math.max(max, diag2Max);
      max = Math.max(max, diag3Max);
      max = Math.max(max, diag4Max);
    }
    return max;
  }

  private static long maxSubsequence(List<Long> vals) {
    long maxSoFar = 0;
    long currentRun = 0;
    for (long x : vals) {
      long newRun = currentRun + x;
      if (newRun > maxSoFar) {
        maxSoFar = newRun;
      }
      currentRun = Math.max(0, newRun);
    }
    return maxSoFar;
  }

  private static long[][] testMatrix() {
    long[][] vals = {{-2, 5, 3, 2}, {9, -6, 5, 1}, {3, 2, 7, 3}, {-1, 8, -4, 8}};
    return vals;
  }

  private static long[][] matrix() {
    long[][] matrix = new long[2000][2000];
    long[] s = new long[4000000];
    for (long k = 1; k <= 55; k++) {
      long value = (100003 - 200003*k + 300007*k*k*k) % 1000000 - 500000;
      s[(int) (k-1)] = value;
    }
    for (long k = 56; k <= 4000000; k++) {
      s[(int) (k-1)] = (s[(int) (k-25)] + s[(int) (k-56)] + 1000000) % 1000000 - 500000;
    }
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        matrix[i][j] = s[2000*i+j];
      }
    }
    return matrix;
  }
}
