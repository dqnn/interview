package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SearchinRotatedSortedArrayII
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _081SearchinRotatedSortedArrayII {

    /**
     * 81. Search in Rotated Sorted Array II
     * Follow up for "Search in Rotated Sorted Array":
     What if duplicates are allowed?

     1 1 1 3 1

     time : O(logn) (worst : O(n))
     space : O(1);
     * @param A
     * @param target
     * @return
     */

    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) return false;
        int l = 0;
        int r = A.length - 1;
        while (l + 1 < r) {
            int mid = (r - l) / 2 + l;
            if (A[mid] == target) return true;
            if (A[l] == A[mid] && A[mid] == A[r]) {
                l++;
                r--;
            } else if (A[l] <= A[mid]) {
                if (A[l] <= target && target <= A[mid]) r = mid;
                else l = mid;
            } else {
                if (A[mid] <= target && target <= A[r]) l = mid;
                else r = mid;
            }
        }
        if (A[l] == target) return true;
        if (A[r] == target) return true;
        return false;
    }
    //this is interview frinendly
    //thinking process: find a target value in a rotated sorted array, 
    //so its start, end mid may not in one trend, so we need to fiure out a 
    //way how to proceed with binary search
    
    //draw two lines then it is easy to fiure out
    
    //the if/else logic mainly is to narrow down the search scope,
    // we mainly want to know whether we need to move left or right, so one quick
    //way is to detect target is in one trend, like start <= target <= mid, or 
    //mid <= target <= right
    public boolean search2(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return false;
        }
        int l = 0, r = nums.length - 1;
        while(l + 1 < r) {
            int mid = l + (r -l) / 2;
            if (nums[l] == target) return true;
            // handle previous same situation, 
            if(nums[l] < nums[mid]) {
                if (nums[l] <= target && target <= nums[mid]) r = mid;
                else l = mid;
            // this handles the case 2, mid fall into second ascend list
            } else if (nums[l] > nums[mid]) {
                if (nums[r] >= target && target >= nums[mid]) l = mid;
                else r = mid;
            //nums[left] == nums[mid], so we move left one step
            } else l++;
        }
        if (nums[l] == target || nums[r] == target) return true;
        return false;
    }
}
