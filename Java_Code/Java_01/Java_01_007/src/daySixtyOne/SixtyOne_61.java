package daySixtyOne;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SixtyOne_61 {
    public static void main(String[] args) {
        int[] n = {1, 5, 1, 1, 6, 4};
        wiggleSort(n);
        int a = 2;
        int[][] b = {{1, 0}};
        System.out.println(findOrder(a, b));
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Queue<Integer> q = new LinkedList<>();
        int indeg[] = indegree(prerequisites, numCourses);
        int ans[] = new int[numCourses];
        for (int i = 0; i < indeg.length; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;

        while (!q.isEmpty()) {
            int r = q.poll();
            ans[count] = r;
            count++;

            for (int i[] : prerequisites) {
                if (i[1] == r) {
                    indeg[i[0]]--;
                    if (indeg[i[0]] == 0) {
                        q.add(i[0]);
                    }
                }
            }
        }

        if (count == numCourses) {
            return ans;
        }
        return new int[0];
    }

    public static int[] indegree(int pre[][], int n) {
        int in[] = new int[n];
        for (int i[] : pre) {
            in[i[0]]++;
        }
        return in;
    }

    public static void wiggleSort(int[] nums) {
        int n = nums.length - 1;
        int[] newarr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newarr);
        for (int i = 1; i < nums.length; i += 2)
            nums[i] = newarr[n--];
        for (int i = 0; i < nums.length; i += 2)
            nums[i] = newarr[n--];
    }
}
