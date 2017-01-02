package net.ajmichael.projecteuler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class Problem504_T {
  @Test
  public void testSolve() {
    assertEquals(694687, Problem504.solve());
  }
}
