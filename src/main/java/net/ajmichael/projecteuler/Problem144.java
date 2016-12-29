package net.ajmichael.projecteuler;

final class Problem144 {
  static int solve() {
    Point p = new Point(0.0, 10.1);
    Point nextP = new Point(1.4, -9.6);
    int bounceCount = 0;
    while (!escapes(nextP)) {
      bounceCount++;
      Point nextNextP = bounce(p, nextP);
      p = nextP;
      nextP = nextNextP;
    }
    return bounceCount;
  }

  private static Point bounce(Point lastP, Point p) {
    double oldM = slope(lastP, p);
    double m = bounceSlope(oldM, p);
    double b = p.y - m * p.x;
    double nextX1 = (-2*m*b + Math.sqrt(4*m*m*b*b-4*(4+m*m)*(b*b-100)))/(8+2*m*m);
    double nextX2 = (-2*m*b - Math.sqrt(4*m*m*b*b-4*(4+m*m)*(b*b-100)))/(8+2*m*m);
    double nextX;
    if (Math.abs(nextX1 - p.x) > Math.abs(nextX2 - p.x)) {
      nextX = nextX1;
    } else {
      nextX = nextX2;
    }
    double nextY = m * nextX + b;
    return new Point(nextX, nextY);
  }

  private static double bounceSlope(double oldM, Point p) {
    double tangentM = tangentSlope(p);
    return angleToSlope(Math.PI - slopeToAngle(oldM) + 2 * slopeToAngle(tangentM));
  }

  private static double tangentSlope(Point p) {
    return -4 * p.x / p.y;
  }

  private static double slopeToAngle(double m) {
    return Math.atan(m);
  }

  private static double angleToSlope(double t) {
    return Math.tan(t);
  }

  private static double slope(Point a, Point b) {
    return (a.y - b.y) / (a.x - b.x);
  }

  private static boolean escapes(Point p) {
    return -0.01 < p.x && p.x < 0.01 && p.y > 0;
  }

  private static class Point {
    final double x;
    final double y;

    Point(double x, double y) {
      this.x = x;
      this.y = y;
    }
  }
}
