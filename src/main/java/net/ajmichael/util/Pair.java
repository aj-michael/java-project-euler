package net.ajmichael.util;

public class Pair<T> {
  public final T x;
  public final T y;

  public Pair(T x, T y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Pair) {
      Pair<T> otherPair = (Pair<T>) other;
      return x.equals(otherPair.x) && y.equals(otherPair.y);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return x.hashCode() ^ y.hashCode();
  }

  @Override
  public String toString() {
    return "(" + x.toString() + "," + y.toString() + ")";
  }
}
