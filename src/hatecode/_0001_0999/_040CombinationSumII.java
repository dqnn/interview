package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project Name : Leetcode Package Name : leetcode File Name : CombinationSumII
 * Creator : professorX Date : Aug, 2018 Description : TODO
 */
public class _040CombinationSumII {

    /**
     * 40. Combination Sum II
     * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

     Each number in C may only be used once in the combination.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [10, 1, 2, 7, 6, 1, 5] and target 8,
     A solution set is:
     [
     [1, 7],
     [1, 2, 5],
     [2, 6],
     [1, 1, 6]
     ]

     [1,1,2,5,6,7,10]
     [[1,1,6],[1,2,5],[1,7],[2,6]]

     time : O(2^n);
     space : O(n);
     * @param A
     * @param target
     * @return
     */

    public List<List<Integer>> combinationSum2(int[] A, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (A == null || A.length == 0) return res;
        Arrays.sort(A);// why we sort
        helper(res, new ArrayList<>(), A, target, 0);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> list, int[] A, int target, int start) {
        if (start > A.length || target < 0) {
            return; //we have to have this because we may have extra computation
        }
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        
        for (int i = start; i < A.length; i++) {
            if (i != start && A[i] == A[i - 1]) continue;//duplicate situations
            list.add(A[i]);
            helper(res, list, A, target - A[i], i + 1); // so the index would be i if allows duplicate
                                                                          // ones
            list.remove(list.size() - 1);
        }
    }
}
