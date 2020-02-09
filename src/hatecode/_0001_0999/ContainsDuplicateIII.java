package hatecode._0001_0999;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ContainsDuplicateIII
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 220. Contains Duplicate III
 */

public class ContainsDuplicateIII {
    /**
     * Given an array of integers, find out whether there are two distinct indices i
     * and j in the array such that the absolute difference between nums[i] and
     * nums[j] is at most t and the absolute difference between i and j is at most
     * k.
     * 
     * Example 1:
     * 
     * Input: nums = [1,2,3,1], k = 3, t = 0 Output: true Example 2:
     * 
     * Input: nums = [1,0,1,1], k = 1, t = 2 Output: true Example 3:
     * 
     * Input: nums = [1,5,9,1,5,9], k = 2, t = 3 Output: false
     * 
     * time : O(nlogk) space : O(k)
     * 
     * @param nums
     * @param k
     * @param t
     * @return
     */

    // this way is little hard to think about, but it is efficient and fast by using
    // TreeSet
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if( k < 1 || t < 0) return false;
        TreeSet<Long> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++){
            // result value <= given value, most closest, avoid overflow by
            // converting to long
            Long floor = set.floor((long)nums[i] + t);

            // treeset.ceiling return least element (closest)in this set greater than
            // or equals to the given element, result means >=given value
            Long ceil = set.ceiling((long)nums[i] - t);

            if((floor != null && floor >= nums[i])
                    || (ceil != null && ceil <= nums[i]) )
                return true;

            set.add((long)nums[i]);
            // we maintain a treeset which helped us to maintain at most k elements in
            // treeset,
            // so we cannot keep more than k elements
            if(i >= k){
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }

    /**

     time : O(n)
     space : O(k)

     * @param nums
     * @param k
     * @param t
     * @return
     */

    // this also used k-window algorithms and bucket partitions
    // bucket here means like: suppose t + 1 = 3
    // 0 -3 --> will be put into bucket 0,
    // 4 - 5 --> will be put into bucket 1, so the number we get each of them
    // maxium is 3, 3-0 = 4-1 = 5 - 2, so we need to get nums[i] from map
    // to re-check the difference between two are less than t, above maybe
    // 5 - 0, we have to check this use case
    public boolean containsNearbyAlmostDuplicate2(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        HashMap<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // this is one way how we avoid the overflow by converting to long
            // bucket here, the most unit is t + 1
            // so bucket1 - bucket2 =
            // 1 = (remappedNum1 - remappedNum2) / (long(t) + 1),

            // we can just reposition every element to start
            // from Integer.MIN_VALUE.
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            // we can use t + 1 as the bucket size to get rid of the
            // case when t == 0. It simplifies the code
            long bucket = remappedNum / ((long) t + 1);
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                return true;
            // here same logic compared to treeset, keep K elements in the map
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }
}
