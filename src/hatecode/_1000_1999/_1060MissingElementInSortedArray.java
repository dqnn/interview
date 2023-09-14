package hatecode._1000_1999;
public class _1060MissingElementInSortedArray {
/*
1060. Missing Element in Sorted Array
Given a sorted array A of unique numbers, 
find the K-th missing number starting from the 
leftmost number of the array.

 

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

        int n = A.length;

        // [1] 1, it will return 3 instead of 2 if we do not return early
        int cnt = A[n-1] - A[0] + 1 - n;
        if( cnt < k) {
            return A[n-1] + k - cnt;
        }
        
        int l = 0, r = A.length - 1;
        //binary search finally will return l/r which is closet what
        //we want
        while (l + 1 < r) {
            int m = l + (r - l) / 2;
            //this means how many missed numbers between 
            //A[mid] and A[l]
            //A[m] - A[l] - 1 means how many numbers between (A[l], A[m]), exclusive
            // m-1 - (l+1)+1 means how many array elments between (A[l], A[m]), so it means how many elements missing
            cnt = A[m] - A[l] - 1  - (m -1 - (l +1) + 1);
            //we cannot return when cnt == A[m] return A[m] -1, because cnt means the total left part missing count of integers.
            if (cnt >= k) r = m;
            else {
                l = m;
                k -= cnt;
            }
        }
        //this means we reached the end, so find something out of array
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
            if(A[m] - m - A[0] >= k) r = m;
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
    
    //brute force
    public int missingElement_BF2(int[] A, int k) {
        
        for(int i = 0; i<A.length - 1; i++) {
            if (A[i] + k < A[i+1]) return A[i] + k;
            else k -= A[i+1] - A[i] - 1;
        }
        
        return A[A.length-1] + k;
    }
    
    
}