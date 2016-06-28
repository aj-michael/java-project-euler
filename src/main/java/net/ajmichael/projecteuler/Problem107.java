package net.ajmichael.projecteuler;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Problem107 {
  private static final URL INPUT_FILE = Resources.getResource("p107_network.txt");

  public static long solve() throws IOException {
    List<String> lines = Resources.readLines(INPUT_FILE, Charsets.UTF_8);
    String NO_EDGE = "-";
    Map<Integer, List<Edge>> vertexEdgeMap = new HashMap<>();
    for (int i = 0; i < lines.size(); i++) {
      vertexEdgeMap.put(i, new ArrayList<>());
    }
    int totalEdgeSum = 0;
    for (int i = 0; i < lines.size(); i++) {
      String[] edges = lines.get(i).split(",");
      for (int j = i + 1; j < edges.length; j++) {
        if (!edges[j].equals(NO_EDGE)) {
          int weight = Integer.parseInt(edges[j]);
          Edge e = new Edge(i, j, weight);
          vertexEdgeMap.get(i).add(e);
          vertexEdgeMap.get(j).add(e);
          totalEdgeSum += weight;
        }
      }
    }
    PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
      @Override
      public int compare(Edge o1, Edge o2) {
        return o1.weight - o2.weight;
      }
    });
    Set<Integer> seen = new HashSet<>();
    int mstEdgeSum = 0;
    seen.add(0);
    pq.addAll(vertexEdgeMap.get(0));
    while (seen.size() < lines.size()) {
      Edge candidate = pq.poll();
      if (!seen.contains(candidate.v1)) {
        seen.add(candidate.v1);
        mstEdgeSum += candidate.weight;
        pq.addAll(vertexEdgeMap.get(candidate.v1));
      } else if (!seen.contains(candidate.v2)) {
        seen.add(candidate.v2);
        mstEdgeSum += candidate.weight;
        pq.addAll(vertexEdgeMap.get(candidate.v2));
      }
    }
    return totalEdgeSum - mstEdgeSum;
  }

  static class Edge {
    public final int v1;
    public final int v2;
    public final int weight;

    public Edge(int v1, int v2, int weight) {
      this.v1 = Math.min(v1, v2);
      this.v2 = Math.max(v1, v2);
      this.weight = weight;
    }

    @Override
    public boolean equals(Object other) {
      if (other instanceof Edge) {
        Edge otherEdge = (Edge) other;
        return (v1 == otherEdge.v1 && v2 == otherEdge.v2) || (v1 == otherEdge.v2 && v2 == otherEdge.v1);
      } else {
        return false;
      }
    }

    @Override
    public int hashCode() {
      return 27 * v1 + v2;
    }

    @Override
    public String toString() {
      return "(" + v1 + "," + v2 + "):" + weight;
    }
  }
}
