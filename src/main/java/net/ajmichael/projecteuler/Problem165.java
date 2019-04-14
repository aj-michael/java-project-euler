package net.ajmichael.projecteuler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

final class Problem165 {
  static class Point {
    final long x;
    final long y;

    Point(long x, long y) {
      this.x = x;
      this.y = y;
    }
  }

  static class DoublePoint {
    final double x;
    final double y;

    DoublePoint(double x, double y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object o) {
      DoublePoint other = (DoublePoint) o;
      return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
      return ((int) (x * 1000)) ^ ((int) (y * 1000));
    }

    @Override
    public String toString() {
      return "(" + x + ", " + y + ")";
    }
  }

  static class Segment {
    final Point a;
    final Point b;

    Segment(Point a, Point b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public String toString() {
      return "Segment[ (" + a.x + "," + a.y + "), (" + b.x + "," + b.y + ") ]";
    }
  }

  private static long next(long previous) {
    return (previous * previous) % 50515093;
  }

  private static List<Segment> segments(int count) {
    long value = 290797;
    List<Segment> segments = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      long a = next(value);
      long b = next(a);
      long c = next(b);
      long d = next(c);
      value = d;
      segments.add(new Segment(new Point(a % 500, b % 500), new Point(c % 500, d % 500)));
    }
    return segments;
  }

  private static Optional<DoublePoint> intersection(Segment s1, Segment s2) {
    long x_num = (s1.a.x * s1.b.y - s1.a.y * s1.b.x) * (s2.a.x - s2.b.x) - (s1.a.x - s1.b.x) * (s2.a.x * s2.b.y - s2.a.y * s2.b.x);
    long y_num = (s1.a.x * s1.b.y - s1.a.y * s1.b.x) * (s2.a.y - s2.b.y) - (s1.a.y - s1.b.y) * (s2.a.x * s2.b.y - s2.a.y * s2.b.x);
    long denom = (s1.a.x - s1.b.x) * (s2.a.y - s2.b.y) - (s1.a.y - s1.b.y) * (s2.a.x - s2.b.x);
    if (denom == 0) {
      return Optional.empty();
    }
    return Optional.of(new DoublePoint(x_num / (1.0 * denom), y_num / (1.0 * denom)));
  }

  private static boolean closeEnough(double a, long b) {
    return Math.abs(b - a) < 0.001;
  }

  private static boolean equals(DoublePoint dp, Point p) {
    return closeEnough(dp.x, p.x) && closeEnough(dp.y, p.y);
  }

  static long solve() {
    List<Segment> allSegments = segments(5000);
    HashSet<DoublePoint> points = new HashSet<>();
    for (int i = 0; i < allSegments.size(); i++) {
      for (int j = 0; j < allSegments.size(); j++) {
        if (i == j) {
          continue;
        }
        Segment s1 = allSegments.get(i);
        Segment s2 = allSegments.get(j);
        Optional<DoublePoint> opt = intersection(allSegments.get(i), allSegments.get(j));
        if (!opt.isPresent()) {
          continue;
        }
        DoublePoint pt = opt.get();
        if (equals(pt, s1.a) || equals(pt, s1.b) || equals(pt, s2.a) || equals(pt, s2.b)) {
          continue;
        }

        if (pt.x > Math.max(s1.a.x, s1.b.x)) {
          continue;
        }
        if (pt.x < Math.min(s1.a.x, s1.b.x)) {
          continue;
        }
        if (pt.x > Math.max(s2.a.x, s2.b.x)) {
          continue;
        }
        if (pt.x < Math.min(s2.a.x, s2.b.x)) {
          continue;
        }
        if (pt.y > Math.max(s1.a.y, s1.b.y)) {
          continue;
        }
        if (pt.y < Math.min(s1.a.y, s1.b.y)) {
          continue;
        }
        if (pt.y > Math.max(s2.a.y, s2.b.y)) {
          continue;
        }
        if (pt.y < Math.min(s2.a.y, s2.b.y)) {
          continue;
        }
        points.add(pt);
      }
    }
    return points.size();
  }

  public static void main(String[] args) {
    System.out.println(solve());
  }
}
