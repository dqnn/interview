package hatecode._0001_0999;
public class _410SplitArrayLargestSum {
/*
410. Split Array Largest Sum
Given an array nums which consists of non-negative 
integers and an integer m, you can split the array into m 
non-empty continuous subarrays.

Write an algorithm to minimize the largest sum among these m 
subarrays.

 

Example 1:

Input: nums = [7,2,5,10,8], m = 2
Output: 18
*/
    //thinking process: O(nlgn)/O(1)
    public int splitArray(int[] A, int k) {
       int l = 0, r = 0;
       for(int a : A) {
           l = Math.max(l, a);
           r += a;
       }
        //System.out.println(l + "----" + r);
       
       while(l < r) {
           int m = l + (r - l)/2;
           if (helper(A, m, k)) {
               r = m;
               
           } else l = m + 1;
       }
        
       return l;
    }
    
    
    private boolean helper(int[] A, int m, int k) {
        int res = 1;
        int sum = 0;
        for(int a : A) {
            if (sum + a > m) {
                sum = a;
                res++;
            } else sum += a;
        }
        
        return res <= k;
    }
}