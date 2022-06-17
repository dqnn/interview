package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by professorX on 28/07/2017.
 */
public class _349IntersectionofTwoArrays {
    /**
     * 349. Intersection of Two Arrays
     * Given two arrays, write a function to compute their intersection.

     Example:
     Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].

     Note:
     Each element in the result must be unique.
     The result can be in any order.

     time : O(n);
     space : O(n);

     * @param nums1
     * @param nums2
     * @return
     */

    // binary search time : O(nlogn) space : O(n)
    public static int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums2);
        for (Integer num : nums1) {
            if (binarySearch(nums2, num)) {
                set.add(num);
            }
        }
        int k = 0;
        int[] res = new int[set.size()];
        for (Integer num : set) {
            res[k++] = num;
        }
        return res;
    }

    public static boolean binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] == target || nums[end] == target) return true;
        return false;
    }

    // Arrays.sort time : O(nlogn) space : O(n);
    // note this is two pointers simple templates
    public static int[] intersection2(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[]{};
        }
        HashSet<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int k = 0;
        int[] res = new int[set.size()];
        for (Integer num : set) {
            res[k++] = num;
        }
        return res;
    }

    // time : O(n) space : O(n + m);
    public int[] intersection3(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null || nums1.length < 1 || nums2.length < 1)  {
            return new int[] {};
        }
        
        Set<Integer> set = new HashSet<>();
        Set<Integer> ret = new HashSet<>();
        for(int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        
        for (int j = 0; j < nums2.length; j++) {
                if (set.contains(nums2[j])) {
                    ret.add(nums2[j]);
                }
            }
        return ret.stream().mapToInt(i->i).toArray();
    }
}
