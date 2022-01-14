package graph;

import java.util.*;

/**
 * Problem no. 269
 * https://leetcode.com/problems/alien-dictionary/
 * Level : Hard
 */
public class AlienDictionary_269 {

    public static void main(String[] args) {
        String[] words = {"ac","ab","zc","zb"};
        AlienDictionary_269 problem = new AlienDictionary_269();
        System.out.println(problem.alienOrder(words));
    }

    public String alienOrder(String[] words) {
        int len = words.length;

        Map<Character, Set<Character>> map = new HashMap<>(); // first -> next set
        Map<Character, Integer> degree = new HashMap<>();

        for(String word: words) {
            for(char c : word.toCharArray()) {
                degree.put(c, 0);
            }
        }

        for(int i = 0; i < len - 1; i++) { // outer loop start
            String current = words[i];
            String next = words[i+1];
            int minLength = Math.min(current.length(), next.length());
            // "wrt" & "wrf"
            int sameCount = 0;
            for(int j = 0; j < minLength; j++) { // inner loop start
                char c = current.charAt(j);
                char n = next.charAt(j);
                if (c != n) {
                    map.putIfAbsent(c, new HashSet<>());

                    if (!map.get(c).contains(n)) {
                        int existingDegree = degree.get(n);
                        degree.put(n, existingDegree + 1);
                    }
                    map.get(c).add(n); // t -> {f}
                    break; // no need to continue processing since we found first difference
                } else {
                    sameCount++; // "wr" & "wr"
                }
            } // inner loop end

            if (sameCount == minLength && current.length() > next.length()) {
                return "";
            }

        }

        // We will do topological sort
        Queue<Character> queue = new ArrayDeque<>();
        for(Map.Entry<Character, Integer> entry : degree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            Set<Character> next = map.getOrDefault(c, new HashSet<>());
            for(Character n : next) {
                int existing = degree.get(n);
                degree.put(n, existing - 1);
                if (existing == 1) queue.offer(n);
            }

        }

        if (sb.length() < degree.size()) return "";
        return sb.toString();
    }

}
