package array;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/insert-interval/
 * Level : Medium
 */
public class InsertInterval_57 {

    // Time - O(nlogn)
    // Space - O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));

        // O(n * logn)
        for(int[] interval : intervals) pq.offer(interval); // throwing everything in PQ will not make use of the sorted input intervals array.

        pq.offer(newInterval);

        LinkedList<int[]> list = new LinkedList<>();
        list.addLast(pq.poll());

        while (!pq.isEmpty()) { // O(N)
            int[] last = list.getLast();
            int start = last[0];
            int end = last[1];
            boolean merged = false;

            while (!pq.isEmpty() && end >= pq.peek()[0]) {
                end = Math.max(end, pq.poll()[1]);
                merged = true;
            }

            if (merged) {
                list.removeLast();
                list.add(new int[]{start, end});
            } else {
                list.add(pq.poll());
            }

        }

        return list.toArray(new int[0][0]);
    }

    // Time - O(n)
    // Space - O(n)
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        int n = intervals.length;

        LinkedList<int[]> list = new LinkedList<>();
        int index = 0;
        while (index < n && newInterval[0] > intervals[index][0]) {
            list.add(intervals[index]);
            index++;
        }

        if (!list.isEmpty() && list.getLast()[1] >= newInterval[0]) {
            int end = Math.max(list.getLast()[1], newInterval[1]);
            list.getLast()[1] = end;
        } else {
            list.add(newInterval);
        }

        for(int i = index; i < n; i++) {
            int[] current = intervals[i];
            int[] last = list.getLast();

            if (current[0] <= last[1]) { // merge
                int end = Math.max(current[1], last[1]);
                list.getLast()[1] = end;
            } else {
                list.add(current);
            }
        }

        return list.toArray(new int[0][0]);
    }

}
