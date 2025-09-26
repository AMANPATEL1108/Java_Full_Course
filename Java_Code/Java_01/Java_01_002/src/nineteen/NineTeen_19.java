package nineteen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class NineTeen_19 {
    public static void main(String[] args) {
//        int[] num={3,1,2,4};
//        System.out.println(Arrays.toString(sortArrayByParity(num)));
        int[] num = {-4, -1, 0, 3, 10};
        System.out.println(Arrays.toString(sortedSquares(num)));

    }

    public static int[] sortedSquares(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] * nums[i];
        }
        int[] s = sorting(nums);
        return s;
    }

    public static int[] sorting(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i] < arr[j]) {
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
        return arr;
    }

    public static int[] sortArrayByParity(int[] nums) {
        ArrayList odd = new ArrayList();
        ArrayList even = new ArrayList();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0) {
                even.add(nums[i]);
            } else {
                odd.add(nums[i]);
            }
        }
        ArrayList merged = new ArrayList();
        for (int i = 0; i < even.size(); i++) {
            merged.add(even.get(i));
        }
        for (int i = 0; i < odd.size(); i++) {
            merged.add(odd.get(i));
        }
        int[] f = new int[merged.size()];
        for (int i = 0; i < merged.size(); i++) {
            f[i] = (int) merged.get(i);
        }


        return f;
    }
}
