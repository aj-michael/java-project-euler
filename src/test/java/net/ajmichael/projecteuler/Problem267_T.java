package net.ajmichael.projecteuler;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Problem267_T {
  @Test
  public void testSolve() {
    assertEquals(BigDecimal.valueOf(0.999992836187), Problem267.solve());
  }
}
