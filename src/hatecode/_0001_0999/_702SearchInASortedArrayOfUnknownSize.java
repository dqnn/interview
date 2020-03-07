package hatecode._0001_0999;

public class _702SearchInASortedArrayOfUnknownSize {
/*
702. Search in a Sorted Array of Unknown Size
Given an integer array sorted in ascending order, write a function to search target in nums.  If target exists, then return its index, otherwise return -1. However, the array size is unknown to you. You may only access the array using an ArrayReader interface, where ArrayReader.get(k) returns the element of the array at index k (0-indexed).

You may assume all integers in the array are less than 10000, and if you access the array out of bounds, ArrayReader.get will return 2147483647.

 

Example 1:

Input: array = [-1,0,3,5,9,12], target = 9
Output: 4
Explanation: 9 exists in nums and its index is 4
*/
    class ArrayReader{
        long get(int k) {
            return 345L;
        }
    }
    public int search2(ArrayReader reader, int target) {
        int left = 0, right = 10000;
        while(left <= right) {
            int mid = left + ((right -left)>>1);
            long value  = reader.get(mid);
            if (value == 2147483647) {
                right = mid -1;
                continue;
            }
            if (value < target) {
                left = mid + 1;
            } else if (value > target) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
    //this is right solution which helped how to scale from small to big
    public int search(ArrayReader reader, int target) {
        int hi = 1;
        while (reader.get(hi) < target) {
            hi = hi << 1;
        }
        int low = hi >> 1;
        while (low <= hi) {
            int mid = low + (hi - low) / 2;
            if (reader.get(mid) > target) {
                hi = mid - 1;
            } else if (reader.get(mid) < target) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}