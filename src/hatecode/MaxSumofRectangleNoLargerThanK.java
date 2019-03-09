package hatecode;

import java.util.TreeSet;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MaxSumofRectangleNoLargerThanK
 * Creator : duqiang
 * Date : Aug, 2018
 * Description : 363. Max Sum of Rectangle No Larger Than K
 */
public class MaxSumofRectangleNoLargerThanK {

    /**
     * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle
     * in the matrix such that its sum is no larger than k.

     Example:
     Given matrix = [
     [1,  0, 1],
     [0, -2, 3]
     ]
     k = 2
     The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

     Note:
     The rectangle inside the matrix must have an area > 0.
     What if the number of rows is much larger than the number of columns?


     1, 求matrix中和最大的那个矩形，返回最大值
     2, 一维 array, 找出其中连续的一段，其和最大，但是不大于 k

     1  0 1 2
     0 -2 3 1
     2  4 1 -2
     3  1 2 -1


     sums = [1 -2 6 4]

     reference: https://www.jianshu.com/p/e9ff87d6bf8e

     time : O[min(m,n)^2 * max(m,n) * log(max(m,n))]
     space : O(max(m, n))

     * @param matrix
     * @param k
     * @return
     */
    // the problem is asking for max sum of a rectangle, all numbers sum in this rectangle.
    
    //we scan the matrix 3 times, 2 row 1 coumn to get accumulted sum, so for each iteration,
    //we use a treeSet to to look for numbers which is cur -k the minimal ones  
    /*
     row scan i->0..c we define sum[i], i is from 0->r
          row scan j ->i, c.
                 column scan col->0, r to get sum[col]
                 we use treeset to store the sum[col], col->0. r. and find the min
                 
                 since sum, we want no more than 7, we seek to ceiling in treeset which contains
                 all integers from sum.this is more like in array, we want to find two numbers
                 sum no more than k
                      
     */
    public int maxSumSubmatrix(int[][] m, int k) {
        if (m == null || m.length < 1) {
            return 0;
        }
        
        int r = m.length, c = m[0].length, res = Integer.MIN_VALUE;
        // row scan
        for (int i = 0; i < c; i ++) {
            //each row sum, sum[i] from column mode 0--> i
            int[] sum = new int[r];
            // second pointer scan from left to right, only after from first pointer
            // column mode sum
            for (int j = i; j < c; j++) {
                // we scan from on column right
                for (int p = 0; p < r; p++) {
                    sum[p] += m[p][j];
                }
                //add column value into treeSet
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0);
                int cur = 0;
                // for each column mode, we visit by row level
                // because sum is accumulated, so use sum[big] - sum[small] <= k, 
                //that's why we use cur - k
                for(int num : sum) {
                    cur += num;
                     // it returns least value >= cur-k
                    //the Floor of 2.31 is 2 
                    // The Ceiling of 2.31 is 3
                    //The Floor/Ceiling of 5 is 5
                    // 大于cur-k 的最小值
                    //temp > cur - k, while temp is min, so k > cur - temp,so 
                    Integer temp = treeSet.ceiling(cur - k);
                    if (temp != null) {
                        res = Math.max(res, cur - temp);
                    }
                    treeSet.add(cur);
                }
            }
        }
        return res;
    }


    private int helper(int[] nums, int k) {
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        int res = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            Integer num = set.ceiling(sum - k);
            if (num != null) {
                res = Math.max(res, sum - num);
            }
            set.add(sum);
        }
        return res;
    }

}
