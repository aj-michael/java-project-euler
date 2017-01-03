package net.ajmichael.projecteuler;

import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;

final class Problem205 {

  static String solve() {
    Map<Integer, Double> peteProbs = ImmutableMap.of(0, 1.0);
    for (int i = 0; i < 9; i++) {
      Map<Integer, Double> nextProbs = new HashMap<>();
      for (int value : peteProbs.keySet()) {
        double baseProb = peteProbs.get(value);
        for (int j = 1; j <= 4; j++) {
          nextProbs.put(value + j, nextProbs.getOrDefault(value + j, 0.) + baseProb / 4);
        }
      }
      peteProbs = nextProbs;
    }

    Map<Integer, Double> colinProbs = ImmutableMap.of(0, 1.0);
    for (int i = 0; i < 6; i++) {
      Map<Integer, Double> nextProbs = new HashMap<>();
      for (int value : colinProbs.keySet()) {
        double baseProb = colinProbs.get(value);
        for (int j = 1; j <= 6; j++) {
          nextProbs.put(value + j, nextProbs.getOrDefault(value + j, 0.) + baseProb / 6);
        }
      }
      colinProbs = nextProbs;
    }

    double totalProb = 0;
    for (int peteRoll : peteProbs.keySet()) {
      double peteProb = peteProbs.get(peteRoll);
      for (int colinRoll : colinProbs.keySet()) {
        if (colinRoll < peteRoll) {
          double colinProb = colinProbs.get(colinRoll);
          totalProb += peteProb * colinProb;
        }
      }
    }

    return String.format("%.7f", totalProb);
  }
}
