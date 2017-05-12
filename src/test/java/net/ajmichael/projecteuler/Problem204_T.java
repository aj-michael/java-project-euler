package net.ajmichael.projecteuler;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Problem204_T {
  @Test
  public void testSolve() throws IOException {
    assertEquals(1105L, Problem204.countHammingNumbers(5, 100000000));
    assertEquals(2944730L, Problem204.countHammingNumbers(100, 1000000000));
  }
}
