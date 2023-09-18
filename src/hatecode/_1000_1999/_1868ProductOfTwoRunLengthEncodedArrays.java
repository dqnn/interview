package hatecode._1000_1999;

import java.util.*;
public class _1868ProductOfTwoRunLengthEncodedArrays {
/*
1868. Product of Two Run-Length Encoded Arrays
Run-length encoding is a compression algorithm that allows for an integer array nums with many segments of consecutive repeated numbers to be represented by a (generally smaller) 2D array encoded. Each encoded[i] = [vali, freqi] describes the ith segment of repeated numbers in nums where vali is the value that is repeated freqi times.

For example, nums = [1,1,1,2,2,2,2,2] is represented by the run-length encoded array encoded = [[1,3],[2,5]]. Another way to read this is "three 1's followed by five 2's".
The product of two run-length encoded arrays encoded1 and encoded2 can be calculated using the following steps:

Expand both encoded1 and encoded2 into the full arrays nums1 and nums2 respectively.
Create a new array prodNums of length nums1.length and set prodNums[i] = nums1[i] * nums2[i].
Compress prodNums into a run-length encoded array and return it.
You are given two run-length encoded arrays encoded1 and encoded2 representing full arrays nums1 and nums2 respectively. Both nums1 and nums2 have the same length. Each encoded1[i] = [vali, freqi] describes the ith segment of nums1, and each encoded2[j] = [valj, freqj] describes the jth segment of nums2.

Return the product of encoded1 and encoded2.

Note: Compression should be done such that the run-length encoded array has the minimum possible length.

 

Example 1:

Input: encoded1 = [[1,3],[2,3]], encoded2 = [[6,3],[3,3]]
Output: [[6,6]]
Explanation: encoded1 expands to [1,1,1,2,2,2] and encoded2 expands to [6,6,6,3,3,3].
prodNums = [6,6,6,6,6,6], which is compressed into the run-length encoded array [[6,6]].
*/
    
    //thinking process: O(n)/O(1)
    
    //the problem is to say: given 2D array, e1 and e2,
    //[1,3]=[1,1,1], the multiple of two array means after expanding
    //e1[i] * e2[i]
    
    //return the compressed results.
    /* e1 = [[1,3],[2,3]], e2 = [[6,3],[3,3]] -===> [[6,6]]
     * [1,1,1,2,2,2] x [6,6,6,3,3,3]=[6,6,6,6,6,6]-->[6,6]
     * 
     * 
     * we use two pointers,one point to e1 another to e2.
     * 
     * it is pretty likely the interval merge, but slightly different.
     * 
     * consider each range, the length maybe different, so we can only get
     * smaller and compute them, then we gradually move to right.
     * 
     * when adding to result list, we need to merge last element of the array since 
     * why we only need to merge with last one?
     * 
     * because the result is only 1-D array, we always appending our results and we only need to
     * compare the last one, becuase if they are not different, we do nont need to merge,
     * see below example
     * [[1,3],[2,1],[3,2],[3,1]]
        [[2,3],[3,3],[2,1]]
        
        ----> [[2,3],[6,1],[9,2],[6,1]]


        Two pointers

     */
    

    public List<List<Integer>> findRLEArray(int[][] A, int[][] B) {
        if(A == null || B == null) return new ArrayList<>();
        
        List<List<Integer>> res = new ArrayList<>();
        int i = 0 , j = 0;
        
        while(i < A.length && j < B.length) {
            int[] a = A[i];
            int[] b = B[j];
            
            int mul = a[0] * b[0];
            int cnt = Math.min(a[1], b[1]);
            //handle cases like [[1,3],[2,3]] * [[6,3],[3,3]] --> [[6,6]]
            List<Integer> last = res.size() > 0 ? res.get(res.size() -1) : null;
            if(last != null && last.get(0) == mul) {
                last.set(1, last.get(1) + cnt);
            } else {
                List<Integer> tmp = Arrays.asList(mul, cnt);
                res.add(tmp);
            }
            
            if(cnt == a[1]) i++;
            else A[i][1] -= cnt;
            
            if(cnt == b[1]) j++;
            else B[j][1] -=cnt;
        }
        
        return res;
    }
}