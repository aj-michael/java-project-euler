package net.ajmichael.projecteuler;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import net.ajmichael.util.Pair;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class Problem096 {
  private static final URL INPUT_FILE = Resources.getResource("p096_sudoku.txt");

  public static int solve() throws IOException {
    List<String> lines = Resources.readLines(INPUT_FILE, Charsets.UTF_8);
    int total = 0;
    for (int boardNumber = 0; 10 * boardNumber < lines.size(); boardNumber++) {
      int[][] board = new int[9][9];
      for (int lineNumber = 1; lineNumber < 10; lineNumber++) {
        String line = lines.get(10 * boardNumber + lineNumber);
        for (int c = 0; c < 9; c++) {
          board[lineNumber - 1][c] = Integer.parseInt(line.substring(c, c + 1));
        }
      }
      int[][] solution = new Sudoku(board).solve();
      total += 100 * solution[0][0] + 10 * solution[0][1] + solution[0][2];
    }
    return total;
  }

  static class Sudoku {
    private int[][] current;

    Sudoku(int[][] initial) {
      current = initial;
    }

    int[][] solve() {
      if (isValid()) {
        Optional<Pair<Integer>> possibleNext = nextOpenCoordinate();
        if (!possibleNext.isPresent()) {
          return current;
        } else {
          Pair<Integer> next = possibleNext.get();
          for (int choice = 1; choice <= 9; choice++) {
            current[next.x][next.y] = choice;
            int[][] solution = solve();
            if (solution != null) {
              return solution;
            }
          }
          current[next.x][next.y] = 0;
        }
      }
      return null;
    }

    Optional<Pair<Integer>> nextOpenCoordinate() {
      for (int r = 0; r < 9; r++) {
        for (int c = 0; c < 9; c++) {
          if (current[r][c] == 0) {
            return Optional.of(new Pair(r, c));
          }
        }
      }
      return Optional.empty();
    }

    boolean isValid() {
      for (int r = 0; r < 9; r++) {
        Set<Integer> seen = new HashSet<>();
        for (int c = 0; c < 9; c++) {
          if (current[r][c] != 0 && seen.contains(current[r][c])) {
            return false;
          }
          seen.add(current[r][c]);
        }
      }
      for (int c = 0; c < 9; c++) {
        Set<Integer> seen = new HashSet<>();
        for (int r = 0; r < 9; r++) {
          if (current[r][c] != 0 && seen.contains(current[r][c])) {
            return false;
          }
          seen.add(current[r][c]);
        }
      }
      for (int square = 0; square < 9; square++) {
        Set<Integer> seen = new HashSet<>();
        for (Pair<Integer> coordinate : squareCoordinates(square)) {
          int value = current[coordinate.x][coordinate.y];
          if (value != 0 && seen.contains(value)) {
            return false;
          }
          seen.add(value);
        }
      }
      return true;
    }
  }

  /**
   * Coordinates of the nth sudoku 3x3 square.
   */
  private static List<Pair<Integer>> squareCoordinates(int n) {
    List<Pair<Integer>> square = new ArrayList<>();
    int rightShift = n % 3;
    int downShift = n / 3;
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        square.add(new Pair(r + 3 * rightShift, c + 3 * downShift));
      }
    }
    return square;
  }
}
