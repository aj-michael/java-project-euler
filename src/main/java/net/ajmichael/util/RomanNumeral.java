package net.ajmichael.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class RomanNumeral {
  private final int value;
  private static final Map<String, Integer> symbolMap = new LinkedHashMap<>();
  static {
    symbolMap.put("M", 1000);
    symbolMap.put("CM", 900);
    symbolMap.put("D", 500);
    symbolMap.put("CD", 400);
    symbolMap.put("C", 100);
    symbolMap.put("XC", 90);
    symbolMap.put("L", 50);
    symbolMap.put("XL", 40);
    symbolMap.put("X", 10);
    symbolMap.put("IX", 9);
    symbolMap.put("V", 5);
    symbolMap.put("IV", 4);
    symbolMap.put("I", 1);
  }

  public static RomanNumeral parse(String s) {
    String input = s.toUpperCase();
    int value = 0;
    for (int i = 0; i < input.length() - 1; i++) {
      int currentValue = symbolMap.get(String.valueOf(input.charAt(i)));
      int nextValue = symbolMap.get(String.valueOf(input.charAt(i + 1)));
      if (currentValue < nextValue) {
        value -= currentValue;
      } else {
        value += currentValue;
      }
    }
    value += symbolMap.get(String.valueOf(input.charAt(input.length() - 1)));
    return new RomanNumeral(value);
  }

  public RomanNumeral(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  @Override
  public String toString() {
    int remaining = value;
    StringBuilder sb = new StringBuilder();
    for (Map.Entry<String, Integer> symbol : symbolMap.entrySet()) {
      int count = remaining / symbol.getValue();
      for (int i = 0; i < count; i++) {
        sb.append(symbol.getKey());
      }
      remaining = remaining - count * symbol.getValue();
    }
    return sb.toString();
  }
}
