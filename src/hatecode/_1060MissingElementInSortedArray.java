package hatecode;
public class _1060MissingElementInSortedArray {
/*
1060. Missing Element in Sorted Array
Given a sorted array A of unique numbers, find the K-th missing number starting from the leftmost number of the array.

 

Example 1:

Input: A = [4,7,9,10], K = 1
Output: 5
*/
    //thinking process:O(lgn) /O(1)
    //the problem is to say: given an array A and integer k, find out
    //the k-th missing number,
    //[4,7,9,10], 1, first missing is 5, 
    //[4,7,9,10], 3, first missing is 8,
    
    //so suppose all filled missing numbers into array, so it will be a search
    //problem, then
    public int missingElement(int[] A, int k) {
        if(A == null || A.length < 1) return 0;
        
        int l = 0, r = A.length - 1;
        int mid = 0;
        while (l + 1 < r) {
            mid = l + (r - l) / 2;
            //this means how many missed numbers between 
            //A[mid] and A[l]
            //A[mid] - A[l]，how many numbers 
            //mid -l, how many numbers in A,
            int cnt = A[mid] - A[l] - (mid - l);
            if (cnt >= k) {
                r =mid;
            } else {
                l = mid;
                k -= cnt;
            }
        }
        
        if (A[l] + k >= A[r]) {
            return A[l] + k + 1;
        }
        return A[l] + k;
    }
    //not understood
    public int missingElement_Best(int[] A, int k) {
        if(A == null || A.length < 1) return 0;
        
        int l = 0, r = A.length;
        while(l < r) {
            int m = l + (r - l) / 2;
            if(A[m] - m - k >= A[0]) r = m;
            else l = m + 1;
        }
        return A[0] + l + k - 1;
    }
    
    public int missingElement_BF(int[] nums, int k) {
         int start = nums[0];
         int end = nums[nums.length-1];
        if (k>(end-start-nums.length+1)) {
            return start+nums.length+k-1;
        }
        
        for (int i=1; i<nums.length-1; i++) {   
            int t = k;
            k -= (nums[i] - nums[i-1] - 1);
            if (k<=0) {
                return nums[i-1]+t;
            }
        }
        return end+k-1;
    }
    
    
}