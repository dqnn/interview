package hatecode._0001_0999;

import java.util.Arrays;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : HIndex
 * Creator : duqiang
 * Date : July, 2018
 * Description : 274. H-Index
 */
public class _274HIndex {
    /**
     * 
     * 
     * Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to 
     * compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h 
citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [3,0,6,1,5]
Output: 3 
Explanation: [3,0,6,1,5] means the researcher has 5 papers in total and each of them had 
             received 3, 0, 6, 1, 5 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note: If there are several possible values for h, the maximum one is taken as the h-index.


     * 0 1 2 3 4
     * 6 5 3 1 0
     */
    // time : O(nlogn) space : O(1)
    public int hIndex(int[] c) {
        if (c == null || c.length < 1) {
            return 0;
        }
        
        Arrays.sort(c);
        int res = 0;
        // so we sort first and find the elements from last to front
        // if we find the value bigger than its previous leng the of elements then that 's it'
        while (res < c.length && c[c.length - res - 1] > res) {
            res ++;
        }
        
        return res;
    }   

    // time : O(n) space : O(n)
    public int hIndex2(int[] citations) {
        int[] helper = new int[citations.length + 1];
        for (int i = 0; i < citations.length; i++) {
            helper[citations[i] <= citations.length ? citations[i] : citations.length] += 1;
        }
        int sum = 0;
        for (int i = citations.length; i > 0; i--) {
            sum += helper[i];
            if (sum >= i) {
                return i;
            }
        }
        return 0;
    }
    // easier version
    public int hIndex3(int[] citations) {
        int n = citations.length;
        int[] buckets = new int[n+1];
        for(int c : citations) {
            if(c >= n) {
                // which means we have more than reference than total count
                buckets[n]++;
            } else {
                // if we have reference less than its length, then bucket number ++
                buckets[c]++;
            }
        }
        int count = 0;
        for(int i = n; i >= 0; i--) {
            count += buckets[i];
            // this is the boundary 
            if(count >= i) {
                return i;
            }
        }
        return 0;
    }
}
