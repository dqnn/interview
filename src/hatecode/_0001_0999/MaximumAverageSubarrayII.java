package hatecode._0001_0999;

import java.util.stream.*;
import java.util.*;
public class MaximumAverageSubarrayII {
/*
644. Maximum Average Subarray II
Given an array consisting of n integers, find the contiguous subarray whose length is greater than or equal to k that has the maximum average value. And you need to output the maximum average value.

Example 1:
Input: [1,12,-5,-6,50,3], k = 4
Output: 12.75
Explanation:
when length is 5, maximum average value is 10.8,
when length is 6, maximum average value is 9.16667.
Thus return 12.75.
*/
    //O(n) solution
  //For each ending index j, the current interval for i under consideration, 
    //[0, j-K+1] (minus parts on the left we have already discarded), has been decomposed 
    //into minimum density segments of longest length [hull[i], hull[i+1]-1], and we discard 
    //these segments as appropriate. That is, for each i in increasing order, hull[i+1] is the 
    //largest index in [hull[i], j-K+1] so that [hull[i], hull[i+1]-1] has minimum density.
    //TODO: understand this solution
    static int[] preSum;
    public static double findMaxAverage_Best(int[] nums, int k) {
        int len = nums.length;
        preSum = new int[len];
        // get preSum array
        preSum[0] = nums[0];
        for(int i=1; i<len; i++){
            preSum[i] =preSum[i-1]+nums[i];
        }
        // create the list such that for the range (i,j), element in this list would satisfy the following condition
        // list[m]  list[m+1]-1 would be the smallest density from list[m] j.
        LinkedList<Integer> list = new LinkedList<Integer>();
        double res = -10000.0;
        for(int j =k-1; j<len; j++){
            // if the last point does not satisfy the condition, we need to remove it
            while(list.size()>=2 && 
                 getDensity(list.get(list.size()-2), list.get(list.size()-1)-1) 
                   >= getDensity(list.get(list.size()-2),j-k ) ){
                    list.pollLast();
            }
            list.add(j-k+1);
            // all points are OK. If the first two point satisfy the condition, 
            //we need to remove the first point. we need to find the max point
            while(list.size()>=2 && 
                 getDensity(list.get(0), list.get(1)-1) <= getDensity(list.get(0),j)
                 ){
                list.pollFirst();
            }
            res = Math.max(res, getDensity(list.get(0),j));
        }
        
        return res ;
    }
    //Let d(x, y) be the density of segment [x, y], ie. d(x, y) = (A[x]+...+A[y]) / (y-x+1)
    public static double getDensity(int l, int r) {
        double res = 0.0;
        if (l == 0) res = ((double) preSum[r]) / (1.0 + r);
        else res = ((double) preSum[r] - preSum[l - 1]) / (1.0 + r - l);
        return res;
    }
    
    public static void main(String[] args) {
        System.out.println(findMaxAverage_Best(new int[] {1,12,-5,-6,50,3}, 4));
    }
    
    
    //thinking process: 
    //O(nlgm) 这里m是nums[]最大值和最小值的差。
    //if return true, then means there is sub array which has avg larger than x, else is false
    //to find a subarray which is larger than x or less than x, 
    //we have a loop for 
    boolean check(int[] nums, int k, double x) {// Check whether we can find a subarray whose average is bigger than x
        int n = nums.length;
        double[] a = new double[n];
        // Transfer to a[i], find whether there is a subarray whose sum is bigger than 0
        IntStream.range(0, n).forEach(i -> a[i] = nums[i] - x);
        //now and last are two 
        double now = 0, last = 0;
        for (int i = 0; i < k; i++) now += a[i];
        if (now >= 0) return true;
        //two pointers move, every time, right moves to next while we detect current avg >= 0 or not.
        //
        for (int i = k; i < n; i++) {
            now += a[i];
            last += a[i - k];
            //means we want to remove a[i-k] from curren subarray
            if (last < 0) {
                now -= last;
                last = 0;
            }
            if (now >= 0) return true;
        }
        return false;
    }
    public double findMaxAverage(int[] nums, int k) {
        double l=Integer.MIN_VALUE,r=Integer.MAX_VALUE;
        //mimic l + 1 < r
        while (l + 0.000004 < r) {// Binary search the answer
            double mid = l + (r - l) / 2;
            if (check(nums, k, mid))
                l = mid;
            else
                r = mid;
        }
        return r;
    }
}