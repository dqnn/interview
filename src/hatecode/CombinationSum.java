package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode Package Name : leetcode File Name : CombinationSum
 * Creator : duqiang Date : Aug, 2018 Description : TODO
 */
public class CombinationSum {

    /**
     * 39. Combination Sum
     * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
     * find all unique combinations in C where the candidate numbers sums to T.

     The same repeated number may be chosen from C unlimited number of times.

     Note:
     All numbers (including target) will be positive integers.
     The solution set must not contain duplicate combinations.
     For example, given candidate set [2, 3, 6, 7] and target 7,
     A solution set is:
     [
     [7],
     [2, 2, 3]
     ]

     time : O(2^n)
     space : O(n)
     * @param candidates
     * @param target
     * @return
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return res;
        helper(res, new ArrayList<>(), candidates, target, 0);
        return res;
    }

    // this is templates for writing permutation
    public void helper(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if (target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            //note last index is i so we can re-pick the index
            helper(res, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1); 
            //this is to remove last one so we can add next to the list, like 2, 2, 2-->2,2-->2,2,3
        }
    }
}
