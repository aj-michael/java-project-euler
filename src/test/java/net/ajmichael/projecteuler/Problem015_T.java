package net.ajmichael.projecteuler;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Problem015_T {

  @Test
  public void testSolve() {
    assertEquals(137846528820L, Problem015.solve());
  }
}
