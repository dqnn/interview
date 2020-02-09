package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SearchforaRange
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class FindFirstAndLastPositionOfElementInSortedArray {
    /**
     * 34. Find First and Last Position of Element in Sorted Array
     * Given an array of integers sorted in ascending order, find the starting 
     * and ending position of a given target value.

     Your algorithm's runtime complexity must be in the order of O(log n).

     If the target is not found in the array, return [-1, -1].

     For example,
     Given [5, 7, 7, 8, 8, 10] and target value 8,
     return [3, 4].

     time : O(logn)
     space : O(1);
     * @param nums
     * @param target
     * @return
     */
    //thinking process:
    //given a sorted array which contains dup elements, we want to find an element which starts and end 
    //position in array. 
    
    //the key of dup binary search is where we put = condition in if clause
    //if = condition is on l(followed by) which means we want to find last(biggest), move to right
    //if = condition is on r(followed by) which means we want to find first(smallest, move to left 
    
    //one exception case for BS is that if cannot find it, return -1
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};
        int start = findFirst(nums, target);
        if (start == -1) return new int[]{-1, -1};
        int end = findLast(nums, target);
        return new int[]{start, end};
    }

    public int findFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        // so when equals fall into each branch then 
        // it would stop there for example:
        //[8,8,8,8,8,8,8, 8], 8, 
        // end will always move forward, start keep still
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            //so we do not have = because we want to move end in more probability 
            // and move start in a very strict conditions, this can help to make sure 
            //start will always < target, so mid can get first 8 or end
            
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //this process is also interesting that we want to return start as start since 
        // start may equals end
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
    //the same as 35
    public int findFirst_SameAsEqualsGreat(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        // so when equals fall into each branch then 
        // it would stop there for example:
        //[8,8,8,8,8,8,8, 8], 8, 
        // end will always move forward, start keep still
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            //so we do not have = because we want to move end in more probability 
            // and move start in a very strict conditions, this can help to make sure 
            //start will always < target, so mid can get first 8 or end
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        //this is to return correct insert position,
        if (nums[start] >= target) return start;
        else if (target <= nums[end]) return end;
        else return end + 1;
    }

    public int findLast(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
      //[8,8,8,8,8,8,8, 8], 8, 
        // end will always move forward, start keep still
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[end] == target) return end;
        if (nums[start] == target) return start;
        return -1;
    }
    
    
    public static int[] searchRange2(int[] A, int target) {
        int start = firstGreaterEqual(A, target);
        if (start == A.length || A[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, firstGreaterEqual(A, target + 1) - 1};
    }

    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    //actually this is the same as lower_bound in C++ STL.
    //template 2
    private static int firstGreaterEqual(int[] A, int target) {
        int low = 0, high = A.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            //low <= mid < high
            if (A[mid] < target) {
                low = mid + 1;
            } else {
                //should not be mid-1 when A[mid]==target.
                //could be mid even if A[mid]>target because mid<high.
                high = mid;
            }
        }
        return low;
    }
    
    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange2(new int[] {1}, 1)));
    }
}
