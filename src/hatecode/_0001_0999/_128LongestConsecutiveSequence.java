package hatecode._0001_0999;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestConsecutiveSequence
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 128. Longest Consecutive Sequence
 */
public class _128LongestConsecutiveSequence {
    /**
Given an unsorted array of integers, 
find the length of the longest consecutive elements sequence.

Your algorithm should run in O(n) complexity.

Example:

Input: [100, 4, 200, 1, 3, 2]
Output: 4
Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. 
Therefore its length is 4.

     Your algorithm should run in O(n) complexity.

     time : O(n)
     space : O(n)

     * @param nums
     * @return
     */
  //O(n)/O(n)
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int down = nums[i] - 1;
            while (set.contains(down)) {
                set.remove(down);
                down--;
            }
            int up = nums[i] + 1;
            while (set.contains(up)) {
                set.remove(up);
                up++;
            }
            res = Math.max(res, up - down - 1);
        }
        return res;
    }
    //O(n)/O(n), worst is O(n^2)/O(n), think about the two loops, there are not full ones, and second one are always decreased,
    //
    //if the array already sorted, then we can climb the ladder in the for while loop, 
    //this is more like we look for the consective sequence starting points
    public int longestConsecutive_SameAsAbove(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            //because we have to go through every one
            if(set.contains(nums[i] - 1)) continue;
            int next = nums[i] + 1;
            while(set.contains(next)) {
                next += 1;
            }
            res = Math.max(res, next - nums[i]);
        }
        return res;
    }
    // we are using hashMap to store this
   //thinking process: for each number m, we want to see know its m - 1 and m + 1 whether 
    //existed in the map or not, 
    public int longestConsecutive2(int[] n) {
        if (n == null || n.length < 1) {
            return 0;
        }
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        //key is the the number in nums, value is to say how many consective numbers in the 
        //sequence which contains the current nums
        //the boundary points of the sequence. 
        //For example, as a result, for sequence {1, 2, 3, 4, 5}, 
        //map.get(1) and map.get(5) should both return 5.
        for(int m : n) {
            // we do not care duplicate number
            if (map.containsKey(m)) continue;
            //one step by step, we get the boundary
            int left = map.containsKey(m - 1) ? map.get(m - 1) : 0;
            int right = map.containsKey(m + 1) ? map.get(m + 1) : 0;
            int sum = left + right + 1;
            //why res compare to sum?  becz if left or right exists, then 
            //sum should be bigger, 
            res = Math.max(res, sum);
            //we put m point to sum, so next time query, this means for integer m was a size = sum sub array
            map.put(m, sum);
            // [100, 4, 200, 1, 3, 2]
            // when reaches to 2 finally, left = map.get(1) = 1, map.get(3) = 2 becz 3,4
            // so sum = 1 + 2 + 1 = 4, we update 1, 4, and 4, 4, right here means the length, 
            //left is the same
            //expand the m boundary
            map.put(m - left, sum);
            map.put(m + right, sum);
        }
        
        return res;
    }
}
