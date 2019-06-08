package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : IncreasingTripletSubsequence
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 334. Increasing Triplet Subsequence
 */
public class IncreasingTripletSubsequence {
    /**
     * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true

Example 2:

Input: [5,4,3,2,1]
Output: false

     time : O(n)
     space : O(1)

     * @param nums
     * @return
     */
    //Assume we found one number A and another number B that is larger than A. 
    //If we could find a third number C that is larger than B, we can return a true. 
    //So the problem becomes how to update A and B to make them ready for C to be discovered.
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, sedMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) min = num;
            else if (num < sedMin) sedMin = num;
            else if (num > sedMin) return true;
        }
        return false;
    }
    
    // another nice solution
    //interview friendly, 
    //thinking process: given an array, find out whether we can find a increasing sequence, at least 3 elements
    
    //assume a, b, x are increasing, first we mark as MAX, so it can be replaced by first element
    //then b!=a, then we can try to find the biggest one, if not found just means we cannot find
    public boolean increasingTriplet2(int[] nums) {
        if(nums.length<=2) return false;
        int a = Integer.MAX_VALUE, b = a;
        for(int x:nums){
          if(x<a) a=x;
          else if(x>a && x<b) b=x;
          else if(x>a && x>b) return true;
        }
        return false;
    }
}
