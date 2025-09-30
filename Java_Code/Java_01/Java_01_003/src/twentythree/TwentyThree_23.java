package twentythree;

import java.util.ArrayList;
import java.util.Arrays;

public class TwentyThree_23 {

    public static void main(String[] args) {
//        int[] a = {0, 1, 0, 3, 12};
//        moveZeroes(a);

        int[] a1 = {1, 2, 2, 1};
        int[] a2 = {2, 2};
        System.out.println(Arrays.toString(intersect(a1, a2)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        ArrayList<Integer> arrayList1 = new ArrayList<>();
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        ArrayList<Integer> arrayList3 = new ArrayList<>();

        for (int i = 0; i < nums1.length; i++) {
            arrayList1.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            arrayList2.add(nums2[i]);
        }

        for (int i = 0; i < arrayList1.size(); i++) {
            for (int j = 0; j < arrayList2.size(); j++) {
                if (arrayList1.get(i).equals(arrayList2.get(j)) && arrayList1.get(i) != -1 && arrayList2.get(j) != -1) {
                    arrayList3.add(arrayList1.get(i));
                    arrayList1.set(i, -1);
                    arrayList2.set(j, -1);
                }
            }
        }

        int[] a = new int[arrayList3.size()];
        for (int i = 0; i < arrayList3.size(); i++) {
            a[i] = arrayList3.get(i);
        }
        return a;

    }

    public static void moveZeroes(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[i] == 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(nums));
    }


}
