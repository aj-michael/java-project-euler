package net.ajmichael.projecteuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import static net.ajmichael.projecteuler.Problem090.Status.IN;
import static net.ajmichael.projecteuler.Problem090.Status.MAYBE;

public class Problem090 {
  public static long solve() {
    Status[] cube1 = {MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE};
    Status[] cube2 = {MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE, MAYBE};
    count(0, cube1, cube2);
    return cubePairs.size();
  }

  private static List<List<Integer>> maybeSwapSixNine(List<Integer> original) {
    List<List<Integer>> allOptions = new ArrayList<>();
    allOptions.add(original);
    for (int i = 0; i < original.size(); i++) {
      if (original.get(i) == 6) {
        int currentSize = allOptions.size();
        for (int j = 0; j < currentSize; j++) {
          List<Integer> swappedList = new ArrayList<>(allOptions.get(j));
          swappedList.set(i, 9);
          allOptions.add(swappedList);
        }
      }
    }
    return allOptions;
  }

  private static <T> boolean noRepeats(List<T> vals) {
    return vals.size() == new HashSet<>(vals).size();
  }

  private static int[][] squares =
      {{0, 1}, {0, 4}, {0, 6}, {1, 6}, {2, 5}, {3, 6}, {4, 6}, {8, 1}};

  private static Set<Long> cubePairs = new HashSet<>();
  private static void count(int index, Status[] cube1, Status[] cube2) {
    if (index == squares.length) {
      List<Integer> requiredCube1 = getIn(cube1);
      List<Integer> requiredCube2 = getIn(cube2);
      for (List<Integer> possibleCube1 : fillIn(requiredCube1)) {
        for (List<Integer> possibleCube2 : fillIn(requiredCube2)) {
          for (List<Integer> swappedCube1 : maybeSwapSixNine(possibleCube1)) {
            for (List<Integer> swappedCube2 : maybeSwapSixNine(possibleCube2)) {
              if (noRepeats(swappedCube1) && noRepeats(swappedCube2)) {
                cubePairs.add(combineCubes(swappedCube1, swappedCube2));
              }
            }
          }
        }
      }
      return;
    }
    int[] square = squares[index];
    if (cube1[square[0]] == IN) {
      if (cube2[square[1]] == IN) {
        count(index + 1, cube1, cube2);
      } else if (cube2[square[1]] == MAYBE && numIn(cube2) < 6) {
        cube2[square[1]] = IN;
        count(index + 1, cube1, cube2);
        cube2[square[1]] = MAYBE;
      }
    } else if (cube1[square[0]] == MAYBE && numIn(cube1) < 6) {
      cube1[square[0]] = IN;
      if (cube2[square[1]] == IN) {
        count(index + 1, cube1, cube2);
      } else if (cube2[square[1]] == MAYBE && numIn(cube2) < 6) {
        cube2[square[1]] = IN;
        count(index + 1, cube1, cube2);
        cube2[square[1]] = MAYBE;
      }
      cube1[square[0]] = MAYBE;
    }

    if (cube2[square[0]] == IN) {
      if (cube1[square[1]] == IN) {
        count(index + 1, cube1, cube2);
      } else if (cube1[square[1]] == MAYBE) {
        cube1[square[1]] = IN;
        count(index + 1, cube1, cube2);
        cube1[square[1]] = MAYBE;
      }
    } else if (cube2[square[0]] == MAYBE && numIn(cube2) < 6) {
      cube2[square[0]] = IN;
      if (cube1[square[1]] == IN) {
        count(index + 1, cube1, cube2);
      } else if (cube1[square[1]] == MAYBE && numIn(cube1) < 6) {
        cube1[square[1]] = IN;
        count(index + 1, cube1, cube2);
        cube1[square[1]] = MAYBE;
      }
      cube2[square[0]] = MAYBE;
    }
  }

  private static List<List<Integer>> fillIn(List<Integer> required) {
    Queue<List<Integer>> q = new LinkedList<>();
    q.add(required);
    List<List<Integer>> result = new ArrayList<>();
    while (!q.isEmpty()) {
      List<Integer> current = q.poll();
      if (current.size() == 6) {
        result.add(current);
      } else {
        for (int i = 0; i < 9; i++) {
          List<Integer> next = new ArrayList<>(current);
          next.add(i);
          q.add(next);
        }
      }
    }
    return result;
  }

  private static long numIn(Status[] array) {
    return Arrays.stream(array)
        .filter(x -> x == IN)
        .count();
  }

  private static long combineCubes(List<Integer> cube1, List<Integer> cube2) {
    if (cube1.size() != 6 || cube2.size() != 6) {
      throw new IllegalArgumentException();
    } else {
      Collections.sort(cube1);
      Collections.sort(cube2);
      long cube1Long = 0;
      long cube2Long = 0;
      for (int i = 0; i < 6; i++) {
        cube1Long = 10 * cube1Long + cube1.get(i);
        cube2Long = 10 * cube2Long + cube2.get(i);
      }
      long combined;
      if (cube1Long < cube2Long) {
        combined = cube1Long * 1000000 + cube2Long;
      } else {
        combined = cube2Long * 1000000 + cube1Long;
      }
      return combined;
    }
  }

  private static List<Integer> getIn(Status[] array) {
    List<Integer> sides = new ArrayList<>();
    for (int i = 0; i < 9; i++) {
      if (array[i] == IN) {
        sides.add(i);
      }
    }
    return sides;
  }

  enum Status {
    IN, MAYBE
  }
}
