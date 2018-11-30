package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duqiang on 28/07/2017.
 */
public class Subsets {
    /**
     * 78. Subsets
     * Given a set of distinct integers, nums, return all possible subsets.

     Note: The solution set must not contain duplicate subsets.

     For example,
     If nums = [1,2,3], a solution is:
     [
     [3],
     [1],
     [2],
     [1,2,3],
     [1,3],
     [2,3],
     [1,2],
     []
     ]

     test : [1,2,3]

     []
     [1]
     [1, 2]
     [1, 2, 3]
     [1, 3]
     [2]
     [2, 3]
     [3]

     1 — 2 - 3
     |   |
     2   3
     |
     3



     time : O(2^n);
     space : O(n);
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }
    
    //this is too elegant
    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
    		// we rely on next visit to add the list into the res, so index > index > nums.length, no =
    	    if (nums == null || index < 0 || index > nums.length) {
            return;
        }
    	    if (list.size() > 0)
    	        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
    // another solution,  for this
    /*
     * While iterating through all numbers, for each new number, we can either pick it or not pick it
1, if pick, just add current number to every existing subset.
2, if not pick, just leave all existing subsets as they are.
We just combine both into our result.

For example, {1,2,3} intially we have an emtpy set as result [ [ ] ]
Considering 1, if not use it, still [ ], if use 1, add it to [ ], so we have [1] now
Combine them, now we have [ [ ], [1] ] as all possible subset

Next considering 2, if not use it, we still have [ [ ], [1] ], if use 2, just add 2 to each previous subset, we have [2], [1,2]
Combine them, now we have [ [ ], [1], [2], [1,2] ]

Next considering 3, if not use it, we still have [ [ ], [1], [2], [1,2] ], if use 3, just add 3 to each previous subset, we have [ [3], [1,3], [2,3], [1,2,3] ]
Combine them, now we have [ [ ], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3] ]
     */
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        if (nums == null || nums.length < 1) {
            return res;
        }
        
        for(int i = 0; i <= nums.length - 1; i++) {
            int size = res.size();
            for(int j = 0;  j < size; j++) {
                List<Integer> list = new ArrayList<>(res.get(j));
                list.add(nums[i]);
                res.add(list);
            }
        }
        
        return res;
    
    }
    
    public static void main(String[] args) {
        int[] input = {1,2,3};
        System.out.println(subsets(input));
    }
}
