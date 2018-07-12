package leetcode;

import java.util.Map;
import java.util.TreeMap;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountofRangeSum
 * Creator : duqiang
 * Date : Jan, 2018
 * Description : 327. Count of Range Sum
 */
public class CountofRangeSum {
    /**
     * Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
     Range sum S(i, j) is defined as the sum of the elements in nums
     between indices i and j (i ≤ j), inclusive.

     Note:
     A naive algorithm of O(n2) is trivial. You MUST do better than that.

     Example:
     Given nums = [-2, 5, -1], lower = -2, upper = 2,
     Return 3.
     The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2.

     1 2 3
     1 3 6 .. lower = 1 upper = 3

     sum = 6     3 - 5
     treeMap :  1 3
     Map : (3,1)

     TreeMap<Key, Value>
     subMap

     * @param nums
     * @param lower
     * @param upper
     * @return
     */

    // time : O(n^2) 不确定 space : O(n)
    /*
     * 首先根据前面的那几道类似题Range Sum Query - Mutable 区域和检索 - 可变， Range Sum Query 2D -
     * Immutable 二维区域和检索和 Range Sum Query - Immutable 区域和检索 -
     * 不可变的解法可知类似的区间和的问题一定是要计算累积和sum的， 其中 sum[i] = nums[0] + nums[1] + ... +
     * nums[i]， 对于某个i来说，只有那些满足 lower <= sum[i] - sum[j] <= upper 的 j能形成一个区间[j,
     * i]满足题意， 那么我们的目标就是来找到有多少个这样的j (0 =< j < i)
     * 
     * 
     * 
     * 满足 sum[i] - upper =< sum[j] <= sum[i] - lower，
     * 我们可以用C++中由红黑树实现的multiset数据结构可以对其中数据排序， 然后用upperbound和lowerbound来找临界值。
     * 
     * 
     * 
     * lower_bound是找数组中第一个不小于给定值的数(包括等于情况)， 而upper_bound是找数组中第一个大于给定值的数，那么两者相减，
     * 就是j的个数，参见代码如下：
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) return 0;
        TreeMap<Long, Long> treeMap = new TreeMap<>();
        treeMap.put((long)0, (long)1);
        long sum = 0;
        long count = 0;

        // 由于RangeSum S(i,j)在[lower,upper]之间的条件是
        // lower<=sums[j+1]-sums[i]<=upper, 所以我们每次insert一个新的
        // PrefixSum sums[k]进这个BST之前，先寻找一下（rangeSize）
        // 该BST内已经有多少个PrefixSum（叫它sums[t]吧）满足lower<=sums[k]-sums[t]<=upper,
        // 即寻找有多少个sums[t]满足：
        // sums[k]-upper<=sums[t]<=sums[k]-lower --> 等于
        // lower<=sums[k]-sums[t]<=upper
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // here is not easy, so we using sum - upper to get the from
            long from = sum - upper;
            long to = sum - lower;
            // subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)
            // Returns a view of the portion of this map whose keys range from fromKey to
            // toKey.

            // if it has duplicated number, then we consider them different
            //
            Map<Long, Long> sub = treeMap.subMap(from, true, to, true);
            for (Long value : sub.values()) {
                count += value;
            }
            treeMap.put(sum, treeMap.getOrDefault(sum, (long)0) + 1);
        }
        return (int)count;
    }

    // time : O(nlogn) space : O(n)
    public int countRangeSum2(int[] nums, int lower, int upper) {
        long[] sum = new long[nums.length + 1];
        for(int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        return helper(sum, new long[sum.length], 0, sum.length - 1, lower, upper);
    }

    /**
     rangeEnd是第一个满足 sums[rangeEnd] - sums[i] > upper 的下标

     rangeStart是第一个满足 sums[rangeStart] - sums[i] >= lower 的下标

     [lower, upper]之间的区间的个数是rangeEnd - rangeStart


     遍历前半段 匹配后半段
     [1,3]  [2,4]


     * @param sum
     * @param helper
     * @param low
     * @param high
     * @param lower
     * @param upper
     * @return
     */

    private int helper(long[] sum, long[] helper, int low, int high, long lower, long upper) {
        if (low >= high) {
            return 0;
        }
        // here needs more understanding
        int mid = (high + 1 - low) / 2 + low;
        int count = helper(sum, helper, low, mid - 1, lower, upper)
                + helper(sum, helper, mid, high, lower, upper);

        int rangeStart = mid, rangeEnd = mid;
        for(int i = low; i < mid; i++) {
            while(rangeStart <= high && sum[rangeStart] - sum[i] < lower)
                rangeStart++;
            while(rangeEnd <= high && sum[rangeEnd] - sum[i] <= upper)
                rangeEnd++;

            count += rangeEnd - rangeStart;
        }

        merge(sum, helper, low, mid, high);
        return count;
    }

    private void merge(long[] sum, long[] helper, int low, int mid, int high) {
        int left = low, right = mid, idx = low;

        while(left < mid && right <= high) {
            if (sum[left] <= sum[right])
                helper[idx++] = sum[left++];
            else
                helper[idx++] = sum[right++];
        }

        while(left < mid)
            helper[idx++] = sum[left++];
        while(right <= high)
            helper[idx++] = sum[right++];

        System.arraycopy(helper, low, sum, low, high + 1 - low);
    }

    // this is naive soltuion but it is working
    public int countRangeSum3(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        int ans = 0;
        for (int i = 0; i < n; ++i)
            for (int j = i + 1; j <= n; ++j)
                if (sums[j] - sums[i] >= lower && sums[j] - sums[i] <= upper)
                    ans++;
        return ans;
    }
    /*
     * 
     * The merge sort based solution counts the answer while doing the merge.
     * 
     * During the merge stage, we have already sorted the left half [start, mid) and
     * right half [mid, end). We then iterate through the left half with index i.
     * For each i, we need to find two indices k and j in the right half where
     * 
     * j is the first index satisfy sums[j] - sums[i] > upper and k is the first
     * index satisfy sums[k] - sums[i] >= lower. Then the number of sums in [lower,
     * upper] is j-k. We also use another index t to copy the elements satisfy
     * sums[t] < sums[i] to a cache in order to complete the merge sort.
     * 
     * Despite the nested loops, the time complexity of the "merge & count" stage is
     * still linear. Because the indices k, j, t will only increase but not
     * decrease, each of them will only traversal the right half once at most. The
     * total time complexity of this divide and conquer solution is then O(n log n).
     * 
     * One other concern is that the sums may overflow integer. So we use long
     * instead.
     */

    public int countRangeSum4(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[] sums = new long[n + 1];
        for (int i = 0; i < n; ++i)
            sums[i + 1] = sums[i] + nums[i];
        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
    }

    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
        if (end - start <= 1)
            return 0;
        int mid = (start + end) / 2;
        int count = countWhileMergeSort(sums, start, mid, lower, upper)
                + countWhileMergeSort(sums, mid, end, lower, upper);
        int j = mid, k = mid, t = mid;
        long[] cache = new long[end - start];
        for (int i = start, r = 0; i < mid; ++i, ++r) {
            while (k < end && sums[k] - sums[i] < lower)
                k++;
            while (j < end && sums[j] - sums[i] <= upper)
                j++;
            while (t < end && sums[t] < sums[i])
                cache[r++] = sums[t++];
            cache[r] = sums[i];
            count += j - k;
        }
        System.arraycopy(cache, 0, sums, start, t - start);
        return count;
    }

    // Binary Index Tree solution
    // [1,2,3,4,5,6,7,8], lower and upper
    // t[] visit the t[] find how many are in lower and upper.

}
