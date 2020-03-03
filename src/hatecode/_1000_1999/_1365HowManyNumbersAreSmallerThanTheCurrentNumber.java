package hatecode._1000_1999;

import java.util.Arrays;

public class _1365HowManyNumbersAreSmallerThanTheCurrentNumber {
/*
1365. How Many Numbers Are Smaller Than the Current Number
Given the array nums, for each nums[i] find out how many numbers in the array are smaller than it. That is, for each nums[i] you have to count the number of valid j's such that j != i and nums[j] < nums[i].

Return the answer in an array.

 

Example 1:

Input: nums = [8,1,2,2,3]
Output: [4,0,1,1,3]
Explanation: 
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3). 
*/
    //thinking process: O(n+m)/O(m) m = ValueRange(A), n = A.length
    
    //the problem is to say given an integer array, for each integer, find 
    //how many integers are smaller than it. 
    //for example, [8,1,2,2,3], return [4,0,1,1,3]
    //so we can sort, and use binary search to find how many before it. 
    
    //following is a better way, so we use each number in a fixed position in a array,so
    //we can easily know how many elements are before this number, 
    //
    
    
    public int[] smallerNumbersThanCurrent(int[] A) {
        if(A ==null || A.length < 1) return new int[0];
        
        int[] count = new int[101];
        int[] res = new int[A.length];
        
        for (int i =0; i < A.length; i++) {
            count[A[i]]++;
        }
        
        for (int i = 1 ; i <= 100; i++) {
            count[i] += count[i-1];    
        }
        
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) res[i] = 0;
            else res[i] = count[A[i] - 1];
        }
        
        return res;    
        
    }
    
    
    public int[] smallerNumbersThanCurrent_BS(int[] A) {
        int[] numsCopy = Arrays.copyOf(A, A.length);
        Arrays.sort(numsCopy);
        int[] res = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            res[i] = binarySearch(numsCopy, A[i]);
        }
        return res;
    }
    private int binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}