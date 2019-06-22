package hatecode;
public class NonDecreasingArray {
/*
665. Non-decreasing Array
Given an array with n integers, your task is to check if it could become non-decreasing by modifying at most 1 element.

We define an array is non-decreasing if array[i] <= array[i + 1] holds for every i (1 <= i < n).

Example 1:
Input: [4,2,3]
Output: True
*/
    
    //interview friendly:
    //thinking process: if the question has the word "most 1", then we should think about use variable
    //prev to record what we done past. 
    //
    public boolean checkPossibility(int[] a) {
        int modified = 0;
        int prev = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] < prev) {
                if (modified++ > 0) return false;
                //this is first change, note in this case, prev = a[i-2], 
                //a[i-2], a[i-1], a[i], 
                if (i - 2 >= 0 && a[i - 2] > a[i]) continue;
            }
            prev = a[i];
        }
        return true;
    }
    public boolean checkPossibility_ChangeInput(int[] nums) {
        //edge case
        if (nums == null || nums.length == 1 || nums.length == 2) {
            return true;
        }
        
        int len = nums.length-1, cnt = 1;
        for(int i = 1; i <= len; i++) {
            if (nums[i] < nums[i-1]) {
                if(cnt == 0) {
                    return false;
                } else if (i == 1 || nums[i] >= nums[i-2]){
                    nums[i-1] = nums[i];
                } else {
                    nums[i] = nums[i-1];
                }
                cnt--;
            }
        }
        return true;
    }
    
    
}