package hatecode._0001_0999;
public class _540SingleElementInASortedArray {
/*
540. Single Element in a Sorted Array
Given a sorted array consisting of only integers where every element 
appears twice except for one element which appears once. 
Find this single element that appears only once.

Example 1:
Input: [1,1,2,3,3,4,4,8,8]
Output: 2
Example 2:
Input: [3,3,7,7,10,11,11]
Output: 10
*/
    //thinking process: O(logN)/O(1)
    //if it is sorted, then BS is on the plate, whole length must be odd number
    //so if mid is even, 
    public int singleNonDuplicate_Standard(int[] A) {
        if (A == null || A.length < 1)
            return 0;

        int n = A.length;
        //this binary search templates is tricky, 
        int l = 0, r = n - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            int temp = mid % 2 == 0 ? mid + 1 : mid - 1;
            if (A[mid] == A[temp]) {
                // if mid is even, then nums[mid] = nums[mid + 1], single number is >= mid + 2
                // if mid is odd, then nums[mid] = nums[mid - 1], single number is >= mid + 1
                // so we choose mid + 1
                l = mid + 1;
            } else {
                // maybe nums[hi] is the single numer or
                // maybe the single number is to the left of nums[hi]
                // <= hi
                r = mid;
            }
        }
        return A[l];
    }
    
    public int singleNonDuplicate(int[] nums) {
        // binary search
        int n=nums.length, l=0, r=n/2;
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[2*m]!=nums[2*m+1]) r = m;
            else l = m+1;
        }
        return nums[2*l];
    }
    //XOR solutions
    public int singleNonDuplicate_XOR(int[] nums) {
        int res = 0;
        for(int i : nums) res = res ^ i;
        
        return res;
    }
}