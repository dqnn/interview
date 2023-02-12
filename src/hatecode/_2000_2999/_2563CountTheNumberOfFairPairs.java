public class _2563CountTheNumberOfFairPairs {
/*
2563. Count the Number of Fair Pairs

Given a 0-indexed integer array nums of size n and two integers lower and upper, return the number of fair pairs.

A pair (i, j) is fair if:

0 <= i < j < n, and
lower <= nums[i] + nums[j] <= upper
 

Example 1:

Input: nums = [0,1,7,4,4,5], lower = 3, upper = 6
Output: 6
Explanation: There are 6 fair pairs: (0,3), (0,4), (0,5), (1,3), (1,4), and (1,5).
*/

/*
   thinking process : O(nlgn)/O(1)
   the problem is to say: given an integer array with lower and upper integer, find how many pairs
   nums = [0,1,7,4,4,5], lower = 3, upper = 6, there will be 6

   so BF force will be two loops, O(n^2)/O(1), 
   
   since it require pair, so if we fix one number, then we just need to looks for how many numbers 
   in the array fit the requirements, 

   then we can think about BS. 

*/
    
    public long countFairPairs2(int[] A, int l, int u) {
        int n = A.length;
        if (n == 1) return 0;
        
        Arrays.sort(A);
        
        
        if (A[n-2] + A[n-1] <l || A[0] + A[1] > u) return 0;
        
        long res = 0;
        int s = 0;
        
        for(int i = 0; i<n-1; i++) {
            int left = findLeft(A, i + 1, n-1, l - A[i]);
            int right = findRight(A, i + 1, n-1, u - A[i]);
            //System.out.println("A[i]="+A[i] +"---left=" + left + "--right=" + right);
            if (-1 != left && -1 != right)
                res += right - left + 1;
        }
        
        return res;
        
    }
    //[0,1,4,4,5,7]
    /*
    find the most left number which are equal or great than least
    */
    private int findLeft(int[] A, int l, int r, int least) {
        
        if (A[r] < least) return -1;
        while(l < r) {
            int m = l + (r - l)/2;
            if (A[m] >= least) r = m;
            else l = m + 1;
        }
        
        return l;
    }
    
     /*
    find the most right number which are equal or less than most
    */
    private int findRight(int[] A, int l, int r, int most) {
        
        if (A[l] > most) return -1;
        
        while(l + 1 < r) {
            int m = l + (r - l)/2;
            if (A[m] <= most) l = m ;
            else r = m;
        }
        
        if (A[r] <= most) return r;
        else return l;
    }
}