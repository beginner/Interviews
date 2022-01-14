package heap;

import common.ListNode;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * Level : Hard
 */
public class MergeKSortedList_23 {

    public ListNode mergeKLists(ListNode[] lists) {
        int n = lists.length;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);

        for(int i = 0; i < n ; i++) {
            ListNode current = lists[i];
            if (current != null) pq.offer(current);
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {
            ListNode poll = pq.poll();
            curr.next = poll;
            curr = curr.next;
            if (poll.next != null) pq.offer(poll.next);
        }

        return dummy.next;

    }
}
