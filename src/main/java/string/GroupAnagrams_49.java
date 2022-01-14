package string;

import java.util.*;

/**
 * https://leetcode.com/problems/group-anagrams/
 * Level: Medium
 */
public class GroupAnagrams_49 {


    // Time Complexity - O(N * KlogK)
    // Space complexity - O(N * K)
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String s : strs) { // O(N)
            char[] arr = s.toCharArray();
            Arrays.sort(arr); // O(KlogK)
            String sorted = new String(arr);
            map.putIfAbsent(sorted, new ArrayList<>());
            map.get(sorted).add(s);
        }

        return new ArrayList<>(map.values());

    }

    // Time - O(N * K)
    // Space - O(N * K)
    public List<List<String>> groupAnagrams_2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for(String s : strs) { // O(N)
            String representation = getRepresentation(s);
            map.putIfAbsent(representation, new ArrayList<>());
            map.get(representation).add(s);
        }

        return new ArrayList<>(map.values());
    }


 // O(K)
    private String getRepresentation(String s) {
        char[] arr = s.toCharArray();
        int[] counts = new int[26];
        for(int i = 0; i < arr.length; i++) { // O(K)
            int index = arr[i] - 'a';
            counts[index]++;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < counts.length; i++) {
            sb.append(counts[i] + "#");
        }
        return sb.toString();
    }

}
