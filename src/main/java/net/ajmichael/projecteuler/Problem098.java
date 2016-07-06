package net.ajmichael.projecteuler;

import com.google.common.base.Charsets;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem098 {
  private static final URL INPUT_FILE = Resources.getResource("p098_words.txt");

  private static int length(long x) {
    return (int) (Math.log10(x) + 1);
  }

  public static long solve() throws IOException {
    String raw = Resources.readLines(INPUT_FILE, Charsets.UTF_8).get(0);
    String[] words = raw.substring(1, raw.length() - 1).split("\",\"");
    Map<String, Set<String>> wordsBySortedWord = new HashMap<>();
    for (String word : words) {
      char[] chars = word.toCharArray();
      Arrays.sort(chars);
      String sortedWord = new String(chars);
      if (!wordsBySortedWord.containsKey(sortedWord)) {
        wordsBySortedWord.put(sortedWord, new HashSet<>());
      }
      wordsBySortedWord.get(sortedWord).add(word);
    }
    Map<String, Set<String>> anagrams = new HashMap<>();
    wordsBySortedWord.entrySet().stream()
        .filter(e -> e.getValue().size() > 1)
        .forEach(e -> anagrams.put(e.getKey(), e.getValue()));
    int maxLength = 0;
    for (String word : anagrams.keySet()) {
      if (word.length() > maxLength) {
        maxLength = word.length();
      }
    }
    Map<Integer, Set<Long>> squaresByLength = new HashMap<>();
    for (int i = 1; i <= maxLength; i++) {
      squaresByLength.put(i, new HashSet<>());
    }
    for (long x = 1; ; x++) {
      int length = length(x * x);
      if (length <= maxLength) {
        squaresByLength.get(length).add(x * x);
      } else {
        break;
      }
    }

    Map<Integer, Map<Map<Integer, Integer>, Set<Long>>> squaresByDigitCountByLength = new HashMap<>();
    for (int length : squaresByLength.keySet()) {
      Map<Map<Integer, Integer>, Set<Long>> squaresByDigitCount = new HashMap<>();
      squaresByDigitCountByLength.put(length, squaresByDigitCount);
      for (long x : squaresByLength.get(length)) {
        Map<Integer, Integer> digitCount = digitCount(x);
        if (!squaresByDigitCount.containsKey(digitCount)) {
          squaresByDigitCount.put(digitCount, new HashSet<>());
        }
        squaresByDigitCount.get(digitCount).add(x);
      }
    }

    long max = 0;
    for (String sorted : anagrams.keySet()) {
      long x = check(anagrams.get(sorted), squaresByDigitCountByLength.get(sorted.length()));
      if (x > max) {
        max = x;
      }
      //break;
    }
    return max;
  }

  private static long check(Set<String> words, Map<Map<Integer, Integer>, Set<Long>> squares) {
    long max = 0;
    for (String word1 : words) {
      for (String word2 : words) {
        if (!word1.equals(word2)) {
          long value = check(word1, word2, squares);
          if (value > max) {
            max = value;
          }
        }
      }
    }
    return max;
  }

  private static long check(String word1, String word2, Map<Map<Integer, Integer>, Set<Long>> squares) {
    long max = 0;
    for (Map<Integer, Integer> digitCount : squares.keySet()) {
      Multiset<Integer> digitFrequencies = HashMultiset.create(digitCount.values());
      Multiset<Integer> characterFrequencies = HashMultiset.create(characterCount(word1).values());
      if (digitFrequencies.equals(characterFrequencies)) {
        Set<Long> candidateNums = squares.get(digitCount);
        for (long word1num : candidateNums) {
          long word2num = 0;
          List<Integer> word1numDigits = longToDigitArray(word1num);
          for (int i = 0; i < word1.length(); i++) {
            int word1index = word1.indexOf(word2.charAt(i));
            int word2digit = word1numDigits.get(word1index);
            word2num = word2num * 10 + word2digit;
          }
          if (candidateNums.contains(word2num)) {
            // we found one!
            max = Math.max(max, Math.max(word1num, word2num));
          }
        }
      }
    }
    return max;
  }

  private static List<Integer> longToDigitArray(long x) {
    List<Integer> result = new ArrayList<>();
    while (x > 0) {
      result.add((int) (x % 10));
      x = x / 10;
    }
    Collections.reverse(result);
    return result;
  }

  private static Map<Character, Integer> characterCount(String w) {
    Map<Character, Integer> result = new HashMap<>();
    for (char c : w.toCharArray()) {
      result.put(c, 1 + result.getOrDefault(c, 0));
    }
    return result;
  }

  private static Map<Integer, Integer> digitCount(long s) {
    Map<Integer, Integer> digitCount = new HashMap<>();
    while (s > 0) {
      int digit = (int) (s % 10);
      digitCount.put(digit, 1 + digitCount.getOrDefault(digit, 0));
      s = s / 10;
    }
    return digitCount;
  }
}
