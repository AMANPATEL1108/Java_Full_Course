package dayFourtyFive;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourtyFive_45 {
    public static void main(String[] args) {
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(findAnagrams(s, p));
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int[] reqFreq = new int[26];
        int[] currFreq = new int[26];
        for(int c=0;c<p.length();c++){
            reqFreq[p.charAt(c) - 'a'] = reqFreq[p.charAt(c) - 'a']+1;
        }


        int l =0;
        int r =0;
        List<Integer> ans = new ArrayList<>();
        while(r<s.length()){
            currFreq[s.charAt(r)-'a'] = currFreq[s.charAt(r)-'a']+1;
            if(r-l+1 == p.length()){

                if(Arrays.equals(currFreq, reqFreq)){

                    ans.add(l);
                }
                currFreq[s.charAt(l)-'a'] = currFreq[s.charAt(l)-'a']-1;
                l++;

            }
            r++;
        }
        return ans;
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        List<List<Integer>> arr = new ArrayList<>();
        while (i < n - 2) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];
                if (total > 0) {
                    k--;
                } else if (total < 0) {
                    j++;
                } else {
                    arr.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    while (nums[j] == nums[j - 1] && j < k) {
                        j++;
                    }
                }
            }
            if (nums[i] > 0) return arr;
            i++;
        }
        return arr;
    }
}
