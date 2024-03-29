package hatecode._0001_0999;

/**
 * Created by professorX on 25/07/2017.
 */
public class _026RemoveDuplicatesfromSortedArray {
    /**
     * 26. Remove Duplicates from Sorted Array
     * Given a sorted array, remove the duplicates in place such that each 
     * element appear only once and return the new length.

     Do not allocate extra space for another array, you must do this 
     in place with constant memory.

     For example,
     Given input array nums = [1,1,2],

     Your function should return length = 2, with the first two elements of 
     nums being 1 and 2 respectively. It doesn't matter what you leave beyond 
     the new length.

     case : [1,1,2,2,3,4,5,6]
             1,2,3,4,5,6
                 c
                     i
     result : [1,2,3,4,5,6]

     time : O(n);
     space : O(1);

     * @param A
     * @return
     */
    /*
     * thinking process: O(n)/O(1)
     */
    public static int removeDuplicates(int[] A) {
        if (A == null || A.length == 0) return 0;
        // here count means point to first repeated chars
        int count = 1;
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1] != A[i]) {
                A[count++] = A[i];
            }
        }
        return count;
    }
}
