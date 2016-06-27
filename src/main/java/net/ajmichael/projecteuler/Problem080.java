package net.ajmichael.projecteuler;

import net.ajmichael.util.BigDecimals;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem080 {
  public static long solve() {
    List<Integer> squares = IntStream.range(1, 11)
        .mapToObj(x -> x * x)
        .collect(Collectors.toList());
    return IntStream.range(1, 101)
        .filter(x -> !squares.contains(x))
        .boxed()
        .map(BigDecimal::valueOf)
        .map(BigDecimals::sqrt)
        .mapToLong(x -> x.toString()
            .replace(".", "")
            .substring(0, 100)
            .chars()
            .map(c -> c - 48)
            .sum())
        .sum();
  }
}
