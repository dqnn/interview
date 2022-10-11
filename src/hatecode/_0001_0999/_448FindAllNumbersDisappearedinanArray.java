package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : 448. Find All Numbers Disappeared in an Array
 */
public class _448FindAllNumbersDisappearedinanArray {
    /**
     * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

     Find all the elements of [1, n] inclusive that do not appear in this array.

     Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

     Example:

     Input:
     [4,3,2,7,8,2,3,1]

     Output:
     [5,6]

     time : O(n)
     space : O(n)

     * @param A
     * @return
     */

    public List<Integer> findDisappearedNumbers(int[] A) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int index = Math.abs(A[i]) - 1;
            if (A[index] > 0) {
                A[index] = -A[index];
            }
        }
        //[4,3,2,7,8,2,3,1] -->[-4, -3, -2, -7, 8, 2, -3, -1]
        // so the disappeared num is the index + 1 we did not set the value
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                res.add(i + 1);
            }
        }
        return res;
    }
}
