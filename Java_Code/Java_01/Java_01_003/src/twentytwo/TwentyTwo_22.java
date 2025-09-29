package twentytwo;

import java.util.*;

public class TwentyTwo_22 {
    public static void main(String[] args) {
//        int t= 7;
//        int[] nums = {2,3,1,2,4,3};
//        System.out.println(minSubArrayLen(t,nums));
        String[] s={"eat","tea","tan","ate","nat"   ,"bat"};

        System.out.println(groupAnagrams(s));

    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            if (!map.containsKey(sortedStr)) {
                map.put(sortedStr, new ArrayList<>());
            }

            map.get(sortedStr).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static int minSubArrayLen(int target, int[] nums) {
        int left = 0, total = 0, result = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            total += nums[right];

            while (total >= target) {
                result = Math.min(result, right - left + 1);
                total -= nums[left];
                left++;
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

}
