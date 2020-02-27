package hatecode._0001_0999;

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
public class _287FindtheDuplicateNumber {
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
    //the array is from 1->n, contious, some number will be dup, may or may not be once, 
    //
    public int findDuplicate(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while (l <= r) {
            int mid = (r - l) / 2 + l;
            int count = 0;
            // here count how many numbers are small than mid, 
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) {
                    count++;
                }
            }
            // here means the duplicate number is on the left side
            if (count > mid) r = mid - 1;
            // here means on the right side
            else l = mid + 1;
        }
        return l;
    }

    // time : O(n) space : O(1)
    /* interview friendly: 
slowPointer distance before meeting =x+y
fastPointer distance before meeting =(x+y+z)+y = x + 2y + z
actually fast point is running x + k * y + (k-1) * z, set k =2 

2x+2y=x+2y+z => x = z
|---->x<----|----->y------>|
-------------              |
            |----->Z<------|meeting point 
     */
    public int findDuplicate2(int[] A) {
        //edge case
        if (A == null || A.length < 1) {
            return - 1;
        }
        
        int slow = A[0];
        int fast = A[A[0]];
        // 1,3,2,4,5,2 so 2,4,5,2 will be a circle, and if they are circle so fast will finally meet slow. 
        // 
        while(slow != fast) {
            slow = A[slow];
            fast = A[A[fast]];
        }
        //fast reset to start
        fast = 0;
        // so fast have same pace with slow, then slow continue the another half of the circle, 
        //they will meet at the circle start again. 
        while (slow != fast) {
            slow = A[slow];
            fast = A[fast];
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
/*
 * from Google interview doc, not from LC
 * 给定一个没有排序的随机数组，使用二分法找数组里的各个元素，返回有多少元素是用二分法永远找不到的。
 * 要求算法复杂度为O(n),
 * 
 * 可以用类似于判断树是否为BST的思路：维护一个“值域”R=(lo, hi)（即在当前搜索下标范围[i, j]内，
 * 只有在R范围内的数才有可能被二分法找到）。求得当前搜索范围[i, j]的中点mid。如果A[mid]在当前的R中，
 * 那A[mid]就一定能被找到。然后分别递归mid的左右两侧[i, mid-1]和[mid+1, j]。递归左侧时，
 * 允许的值域是(lo, A[mid])；同理右侧的值域是(A[mid], hi)。如果任何时刻发现值域R已经不包含任何整数，
 * 那就表示[i, j]这个下标范围内没有任何能找到的数字
 */
    public int find(int[] nums) {
        // do some sanity check operations
        return dfs(nums, 0, nums.length - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int dfs(int[] nums, int left, int right, int lower, int upper) {
        // base case: there should be no elements between (lower, upper) or (left,right)
        if (lower >= upper) return right - left + 1;
        if (left > right) return 0;

        int mid = left + (right - left) / 2;
        int l = dfs(nums, left, mid - 1, lower, nums[mid]);
        int r = dfs(nums, mid + 1, right, nums[mid], upper);
        
        if (nums[mid] > lower && nums[mid] < upper)  return l + r;
        else  return l + r + 1;
    }

}
