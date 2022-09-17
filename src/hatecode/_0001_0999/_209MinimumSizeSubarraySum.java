package hatecode._0001_0999;

/**
 * Date : Aug, 2018
 * Description : 209. Minimum Size Subarray Sum
 */
public class _209MinimumSizeSubarraySum {
    /**
Given an array of n positive integers and a positive integer s, 
find the minimal length of a contiguous 
subarray of which the sum ≥ s. If there isn't one, return 0 instead.

Example: 

Input: s = 7, A = [2,3,1,2,4,3]
Output: 2
Explanation: the subarray [4,3] has the minimal length under the 
problem constraint.
Follow up:
If you have figured out the O(n) solution, try coding another solution of which 
the time complexity is O(n log n). 

     time : O(n)
     space : O(1)

     * @param s
     * @param A
     * @return
     */
    //O(n) because even 2 loops but inside while is not starting from 
    //0 every time, so it is constant time, 
    
    //thinking process, the templates for two pointers
    public int minSubArrayLen(int s, int[] A) {
        // res is the length of subarray
        int res = Integer.MAX_VALUE;
        //left is sliding window left
        int l = 0, sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            // left is before i's position,
            while (l <= i && sum >= s) {
                // we move pointer left to i so res is the shortest length
                res = Math.min(res, i -l + 1);
                // remove from the sum
                sum -= A[l++];
            }
        }
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
