package hatecode._0001_0999;
public class _713SubarrayProductLessThanK {
/*
713. Subarray Product Less Than K
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example 1:
Input: nums = [10, 5, 2, 6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.

*/
    //thinking process: 
    
    //given an array, integer k, find out how many subarray which the product is less than K
    
    //arithemtic subarray, the same as that one
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) return 0;
        
        int cnt = 0;
        int pro = 1;
        for(int i =0, j =0; j < nums.length; j++) {
            pro *= nums[j];
            while(i <=j && pro >= k) {
                pro /= nums[i++];
            }
            cnt +=j - i +1;
        }
        return cnt;
    }
}