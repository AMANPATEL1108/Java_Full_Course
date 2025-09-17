import javax.swing.text.html.Option;
import java.util.*;

public class Day_Ten {
    public static void main(String[] args) {
        int[] a={3,3};
        int target=6;
//        System.out.println(Arrays.toString(twoSum(a,target)));
        System.out.println(isAnagram("rat","car"));

    }
    public static boolean isAnagram(String s, String t) {
        char[] a=new char[s.length()];
        char[] b=new char[s.length()];
        if(t.length()!=s.length()){
            return false;
        }
        for(int i=0;i<s.length();i++){
            a[i]=s.charAt(i);
        }
        for(int i=0;i<t.length();i++){
            b[i]=t.charAt(i);
        }

        Arrays.sort(a);
        Arrays.sort(b);

        for(int i=0;i<a.length;i++){
            if (a[i]!=b[i]){
                return false;
            }
        }
        return true;

    }




    public static int[] twoSum(int[] nums, int target) {
        int[] index = new int[2];

        for (int i = 0; i < nums.length; i++) {

            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i!=j) {
                    index[0] = i;
                    index[1] = j;
                    return index;

                }
            }
        }
        return index;
    }
}
