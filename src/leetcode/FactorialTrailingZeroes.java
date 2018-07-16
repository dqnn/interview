package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FactorialTrailingZeroes
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 172. Factorial Trailing Zeroes
 */
public class FactorialTrailingZeroes {
    /**
     * Given an integer n, return the number of trailing zeroes in n!.

     Note: Your solution should be in logarithmic time complexity.


     time : O(logn)
     space : O(n)

     * @param n
     * @return
     */
    // there was always 2 or 4 so definitely we can get 0 and it was decided by
    // how many 5 factors in the last
    // 1x2x3x4x5x...x20 = M
    // M = 5x 3X 5(15) x 4x5(20),so we can get more 5 factors finally
    // if we diveded by 5, we are missing 25, 
    
    /*
     * 25, 24, 23, 22, 21 ----- 25/5 = 5
       20, 19, 18, 17, 16 ----- 20/5 = 4
       15, 14, 13, 12, 11------ 15/5 = 3
       10, 9, 8, 7, 6 ----------- 10/5 = 2
       5, 4, 3, 2, 1 -------------- 5/5 = 1
        which gives anther loop: 5, 4, 3, 2, 1 ('2' is always enough for '5' to multiply)
        So: 25/5 + 5/5 = 6
     */
    public int trailingZeroes(int n) {
        return n == 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
    
    public int trailingZeroes2(int n) {
        //edge case
        if (n <=4) {
            return 0;
        }
        
        int m = n;
        int res = 0;
        while (m != 0) {
            m = m / 5;
            res += m;
        }
        return res;
    }
}
