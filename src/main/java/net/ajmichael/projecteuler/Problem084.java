package net.ajmichael.projecteuler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.A1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.A2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.B1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.B2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.B3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.C1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.C2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.C3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.CC1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.CC2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.CC3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.CH1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.CH2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.CH3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.D1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.D2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.D3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.E1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.E2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.E3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.F1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.F2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.F3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.FP;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.G1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.G2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.G2J;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.G3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.GO;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.H1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.H2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.JAIL;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.R1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.R2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.R3;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.R4;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.T1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.T2;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.U1;
import static net.ajmichael.projecteuler.Problem084.MonopolyGame.Tile.U2;

public class Problem084 {
  public static long solve() {
    MonopolyGame game = new MonopolyGame(Arrays.asList(new Dice(4), new Dice(4)));
    Map<Integer, Integer> counter = new HashMap<>();
    int N = 10000000;
    for (int i = 0 ; i < N; i++) {
      int position = game.move();
      counter.put(position, counter.getOrDefault(position, 0) + 1);
    }
    List<Map.Entry<Integer, Integer>> counts = new ArrayList<>(counter.entrySet());
    Collections.sort(counts, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
    return Integer.parseInt(
        String.join("", counts.stream()
            .map(Map.Entry::getKey)
            .map(Object::toString)
            .collect(Collectors.toList())
            .subList(0, 3)));
  }

  static class MonopolyGame {
    final List<Dice> dice;
    final List<Tile> board;
    private int doublesCount = 0;

    static final Deck chDeck;
    static final Deck ccDeck;
    static final Deck g2jDeck;
    static {
      g2jDeck = new Deck(Collections.singletonList(new GotoAction(10)));
      ccDeck = new Deck(Arrays.asList(
          new GotoAction(0),
          new GotoAction(10),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction()
      ));
      chDeck = new Deck(Arrays.asList(
          new GotoAction(0),
          new GotoAction(10),
          new GotoAction(11),
          new GotoAction(24),
          new GotoAction(39),
          new GotoAction(5),
          new NextRAction(),
          new NextRAction(),
          new NextUAction(),
          new GoBackAction(3),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction(),
          new NoopAction()
      ));
    }
    private int position = 0;

    {
      board = Arrays.asList(
          GO, A1, CC1, A2, T1, R1, B1, CH1, B2, B3, JAIL, C1, U1, C2, C3, R2, D1, CC2, D2, D3, FP, E1, CH2, E2, E3, R3, F1, F2, U2, F3, G2J, G1, G2, CC3, G3, R4, CH3, H1, T2, H2
      );
    }

    MonopolyGame(List<Dice> dice) {
      this.dice = dice;
    }

    int move() {
      position = (position + rollDice()) % board.size();
      if (doublesCount == 3) {
        position = 10;
        doublesCount = 0;
      } else {
        Deck deck = board.get(position).deck;
        if (deck != null) {
          position = deck.top().apply(position, board);
        }
      }
      return position;
    }

    private int rollDice() {
      int first = dice.get(0).roll();
      int total = first;
      boolean allDiceMatch = true;
      for (int i = 1; i < dice.size(); i++) {
        int next = dice.get(i).roll();
        if (next != first) {
          allDiceMatch = false;
        }
        total += next;
      }
      if (allDiceMatch) {
        doublesCount += 1;
      } else {
        doublesCount = 0;
      }
      return total;
    }

    enum Tile {
      GO, A1, CC1(ccDeck), A2, T1, R1, B1, CH1(chDeck), B2, B3, JAIL, C1, U1, C2, C3, R2, D1, CC2(ccDeck), D2, D3, FP, E1, CH2(chDeck), E2, E3, R3, F1, F2, U2, F3, G2J(g2jDeck), G1, G2, CC3(ccDeck), G3, R4, CH3(chDeck), H1, T2, H2;

      private Deck deck;

      Tile() { }

      Tile(Deck deck) {
        this.deck = deck;
      }
    }

    static class Deck {
      private List<Action> cards;
      private int position = 0;

      Deck(List<Action> cards) {
        this.cards = cards;
      }

      Action top() {
        position = (position + 1) % cards.size();
        return cards.get(position);
      }
    }

    interface Action {
      int apply(int position, List<Tile> board);
    }

    static class NoopAction implements Action {
      @Override
      public int apply(int position, List<Tile> board) {
        return position;
      }
    }

    static class GotoAction implements Action {
      private final int index;

      GotoAction(int index) {
        this.index = index;
      }

      @Override
      public int apply(int position, List<Tile> board) {
        return index;
      }
    }

    static class NextRAction implements Action {
      @Override
      public int apply(int position, List<Tile> board) {
        if (position < 5) {
          return 5;
        } else if (position < 15) {
          return 15;
        } else if (position < 25) {
          return 25;
        } else if (position < 35) {
          return 35;
        } else {
          return 5;
        }
      }
    }

    static class NextUAction implements Action {
      @Override
      public int apply(int position, List<Tile> board) {
        if (position < 12) {
          return 12;
        } else if (position < 28) {
          return 28;
        } else {
          return 12;
        }
      }
    }

    static class GoBackAction implements Action {
      private final int offset;

      GoBackAction(int offset) {
        this.offset = offset;
      }

      @Override
      public int apply(int position, List<Tile> board) {
        return ((position + board.size()) - offset) % board.size();
      }
    }
  }

  private static class Dice {
    final int sides;
    final Random random = new Random();

    Dice(int sides) {
      this.sides = sides;
    }

    int roll() {
      return 1 + random.nextInt(sides);
    }
  }
}
