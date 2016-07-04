package net.ajmichael.util;

public class PythagoreanTriple {
  public final long a;
  public final long b;
  public final long c;

  public PythagoreanTriple(long a, long b, long c) {
    this.a = Math.min(a, b);
    this.b = Math.max(a, b);
    this.c = c;
    if (a * a + b * b != c * c) {
      throw new IllegalArgumentException("Not a Pythagorean triple");
    }
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof PythagoreanTriple) {
      PythagoreanTriple otherTriple = (PythagoreanTriple) other;
      return a == otherTriple.a && b == otherTriple.b;
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return (int) (31 * a + b);
  }

  @Override
  public String toString() {
    return "Triple(" + a + "," + b + "," + c + ")";
  }
}
