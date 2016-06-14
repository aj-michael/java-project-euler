package net.ajmichael.projecteuler;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import net.ajmichael.util.RomanNumeral;

import java.io.IOException;
import java.net.URL;

public class Problem089 {
  private static final URL INPUT_FILE = Resources.getResource("p089_roman.txt");

  public static int solve() throws IOException {
    int totalDifference = 0;
    for (String line : Resources.readLines(INPUT_FILE, Charsets.UTF_8)) {
      RomanNumeral rn = RomanNumeral.parse(line);
      totalDifference += line.length() - rn.toString().length();
    }
    return totalDifference;
  }
}
