package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * File Name : PermutationsII
 * Date : Sep, 2018
 * Description : TODO
 */
public class _047PermutationsII {
    /**
     * 47. Permutations II
Given a collection of numbers that might contain duplicates, 
return all possible unique permutations.

Example:

Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


     * @param nums
     * @return
     */
    // time : O(n!) space : O(n);
    
    //the difference compared to first one here asked no same entry in result set. 
    //so we would like to sort them first, so same value will grouped together, then 
    //we would consider same value should be result set together, like 
    //
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return  res;
        // we have to sort first since unique permutations
        Arrays.sort(nums);
        helper(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    // so we used used to mark where we have visited, 
    //and use backtracking templates to get correct answer
    public void helper(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
        }
        for (int i = 0; i < nums.length; i++) {
            //so this means 
            //1. already used skip.
            //2. if it is same as previous and previous was used then we can use it
            
            //if we change the !used[i - 1] to used[i - 1], it also works but this will introduce more
            //branches in the recursive tree because because it means we will use previous not used,  the 
            //tree has deeper tree height, higher on time and space complexity
            //see !used[i - 1]: https://ibb.co/k4zv00 
            //see used[i - 1]: https://ibb.co/ncMm7f
            if (used[i] || i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) continue;
            //below also works, the only difference is that above is asc order, below is desc order
            //if(used[i] || i > 0 && nums[i]== nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            list.add(nums[i]);
            helper(res, list, nums, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }

    // time : O(n! * n) space : O(n);
    public List<List<Integer>> permuteUnique2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return  res;
        Arrays.sort(nums);
        helper2(res, nums, 0);
        return res;
    }
    public void helper2(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            res.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (isUsed(nums,start, i)) continue;
            swap(nums, start, i);
            helper2(res, nums, start + 1);
            swap(nums, start, i);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public boolean isUsed(int[] nums, int i, int j) {
        for (int x = i; x < j; x++) {
            if (nums[x] == nums[j]) return true;
        }
        return false;
    }
}
