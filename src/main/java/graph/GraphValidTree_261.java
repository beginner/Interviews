package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Problem no. 261
 * https://leetcode.com/problems/graph-valid-tree/
 * Level : Medium
 */
public class GraphValidTree_261 {

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int[] edge: edges) {
            map.putIfAbsent(edge[0], new HashSet<>());
            map.putIfAbsent(edge[1], new HashSet<>());
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        Set<Integer> visit = new HashSet<>();
        dfs(visit, 0, map);

        return visit.size() == n && edges.length == n - 1;
    }

    private void dfs(Set<Integer> visit, int node, Map<Integer, Set<Integer>> map) {
        visit.add(node);
        Set<Integer> nodes = map.getOrDefault(node, new HashSet<>());
        for(int n : nodes) {
            if (!visit.contains(n))
                dfs(visit, n, map);
        }
    }

}
