package net.ajmichael.projecteuler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class Problem205_T {
  @Test
  public void testSolve() throws IOException {
    assertEquals("0.5731441", Problem205.solve());
  }
}
