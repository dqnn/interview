package hatecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by duqiang on 28/07/2017.
 */
public class SubsetsII {
    /**
     * 90. Subsets II
     * Given a collection of integers that might contain duplicates, nums, 
     * return all possible subsets.

     Note: The solution set must not contain duplicate subsets.


     For example,
     If nums = [1,2,2], a solution is:

     [
     [2],
     [1],
     [1,2,2],
     [2,2],
     [1,2],
     []
     ]

     test: [1,2,2]


     time : O(2^n);
     space : O(n);
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        helper(res, new ArrayList<>(), nums, 0);
        return res;
    }
    //if we did not return after adding to result which means 
    // we would have permutations
    public static void helper(List<List<Integer>> res, List<Integer> list, int[] nums, int index) {
        if (index > nums.length) {
            return;
        }
        res.add(new ArrayList<>(list));
        for (int i = index; i < nums.length; i++) {
            //first 2 will not considered as duplicated
            if (i != index && nums[i] == nums[i - 1]) continue;
            list.add(nums[i]);
            helper(res, list, nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
    // try to use Math way to add it, see explain in SubSets.java
    public List<List<Integer>> subsetsWithDup2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        if (nums == null || nums.length < 1) {
            return res;
        }
        
        for (int i = 0; i <= nums.length - 1; i++) {
            int size = res.size();
            for (int j = 0; j <= size - 1; j ++) {
                List<Integer> subset = new ArrayList<>(res.get(j));
                subset.add(nums[i]);
                Collections.sort(subset);
                res.add(subset);
            }
        }
        Set<List<Integer>> temp = new HashSet<>(res);
        return new ArrayList<>(temp);
        
    }
    
    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[] {1,2,2}));
    }
}
