package net.ajmichael.projecteuler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class Problem203_T {
  @Test
  public void testSolve() throws IOException {
    assertEquals(34029210557338L, Problem203.solve());
  }
}
