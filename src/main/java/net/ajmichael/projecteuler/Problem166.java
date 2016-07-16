package net.ajmichael.projecteuler;

public class Problem166 {
  public static long solve() {
    int[][] values = new int[4][4];
    long total = 0;
    // 0 is the same as 36, 1 as 35, etc.
    for (int sum = 0; sum <= 17; sum++) {
      total += 2 * waysToFill(values, sum, 0, 0);
    }
    total += waysToFill(values, 18, 0, 0);
    return total;
  }

  private static long waysToFill(int[][] values, int sum, int r, int c) {
    int existingRowSum = 0;
    for (int c2 = 0; c2 < c; c2++) {
      existingRowSum += values[r][c2];
    }
    int maxValueForRow = sum - existingRowSum;
    int minValueForRow = maxValueForRow - (values[r].length - c - 1) * 9;
    int existingColSum = 0;
    for (int r2 = 0; r2 < r; r2++) {
      existingColSum += values[r2][c];
    }
    int maxValueForCol = sum - existingColSum;
    int minValueForCol = maxValueForCol - (values.length - r - 1) * 9;
    int maxValueForDiag = 9;
    int minValueForDiag = 0;
    if (r == c) {
      int existingDiagSum = 0;
      for (int i = 0; i < r; i++) {
        existingDiagSum += values[i][i];
      }
      maxValueForDiag = sum - existingDiagSum;
      minValueForDiag = maxValueForDiag - (values.length - r - 1) * 9;
    } else if (values.length - 1 - r == c) {
      int existingDiagSum = 0;
      for (int i = 0; i < r; i++) {
        existingDiagSum += values[i][values[i].length - 1 - i];
      }
      maxValueForDiag = sum - existingDiagSum;
      minValueForDiag = maxValueForDiag - (values.length - r - 1) * 9;
    }
    int minValue = Math.max(Math.max(minValueForRow, minValueForCol), Math.max(minValueForDiag, 0));
    int maxValue = Math.min(Math.min(maxValueForRow, maxValueForCol), Math.min(maxValueForDiag, 9));
    if (r + 1 == values.length && c + 1 == values[r].length) {
      return minValue == maxValue ? 1 : 0;
    } else {
      long total = 0;
      int nextR = c + 1 == values[r].length ? r + 1 : r;
      int nextC = (c + 1) % values[r].length;
      for (int value = minValue; value <= maxValue; value++) {
        values[r][c] = value;
        total += waysToFill(values, sum, nextR, nextC);
      }
      values[r][c] = -1;
      return total;
    }
  }
}
