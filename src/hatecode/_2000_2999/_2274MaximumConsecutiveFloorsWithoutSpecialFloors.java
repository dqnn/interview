package hatecode._2000_2999;

import java.util.*;
public class _2274MaximumConsecutiveFloorsWithoutSpecialFloors {
/*
2274. Maximum Consecutive Floors Without Special Floors
Alice manages a company and has rented some floors of a building as office space. Alice has decided some of these floors should be special floors, used for relaxation only.

You are given two integers bottom and top, which denote that Alice has rented all the floors from bottom to top (inclusive). You are also given the integer array special, where special[i] denotes a special floor that Alice has designated for relaxation.

Return the maximum number of consecutive floors without a special floor.

 

Example 1:

Input: bottom = 2, top = 9, special = [4,6]
Output: 3
*/
    //thinking process: O(n)/O(1)
    //the problem is to say: find the biggest distance inside a unsorted array
    public int maxConsecutive(int bottom, int top, int[] A) {
        Arrays.sort(A);
        int left = A[0] - bottom, right = top - A[A.length -1];
        
        int res = Math.max(left, right);
        
        for(int i = 0; i< A.length- 1; i++) {
            res = Math.max(res, A[i+1] - A[i] - 1);
        }
        
        return res;
    }
}