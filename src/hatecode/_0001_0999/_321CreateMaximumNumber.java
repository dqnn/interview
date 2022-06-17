package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CreateMaximumNumber
 * Creator : professorX
 * Date : Jan, 2018
 * Description : 321. Create Maximum Number
 */
public class _321CreateMaximumNumber {
    /**
     * Given two arrays of length m and n with digits 0-9 representing two numbers.
     * Create the maximum number of length k <= m + n from digits of the two. The
     * relative order of the digits from the same array must be preserved. Return an
     * array of the k digits. You should try to optimize your time and space
     * complexity.
     * 
     * Example 1: nums1 = [3, 4, 6, 5] 1 2 nums2 = [9, 1, 2, 5, 8, 3] 4 3 k = 5
     * return [9, 8, 6, 5, 3]
     * 
     * Example 2: nums1 = [6, 7] nums2 = [6, 0, 4] k = 5 return [6, 7, 6, 0, 4]
     * 
     * Example 3: nums1 = [3, 9] nums2 = [8, 9] k = 3 return [9, 8, 9]
     * 
     * 1：从nums1里取i个元素组成最大数组，从nums2里取k-i个元素组成最大数组。
     * 
     * 2：合并之前结果，得到一个长度为k的最大数组。
     * 
     * 3：对于不同长度分配的情况，比较每次得到的长度为k的最大数组，返回最大的一个。
     * 
     * 三个不同的函数分别用于取，合并，比较。 Reference : https://segmentfault.com/a/1190000007655603
     * 
     * time : O((m+n)^3) 不确定 space : O(m1+m2)
     * 
     * [2,3,1] [2,3]
     * 
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    //
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        //to make sure nums1 is shorter than nums2,  but it does not improve the latency of the solution
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        
        int m = nums1.length, n = nums2.length;
        int[] res = new int[k];
        //here is the improvements, it beat 100% of the submissions, 
        // seems a lot test cases here are that k is smaller than length/3
        if ( k <= m/3 || k <= n/3){
            m =  Math.min(nums1.length, k);
            n =  Math.min(nums2.length, k);
            nums1 = maxNumber(nums1, m);
            nums2 = maxNumber(nums2, n);
        }
        
        //so how we composite the two integers, we pick i from nums1 and k-i from nums2, 
        //and we merge by "sorted k sorted list"
        //note k might exceed nums2's length, so 
        for(int i = Math.max(0, k- n); i <= k && i <= m; i++) {
            int[] candidate = merge(maxNumber(nums1, i), maxNumber(nums2, k - i), k);
             if (greater(candidate, 0, res, 0)) res = candidate;
        }
        return res;
    }
    
    public int[] merge(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        for(int i = 0, j= 0, r = 0; r < k; r++) {
            res[r] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return res;
    }
    //compare two arrays, which one is bigger, compare from index 0 to i or j
    public boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    //nums1 = [3, 4, 6, 5] k =2, it would be 65
    //
    private int[] maxNumber(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[k];
        
        for(int i = 0, j= 0;i < n; i++) {
            //n-i > k-j means in array nums we have big enough number to insert to res,if we can find a bigger integer at this 
            //point then we will keep it, if not, we cannot remove current all integers,
            //nums[i] > res[j-1] means we find one number is better, so we need to find a place to replace
            while(n-i > k-j && j >0 && nums[i] > res[j-1]) {
                j--;
            }
            //find one suitable number and put into nums array
            if (j < k) res[j++] = nums[i];
        }
        return res;
    }
}
