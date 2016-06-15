package net.ajmichael.projecteuler;

import net.ajmichael.util.Primes;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Problem060 {
  public static void main(String args[]) {
    System.out.println(solve());
  }

  public static int solve() {
    Set<Integer> primes = new LinkedHashSet<>(Primes.sieve(90000000));
    Map<Integer, Set<Integer>> edgeSets = new LinkedHashMap<>();
    for (int prime : primes) {
      edgeSets.put(prime, new LinkedHashSet<>());
    }

    for (int prime : primes) {
      String string = Integer.toString(prime);
      for (int i = 1; i < string.length(); i++) {
        String start = string.substring(0, i);
        String end = string.substring(i);
        if (end.charAt(0) != '0') {
          int p1 = Integer.parseInt(start);
          int p2 = Integer.parseInt(end);
          int p3 = Integer.parseInt(end + start);
          if (primes.contains(p1) && primes.contains(p2) && primes.contains(p3)) {
            if (p1 < p2) {
              edgeSets.get(p1).add(p2);
            } else {
              edgeSets.get(p2).add(p1);
            }
          }
        }
      }
    }

    int currentMin = Integer.MAX_VALUE;
    for (int prime : primes) {
      for (int adjacentPrime : edgeSets.get(prime)) {
        Set<Integer> intersection = new LinkedHashSet<>(edgeSets.get(prime));
        intersection.retainAll(edgeSets.get(adjacentPrime));
        if (intersection.size() >= 3) {
          for (int anotherAdjacentPrime : intersection) {
            Set<Integer> furtherIntersection = new LinkedHashSet<>(intersection);
            furtherIntersection.retainAll(edgeSets.get(anotherAdjacentPrime));
            if (furtherIntersection.size() >= 2) {
              for (int fourthAdjacentPrime : furtherIntersection) {
                Set<Integer> finalIntersection = new LinkedHashSet<>(furtherIntersection);
                finalIntersection.retainAll(edgeSets.get(fourthAdjacentPrime));
                Optional<Integer> finalPrime = finalIntersection.stream().min(Comparator.naturalOrder());
                if (finalPrime.isPresent()) {
                  int sum = prime + adjacentPrime + anotherAdjacentPrime + fourthAdjacentPrime + finalPrime.get();
                  if (sum < currentMin) {
                    currentMin = sum;
                  }
                }
              }
            }
          }
        }
      }
    }
    return currentMin;
  }
}
