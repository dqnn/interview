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
    /*
     * sliding windows
     * 
     * we move to right everytime, but then we will shrink with moving left because we want to have a qualified window
     */
    public int minSubArrayLen(int t, int[] A) {
        if(A == null || A.length < 1) return 0;
        
        int l =0,r = 0;
        int sum  =0;
        int res = Integer.MAX_VALUE;
        while(r < A.length) {
            sum += A[r];
            
            while(sum >= t) {
                res = Math.min(res, r -l + 1);
                sum -= A[l];
                l++;
            }
            
            r++;
        }
        
        return res == Integer.MAX_VALUE ? 0 : res;
    }
}
