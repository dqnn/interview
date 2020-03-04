package hatecode._0001_0999;

import java.util.Arrays;

public class _556NextGreaterElementIII {
/*
556. Next Greater Element III
This is the same as Next Permutation II 

Given a positive 32-bit integer n, you need to find the smallest 32-bit integer which has exactly the same digits existing in the integer n and is greater in value than n. If no such positive 32-bit integer exists, you need to return -1.

Example 1:

Input: 12
Output: 21
 

Example 2:

Input: 21
Output: -1
*/
    // thinking process: 
    public int nextGreaterElement(int n) {
        char[] chs = String.valueOf(n).toCharArray();
        int i = chs.length - 2;
        //increase sequence
        while(i >=0 && chs[i + 1] <= chs[i]) {
            i --;
        }
        if (i < 0) {
            return -1;
        }
        // for case: 12443322
        //we would get  i = 2, and we are not sure after i + 1 there still
        //number between i and i + 1. so we scan from back to i + 1, choose the smallest 
        // and replace with i
        for(int j = chs.length -1;j > i; j--) {
            if (chs[i] < chs[j]) {
                char temp = chs[i];
                chs[i] = chs[j];
                chs[j] = temp;
                Arrays.sort(chs, i+1, chs.length);
                long res = Long.valueOf(new String(chs));
                return res > Integer.MAX_VALUE ? -1 : (int)res;
            }
        }
        return -1;
    }
}