package fourteen;

import java.util.*;

import static fourteen.Fourteen_14.MergeKSortedLists.createList;

public class Fourteen_14 {
    public static void main(String[] args) {
        findLargest();
        List<ListNode> lists = List.of(
                createList(new int[]{1, 4, 5}),
                createList(new int[]{1, 3, 4}),
                createList(new int[]{2, 6})
        );

        ListNode result = MergeKSortedLists.mergeKLists(lists);

        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }

        System.out.println();

        String s1 = "aabbcc";
        int k1 = 3;
        System.out.println("Rearranged string: " + rearrangeString(s1, k1));
        String s2 = "aaabc";
        int k2 = 3;
        System.out.println("Rearranged string: " + rearrangeString(s2, k2));
    }

    public static void findLargest() {
        int[] a = {3, 2, 1, 5, 6, 4};
        int k = 2;

        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int i = 0; i < a.length; i++) {
            priorityQueue.offer(a[i]);

            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        System.out.println("largest elements are: " + priorityQueue);
    }

    static class MergeKSortedLists {
        public static ListNode mergeKLists(List<ListNode> lists) {
            PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

            for (ListNode node : lists) {
                if (node != null) {
                    minHeap.offer(node);
                }
            }

            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            while (!minHeap.isEmpty()) {
                ListNode node = minHeap.poll();
                current.next = node;
                current = current.next;

                if (node.next != null) {
                    minHeap.offer(node.next);
                }
            }

            return dummy.next;
        }

        public static ListNode createList(int[] arr) {
            ListNode head = new ListNode(arr[0]);
            ListNode current = head;
            for (int i = 1; i < arr.length; i++) {
                current.next = new ListNode(arr[i]);
                current = current.next;
            }
            return head;
        }
    }

    public static String rearrangeString(String s, int k) {
        if (k == 0) return s;

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequencyMap.put(ch, frequencyMap.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));
        maxHeap.addAll(frequencyMap.keySet());

        StringBuilder result = new StringBuilder();

        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();

        while (!maxHeap.isEmpty()) {
            char currentChar = maxHeap.poll();
            result.append(currentChar);
            frequencyMap.put(currentChar, frequencyMap.get(currentChar) - 1);

            waitQueue.offer(new AbstractMap.SimpleEntry<>(currentChar, frequencyMap.get(currentChar)));

            if (waitQueue.size() >= k) {
                Map.Entry<Character, Integer> entry = waitQueue.poll();
                if (entry.getValue() > 0) {
                    maxHeap.offer(entry.getKey());
                }
            }
        }

        return result.length() == s.length() ? result.toString() : "";
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}
