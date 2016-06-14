package net.ajmichael.projecteuler;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import net.ajmichael.util.Coordinate2D;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem102 {
  private static final URL INPUT_FILE = Resources.getResource("p102_triangles.txt");
  private static final Coordinate2D<Float> ORIGIN = new Coordinate2D<>(0f, 0f);

  private static float scurl(Coordinate2D<Float> p1, Coordinate2D<Float> p2, Coordinate2D<Float> p3) {
    return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
  }

  public static int solve() throws IOException {
    int count = 0;
    for (String line : Resources.readLines(INPUT_FILE, Charsets.UTF_8)) {
      List<Float> components = Arrays.stream(line.split(","))
          .map(Float::parseFloat)
          .collect(Collectors.toList());
      Coordinate2D<Float> p1 = new Coordinate2D<>(components.get(0), components.get(1));
      Coordinate2D<Float> p2 = new Coordinate2D<>(components.get(2), components.get(3));
      Coordinate2D<Float> p3 = new Coordinate2D<>(components.get(4), components.get(5));
      boolean scurlSign12 = scurl(ORIGIN, p1, p2) < 0f;
      boolean scurlSign23 = scurl(ORIGIN, p2, p3) < 0f;
      boolean scurlSign31 = scurl(ORIGIN, p3, p1) < 0f;
      if ((scurlSign12 == scurlSign23) && (scurlSign23 == scurlSign31)) {
        count++;
      }
    }
    return count;
  }
}
