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
