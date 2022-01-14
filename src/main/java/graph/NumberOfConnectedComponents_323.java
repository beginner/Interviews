package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem no. 323
 * https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
 * Level : Medium
 */
public class NumberOfConnectedComponents_323 {

    public int countComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for(int[] edge : edges) {
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.putIfAbsent(edge[1], new HashSet<>());

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visited = new HashSet<>();
        int connected = 0;

        for(int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                connected++;
                dfs(i, visited, graph);
            }
        }
        return connected;

    }

    private void dfs(int node, Set<Integer> visited, Map<Integer, Set<Integer>> graph) {
        visited.add(node);
        Set<Integer> neighbors = graph.getOrDefault(node, new HashSet<>());
        for(int n : neighbors) {
            if (!visited.contains(n)) {
                dfs(n, visited, graph);
            }
        }
    }

}
