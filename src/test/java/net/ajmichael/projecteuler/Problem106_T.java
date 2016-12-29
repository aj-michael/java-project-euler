package net.ajmichael.projecteuler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class Problem106_T {
  @Test
  public void testProvidedAnswers() {
    assertEquals(1, Problem106.numberOfPairsToCheck(4));
    assertEquals(70, Problem106.numberOfPairsToCheck(7));
  }

  @Test
  public void testSolve() {
    assertEquals(21384, Problem106.solve());
  }
}
