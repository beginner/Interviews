package array;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/merge-intervals/
 * Level : Medium
 */
public class MergeIntervals_56 {

    // Time - O(NlogN)
    // Space - O(N)
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // O(NlogN)

        LinkedList<int[]> list = new LinkedList<>(); // Space - O(N)
        list.addLast(intervals[0]);

        for(int i = 0; i < intervals.length; i++) { // O(N)
            int[] current = intervals[i];
            int[] last = list.getLast();
            if (current[0] > last[1]) { // no merge
                list.addLast(current);
            } else { // merge
                int start = last[0];
                int end = Math.max(last[1], current[1]);
                list.removeLast();
                list.addLast(new int[]{start, end});
            }
        }

        return list.toArray(new int[0][0]);
    }


}

