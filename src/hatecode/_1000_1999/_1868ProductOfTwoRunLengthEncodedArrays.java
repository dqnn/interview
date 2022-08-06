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
     */
    public List<List<Integer>> findRLEArray(int[][] e1, int[][] e2) {
        int p1 = 0, p2 =0;
        
        List<List<Integer>> res = new ArrayList<>();
        while(p1 < e1.length) {
            int len = Math.min(e1[p1][1], e2[p2][1]);
            int mul = e1[p1][0] * e2[p2][0];
            
            //handle cases like [[1,3],[2,3]] * [[6,3],[3,3]] --> [[6,6]]
            if (res.size() > 0 && res.get(res.size()-1).get(0) == mul) {
                int nLen = res.get(res.size()-1).get(1) + len;
                res.set(res.size()-1, Arrays.asList(mul, nLen));
            } else {
                res.add(Arrays.asList(mul, len));
            }
            
            e1[p1][1] -= len;
            e2[p2][1] -= len;
            if (e1[p1][1] == 0) p1++;
            if (e2[p2][1] == 0) p2++;
            //System.out.println(p1+"===" + p2);
        }
        
        return res;
    }
}