package hatecode._0001_0999;

/**
 * Created by professorX on 25/07/2017.
 */
public class _027RemoveElement {
    /**
     * 27. Remove Element (26, 80)
     * Given an array and a value, remove all instances of that value in place and return the new length.

     Do not allocate extra space for another array, you must do this in place with constant memory.

     The order of elements can be changed. It doesn't matter what you leave beyond the new length.

     Example:
     Given input array nums = [3,2,2,3], val = 3

     Your function should return length = 2, with the first two elements of nums being 2.

     case : [3,2,2,3] 3
             2,2
                 r
                   i
     res : [2,2]
     time : O(n);
     space : O(1);
     * @param nums
     * @param val
     * @return
     */
    // thinking process:
    // the problem to replace element with same value, so we use overwrite, 
    // res is the numbe of count, but also can be used as index
    //when we found one num[i] != target, then num[res] can be position for
    //res like gatekeeper always guard only different value can be there
    public static int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        //always use this way to remove duplicates
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[res++] = nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3, 4};
        removeElement(nums,3);
    }
}
