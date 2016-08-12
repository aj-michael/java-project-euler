package net.ajmichael.projecteuler;

import com.google.common.hash.Hashing;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Problem191 {
  public static long solve() {
    return countWins(new State(30, false, 0));
  }

  private static final Map<State, Long> wins = new HashMap<>();

  private static long countWins(State state) {
    if (state.lengthRemaining == 0) {
      return 1;
    } else if (!wins.containsKey(state)) {
      long total = 0;
      total += countWins(new State(state.lengthRemaining - 1, state.alreadyLate, 0));
      if (!state.alreadyLate) {
        total += countWins(new State(state.lengthRemaining - 1, true, 0));
      }
      if (state.consecutiveAbsences < 2) {
        total += countWins(new State(state.lengthRemaining - 1, state.alreadyLate, state.consecutiveAbsences + 1));
      }
      wins.put(state, total);
    }
    return wins.get(state);
  }

  private static final class State {
    final int lengthRemaining;
    final boolean alreadyLate;
    final int consecutiveAbsences;

    State(int lengthRemaining, boolean alreadyLate, int consecutiveAbsences) {
      this.lengthRemaining = lengthRemaining;
      this.alreadyLate = alreadyLate;
      this.consecutiveAbsences = consecutiveAbsences;
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof State) {
        State otherState = (State) other;
        return Objects.equals(lengthRemaining, otherState.lengthRemaining)
            && Objects.equals(alreadyLate, otherState.alreadyLate)
            && Objects.equals(consecutiveAbsences, otherState.consecutiveAbsences);
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return Hashing.md5()
          .newHasher()
          .putInt(lengthRemaining)
          .putBoolean(alreadyLate)
          .putInt(consecutiveAbsences)
          .hash()
          .asInt();
    }
  }
}
