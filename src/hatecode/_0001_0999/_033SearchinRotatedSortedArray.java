package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SearchinRotatedSortedArray
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class _033SearchinRotatedSortedArray {

    /**
     * 33. Search in Rotated Sorted Array
     * Suppose an array sorted in ascending order is rotated at some pivot 
     * unknown to you beforehand.

     (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

     You are given a target value to search. If found in the array return its index, 
     otherwise return -1.

     You may assume no duplicate exists in the array.

     4 5 6 7 0 1 2

     4 5 6 0 1 2 3

     time : O(logn);
     space : O(1);
     * @param nums
     * @param target
     * @return
     */
    //interview friendly
    // thinking process:
    // so the array is like way up and way down, eg, 5,6,7,8,1,2,3
    // mid has two cases: 1 is in 5,6,7,8 another is in 1,2,3 range, 
    //so we need to have  if else to handle the situation, suppose 
    //mid is in 5,6,7,8, target also have two cases
    //one is in 5,6,7,8 another one is in 1,2,4 if it is in 5,6,7,8, 
    //we need to move end = mid
    //else move left = mid since we want to narrow down the range
    
    //the reason why we have to use two && condition to check which range is the target because since
    //it is roated, we have to make sure it is in one single asc/desc range
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int l = 0;
        int r = nums.length - 1;
        while (l + 1 < r) {
            int mid = (r - l) / 2 + l;
            if (nums[mid] == target) return mid;
          //which means we are in ascend sequence maybe in first or second part
            if (nums[l] < nums[mid]) {
                // 5,6,7,8,1,2,3 left = 5 mid = 8, target is 2 so we want to 
                //make sure target is between left and mid because we want to 
                if (nums[l] <= target && target <= nums[mid]) r = mid;
                 //not here we only have > or < no equals
                else l = mid;
            //which means we are in descend sequence maybe partially 
            // but we examine from mid to end and move start to mid
            //nums[start] >= nums[mid]
            } else {
                if (nums[mid] <= target && target <= nums[r]) l = mid;
                else r = mid;
            }
        }
        if (nums[l] == target) return l;
        if (nums[r] == target) return r;
        return -1;
    }
    
    // another solutions
    public int search2(int[] nums, int target) {
        int n = nums.length;
        int lo = 0, hi = nums.length - 1;
        // find the index of the smallest value using binary search.
        // Loop will terminate since mid < hi, and lo or hi will shrink by at least 1.
        // Proof by contradiction that mid < hi: if mid==hi, then lo==hi and loop would
        // have been terminated.
        while (lo < hi) {
            int mid = lo + (hi -lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        // lo==hi is the index of the smallest value and also the number of places
        // rotated.
        int rot = lo;
        lo = 0;
        hi = n - 1;
        // The usual binary search and accounting for rotation.
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realmid = (mid + rot) % n;
            if (nums[realmid] == target)
                return realmid;
            if (nums[realmid] < target)
                lo = mid + 1;
            else
                hi = mid - 1;
        }
        return -1;
    }
}
