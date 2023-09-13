package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ThreeSum
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _015ThreeSum {
    /**
     * 15. 3Sum
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.

     Note: The solution set must not contain duplicate triplets.

     For example, given array S = [-1, 0, 1, 2, -1, -4],
     
    [-4, -1,-1,0,1,2]
     A solution set is:
     [
     [-1, 0, 1],
     [-1, -1, 2]
     ]

     time : O(n^2);
     space : O(n);
     * @param A
     * @return
     */
    public static List<List<Integer>> threeSum(int[] A) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(A);
        for (int i = 0; i < A.length - 2; i++) {
            if (i > 0 && A[i] == A[i - 1]) continue;
            int l = i + 1, r = A.length - 1, sum = 0 - A[i];
            while (l < r) {
                if (A[l] + A[r] == sum) {
                    res.add(Arrays.asList(A[i], A[l], A[r]));
                    //we move since we found many dup, like  {-1, 0, 1, 1, 2, -1, -4}
                    while (l < r && A[l] == A[l + 1]) l++;
                    while (l < r && A[r] == A[r - 1]) r--;
                    l++;
                    r--;
                } else if (A[l] + A[r] < sum) {
                    l++;
                } else r--;
            }
        }
        return res;
    }
    
    
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(threeSum(new int[] {-1, 0, 1, 1, 2, -1, -4}).toArray()));
    }
}
