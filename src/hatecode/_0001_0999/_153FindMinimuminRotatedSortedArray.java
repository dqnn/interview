package hatecode._0001_0999;

/**

 * Description : TODO
 */
public class _153FindMinimuminRotatedSortedArray {
    /**
     * 153. Find Minimum in Rotated Sorted Array
     * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
     *
     4 5 6 7 0 1 2

     4 5 6 0 1 2 3

     2 1

     * time : O(logn)
     * space : O(1);
     * @param A
     * @return
     */
    /*
     * thinking process: O(lgn)/O(1)
     */
    public int findMin(int[] A) {
        if (A == null || A.length == 0) return -1;
        int start = 0;
        //templates for binary search
        int end = A.length - 1;
        // the exit condition is < end, not start < end
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            // original array is rotated to be placed in the first several positions, 
            //if we found mid elements still smaller than end, which means mid must be in first part
            // so we narrow down the search scope
            if (A[mid] < A[end]) {
                end = mid;
            } else {
                // or mid should be in first part, so narrow down the search scope
                start = mid + 1;
            }
            
            /* this also works
             * if(A[l] < A[mid] && A[mid] > A[r]) {
                l = mid;
            } else r = mid;
             */
        }
        // the position returned is start, 
        if (A[start] < A[end]) return A[start];
        else return A[end];
    }
    
    //O(n), O(1)
    public int findMin2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        // we just get first elments, so whole arrays can be divided into two parts, 
        // both of them are inceasing, nums[0] must be smallest at first part, 
        // so if we can find smaller in array which means the beginning of second part.
        int pre = nums[0]; 
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] < pre) {
                return nums[i];
            }
        }
        return pre;
    }
}
