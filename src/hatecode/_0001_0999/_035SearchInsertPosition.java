package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SearchInsertPosition
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _035SearchInsertPosition {

    /**
     * 35. Search Insert Position
     * Given a sorted array and a target value, return the index if the target 
     * is found. If not, return the index where it would be if it were inserted 
     * in order.

     You may assume no duplicates in the array.

     Here are few examples.
     [1,3,5,6], 5 → 2
     [1,3,5,6], 2 → 1
     [1,3,5,6], 7 → 4
     [1,3,5,6], 0 → 0

     __target___target

     time : O(logn);
     space : O(1);
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (target == nums[mid]) return mid;
            else if (target < nums[mid]) end = mid;
            else start = mid;
        }
        //here is the key, last it would be start + 1 == end
        if (target <= nums[start]) {
            return start;
        } else if (target <= nums[end]) {
            return end;
        } else {
            return end + 1;
        }
    }

    /*
     * 2nd template, so it needs post-processing to handle the the element last
     */
    public int searchInsert2(int[] A, int t) {
        int l = 0, r = A.length - 1;
        
        while(l < r) {
            int m = l + (r - l)/2;
            if (A[m] == t) return m;
            else if (A[m] > t) r = m;
            else l = m + 1;
        }
        
        if(A[l] >= t) return l;
        else return l + 1;
    }
}
