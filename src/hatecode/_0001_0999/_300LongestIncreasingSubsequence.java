package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LongestIncreasingSubsequence
 * Creator : professorX
 * Date : Aug, 2018
 * Description : 300. Longest Increasing Subsequence
 */
public class _300LongestIncreasingSubsequence {
    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.

     For example,
     Given [10, 9, 2, 5, 3, 7, 101, 18],
     The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
     Note that there may be more than one LIS combination, it is only necessary for you to return the length.

     [10, 9, 2, 5, 3, 7, 101, 18]

     res == size

     10 res = 0 i = 0 j = 0
     9 res = 1 i = 0 j = 0 mid = 0
     2 res = 1 i = 0 j = 0 mid = 0
     5 res = 1 i = 1 j = 1 mid = 0
     3 res = 2
     7 res = 2 i = 2 j = 2 mid = 1
     101 res = 3 i = 2 j = 3 mid = 1
     18 res = 4 i = 3 j = 3 mid = 3


     i, j 相当于tails的起点，终点
     tails : [2,3,7,18]

     time : O(nlogn)
     space : O(n)

     * @param A
     * @return
     */
    
    ////Patience sorting
    /*
     * like you plaing cards, every time, starting from left, you can only place smaller one above it, else create a new pile 
     * 7, 3, 4, 5, 1
     * 
     * 3    1
     * 7 4  5
     * 
     * the length of the piles will be LIS, here we have 3 piles of cards  
     */
    // the idea  is to maintain a list, and for each element in n, we want to find a position in 
    // tail to insert, it is more like insert sort, 
    // the way to identify the element which should be longest increasing sequence is the position for 
    //res is the variable to maintain the insert position for each element

    //another thinking process:
    //we have an array tail which stored the tail[i] means for len = i + 1 sub array, the smallest 
    //element, from 0->i sub array
    
    //so if we meet a bigger value than tail[last], then just append it, if 
    //we meet a value less than tail[i], which means we meet a smaller value in array compared to previous 
    //ones, then we find the correct position and update by binary search, so the key idea here is to keep
    //tracking of the for sub array 0->i, the smallest last element sequence = len +1, the reason why
    //we like smallest because it is greedy to have longest sequence
    
    //
    //LIS length, O(nlgn)/O(n)
    public static int lengthOfLIS(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        /** 
         * tails is an array storing the smallest tail of all increasing subsequences with length i+1 in tails[i].
For example, say we have A = [4,5,6,3], then all the available increasing subsequences are:

len = 1   :      [4], [5], [6], [3]   => tails[0] = 3
len = 2   :      [4, 5], [5, 6]       => tails[1] = 5
len = 3   :      [4, 5, 6]            => tails[2] = 6
         * another example as below:
         *  */
        //[1,3,5,4,7]->[1, 3, 4, 7, 0]
        //len = 1, tails = [1];
        //len = 2, tails = [1, 3]
        //len = 3, tails = [1, 3, 4] 
        //len = 4, tails = [1, 3, 4, 7]
       /**(1) if x is larger than all tails, append it, increase the size by 1
        * (2) if tails[i-1] < x <= tails[i], update tails[i] 
        
        we want to find a position in dp[0] --- dp[i-1], which position is best for A[i],

        suppose the position is j, we can find dp[0]...dp[j-1] < dp[j] < dp[j+1]...dp[i-1]


        use example, 
        
        A [1,3,5,4,7]->[1, 3, 4, 7, 0] 
       dp  1 3 5             
              res

        when we process A[i] =4, we are compare A[i] to dp[m], 

        so  A[i] >dp[m], we want to move l = m + 1, becaus dp[m] is not quailified 
        if  A[i] == dp[m], so since it is already LIS, we can just replace it  
        
        */
        int[] tails = new int[A.length];
        int res = 0;
        for (int num : A) {
            //we search from l->r, r is last iteration result location
            int l = 0, r = res;
            // binary search ontails here can help to find which position for num
            // this templates is while(left < start), 2# template, we have one element left for 
            //post processin
            //binary search 2nd templates
            while (l < r) {
                int m = l + (r - l) /2;
                if (tails[m] < num) {
                    l = m + 1;
                //the == have to be on this side because if they are equals, then we can replace it, for example 
                // [1,4,5,4,7]->  when A[3] = 4,  we will replace the dp[1] in dp array, if we place = in above line, 
                //then we will miss since l move to m + 1 
                } else {// here is  >=
                    r = m;
                }
            }
            
            // this is to update or insert a new value in tails, 
            // 2 4 9 3 7 8, when res = 3, num = 3, we finally will find and replace tail[1] = 4
            // to tail[1] = 3, and for this time, res will keep previous value
            tails[l] = num;
            // so if i == res, which means num is a correct in last longest sequence
            if (l == res) ++res;
            
            System.out.println(num + "-->" + Arrays.toString(tails));
        }
        //System.out.println(Arrays.toString(tails));
        return res;
    }


    /*
     * the only difference is when we find A[i] == dp[m], we just use l = m then break, 
     * 
     * this is because dp is increase strictly, it should not have any two same numbers in array, we can just replace and return for A[i]
     * 
     * 
     */

    public int lengthOfLIS_Another_BS(int[] A) {
        if (A == null || A.length < 1) return 0;
        
        int n = A.length;
        int[] dp = new int[n];
        
        int res = 0;
        for(int i= 0; i< n; i++) {
            
            int l = 0, r = res;
            
            while(l < r) {
                int m = l + (r-l)/2;
                if (A[i] == dp[m]) {
                    l =m;
                    break;
                }
                else if(A[i] < dp[m]) {
                    r = m;
                } else l = m + 1;
            }
            
            dp[l] = A[i];
            if(l == res) res++;
        }
        
        return res;
    }
    
    // this is interview friendly for O(n^2)， brute force Only
    public int lengthOfLIS2(int[] A) {
        if (A == null || A.length < 1) {
            return 0;
        }
        int len = A.length;

        // dp[i] means if nums[i] as the last elements, the longest increasing sequence length
        int[] dp = new int[len];
        // at least 1 number for LIS
        Arrays.fill(dp, 1);

        // set max = 1, edge case like [2, 2] it will not go through the two loops,
        //also for [0] cases, we also need max to be 1 or else we have add if (len == 1) return 1;
        int max = 1;
        //we compare each nums[j] to nums[i]. if n[j] < n[i] then it should be included. 
        // so we update each dp[i] = max(dp[i],dp[j] + 1)
        //same: if nums[i] as last elment in array, what's the maxium increase array
        //[1,3,5,4,7]->dp[1,2,3,3,4]
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if (A[j] < A[i]) {
                    // means nums[i] could be nums[j] next element, 
                    //that's why we need compare to n[j]
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                    max = Math.max(dp[i], max);
                }
            }
        }
        return max;
    }
    public static void main(String[] args) {
        int[] in= {1, 3,5,4,7};
        System.out.println(lengthOfLIS(in));
    }
}
