package net.ajmichael.projecteuler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class Problem119_T {

  @Test
  public void testSolve() throws IOException {
    assertEquals(248155780267521L, Problem119.solve());
  }
}
