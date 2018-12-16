package hatecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : GrayCode
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 89. Gray Code
 */
public class GrayCode {
    /**
     * The gray code is a binary numeral system where two successive values differ in only one bit.

     Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
     A gray code sequence must begin with 0.

     For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

     00 - 0
     01 - 1
     11 - 3
     10 - 2

     G(i) = i ^ (i/2)

     time : O(1 << n) / O(n)
     space : O(1 << n) / O(n)

     * @param n
     * @return
     */
    
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ i >> 1);
        }
        return res;
    }
    
    public List<Integer> grayCode2(int n) {
        List<Integer> res = new ArrayList<>();
        res.add(0);
        if (n == 0) return res;
        
        // so every next gray code is based on previous. 
        for(int i = 0; i < n; i++) {
            int size = res.size();
            for(int j = size - 1; j >= 0; j--) {
                // we use or last one element with i * 2
                // so 1 << i means 1 left shift i bits
                // suppose previous two is 0   1
                // then next iteration we will add 0 + 2,  1 + 2 ==> 0 and 1 are 1b difference, so next we will 2 to each
                // so there still one bit difference
                res.add(res.get(j) | 1 << i);
            }
        }
        
        return res;
    }
}
