package dayFiftyFour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FiftyFour_54 {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5};
        int n = 2;
        ListNode head = new ListNode(a[0]);
        ListNode current = head;
        for (int i = 1; i < a.length; i++) {
            current.next = new ListNode(a[i]);
            current = current.next;
        }
        head = removeNthFromEnd(head, n);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }

        int[] b={1,2,3};
        System.out.println(Arrays.asList(permute(b)));

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> permutations = new ArrayList<>();
        if (nums.length == 0) {
            return permutations;
        }

        collectPermutations(nums, 0, new ArrayList<>(), permutations);
        return permutations;
    }

    private static void collectPermutations(int[] nums, int start, List<Integer> permutation,
                                            List<List<Integer>> permutations) {

        if (permutation.size() == nums.length) {
            permutations.add(permutation);
            return;
        }

        for (int i = 0; i <= permutation.size(); i++) {
            List<Integer> newPermutation = new ArrayList<>(permutation);
            newPermutation.add(i, nums[start]);
            collectPermutations(nums, start + 1, newPermutation, permutations);
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(0, head);
        ListNode dummy = res;
        for (int i = 0; i < n; i++) head = head.next;
        while (head != null) {
            head = head.next;
            dummy = dummy.next;
        }
        dummy.next = dummy.next.next;
        return res.next;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
