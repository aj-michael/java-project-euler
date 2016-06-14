package net.ajmichael.projecteuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Problem014_T {

  @Test
  public void testSolve() {
    assertEquals(837799, Problem014.solve());
  }
}
