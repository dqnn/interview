package hatecode._0001_0999;

/**
 * Created by duqiang on 23/07/2017.
 */
public class TwoSumII {

    /**
     * 167. Two Sum II - Input array is sorted

     Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.

     The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

     You may assume that each input would have exactly one solution and you may not use the same element twice.

     Input: numbers={2, 7, 11, 15}, target=9
     Output: index1=1, index2=2

     time : O(logn)
     space : O(1)
     * @param numbers
     * @param target
     * @return
     */
    //interview friendly:
    
    //TP templates, simple ones
    public int[] twoSum(int[] A, int target) {
        //edge case
        if (A == null || A.length <= 1) return new int[] {-1, -1};
        
        int l = 0, r = A.length -1;
        while(l < r) {
            int sum = A[l] + A[r];
            if (sum == target) return new int[]{l+1, r+1};
            else if(sum < target) l++;
            else r--;
        }
        
        return new int[]{-1,-1};
        
       
    }
}
