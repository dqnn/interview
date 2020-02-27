package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FourSum
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 18. 4Sum
 */
public class _018FourSum {

    /**
     time : O(n^3);
     space : O(n);
     * @param nums
     * @param target
     * @return
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int low = j + 1, high = nums.length - 1;
               // we continue to find the left two numbers
                while (low < high) {
                    int sum = nums[i] + nums[j] + nums[low] + nums[high];
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[low], nums[high]));
                        while (low < high && nums[low] == nums[low + 1]) low++;
                        while (low < high && nums[high] == nums[high - 1]) high--;
                        low++;
                        high--;
                    } else if (sum < target) {
                        low++;
                    } else high--;
                }
            }
        }
        return res;
    }
    
    // first two loops we can get factors
    // then we use two pointers to get them
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        //edge case
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length < 4) {
            return result;
        }
        
        if (nums.length == 4) {
            if (nums[0] + nums[1] + nums[2] + nums[3] == target) {
                result.add(new ArrayList<>(Arrays.asList(new Integer[]{nums[0], nums[1], nums[2], nums[3]})));    
            }
            return result;
        }
        
        Arrays.sort(nums);
        Set<List<Integer>> tempSet = new HashSet<>();
        int len = nums.length -1;
        for(int i =0; i<=len -3; i++) {
            for(int j = i+1; j<=len-2; j++) {
                int fTwoSum = target - (nums[i] + nums[j]);
                int p = j+1, pL =len;
                // two pointers
                while(p < pL) {
                    int sum = nums[p] + nums[pL];
                    if(sum == fTwoSum) {
                        tempSet.add(Arrays.asList(new Integer[]{nums[i], nums[j], nums[p], nums[pL]}));
                        pL--;
                    } else if(sum > fTwoSum){
                        pL--;
                    } else {
                        p++;
                    }
                }
            }
            
        }
        result.addAll(tempSet);
        return result;    
    }
}
