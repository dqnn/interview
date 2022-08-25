package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ThreeSumClosest
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 16. 3Sum Closest
 */
public class _016ThreeSumClosest {
    /**
     * For example, given array S = {-1 2 1 -4}, and target = 1.

     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

     time : O(n^2);
     space : O(1);

     * @param nums
     * @param target
     * @return
     */
    //thnking process:
    //the problem is get the closet sum to the target
    
    //so brute force is O(n^3) then we can improve can sort first then we can 
    //use two pointers to move the end and start
    public int threeSumClosest(int[] A, int target) {
        Arrays.sort(A);
        
        int res = A[0] + A[1] + A[A.length-1];
        
        for(int i = 0; i< A.length; i++) {
            int s = i +1, e= A.length -1;
            while(s < e) {
                int sum = A[i] + A[s] + A[e];
                if (sum > target) e--;
                else if (sum < target) s++;
                else return target;
                
                if (Math.abs(res - target) > Math.abs(sum - target)) {
                    res = sum;
                }
            }
        }
        
        return res;
    }
}
