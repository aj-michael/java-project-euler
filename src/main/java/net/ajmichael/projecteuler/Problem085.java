package net.ajmichael.projecteuler;

public class Problem085 {
  public static long solve() {
    int bestArea = 0;
    int bestRectangles = 0;
    for (int m = 1; m < Math.pow(8000000, 0.25); m++) {
      int n = (int) Math.sqrt(8000000 / (m*(m+1)));
      int area = n*m;
      int rectangles = n*(n+1)*m*(m+1)/4;
      if (Math.abs(rectangles - 2000000) < Math.abs(bestRectangles - 2000000)) {
        bestRectangles = rectangles;
        bestArea = area;
      }
    }
    return bestArea;
  }
}
