package net.ajmichael.projecteuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Problem016_T {

  @Test
  public void testSolve() {
    assertEquals(1366, Problem016.solve());
  }
}
