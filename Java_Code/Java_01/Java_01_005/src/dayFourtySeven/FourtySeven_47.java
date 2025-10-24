package dayFourtySeven;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourtySeven_47 {
    public static void main(String[] args) {
        int[][] a={{1,3},{2,6},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(merge(a)));
        int[] arr ={1,2,3,4};
        System.out.println(Arrays.toString(productExceptSelf(arr)));
    }

    public static int[] productExceptSelf(int[] nums) {
        int len=nums.length;
        int[] arr=new int[len];
        int prod=1;
        boolean zero=false;
        boolean secondzero=false;
        boolean num=false;

        for(int i=0;i<len;i++){
            if(nums[i]!=0){
                prod*=nums[i];
                num=true;
            }
            else if(!zero) zero=true;
            else secondzero=true;
        }
        if(!num || secondzero) return arr;
        for(int i=0;i<len;i++){
            if(!zero){
                arr[i]=prod/nums[i];
            }
            else if(nums[i]==0)arr[i]=prod;
        }
        return arr;
    }

    public static int[][] merge(int[][] intervals) {
        List<int[]> answer = new ArrayList<>();

        if(intervals.length != 0 || intervals != null){
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

            int start = intervals[0][0];
            int end = intervals[0][1];
            for(int[] i: intervals){
                if(i[0] <= end){
                    end = Math.max(end, i[1]);
                } else {
                    answer.add(new int[]{start,end});
                    start = i[0];
                    end = i[1];
                }
            }
            answer.add(new int[]{start,end});

        }

        return answer.toArray(new int[0][]);
    }
}
