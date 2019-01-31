package hatecode;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FindtheDuplicateNumber
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 287. Find the Duplicate Number
 */
public class FindtheDuplicateNumber {
    /**
     * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
     * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
     * find the duplicate one.

     1 2 3 4 5 6  7 8 8 9 10
     0 1 2 3 4 5  6 7 8 9 10  len = 11

     https://segmentfault.com/a/1190000003817671


     * @param nums
     * @return
     */

    // time : O(nlogn) space : O(1)
    public int findDuplicate(int[] nums) {
        int min = 0;
        int max = nums.length - 1;
        while (min <= max) {
            int mid = (max - min) / 2 + min;
            int count = 0;
            // here count how many numbers are small than mid, 
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            // here means the duplicate number is on the left side
            if (count > mid) {
                max = mid - 1;
            // here means on the right side
            } else {
                min = mid + 1;
            }
        }
        return min;
    }

    // time : O(n) space : O(1)
    /*
slowPointer distance before meeting =x+y
fastPointer distance before meeting =(x+y+z)+y = x + 2y + z

2x+2y=x+2y+z => x = z
|---->x<----|----->y<------|
-------------
            |----->Z<------| meet here
     */
    public int findDuplicate2(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return - 1;
        }
        
        int slow = nums[0];
        int fast = nums[nums[0]];
        // 1,3,2,4,5,2 so 2,4,5,2 will be a circle, and if they are circle so fast will finally meet slow. 
        // 
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        // so fast have same pace with slow, then slow continue the another half of the circle, they will meet at the circl start again. 
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
    
    // O(n), O(n)
    public int findDuplicate3(int[] nums) {
        //edge case
        if (nums == null || nums.length < 1) {
            return - 1;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for(Integer n: nums) {
            if (map.containsKey(n)) {
                return n;
            } else {
                map.put(n, 1);
            }
        }
        return -1;
    }
}
