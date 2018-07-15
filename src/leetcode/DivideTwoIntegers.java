package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DivideTwoIntegers
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 29. Divide Two Integers
 */
public class DivideTwoIntegers {
    /**
     * Given two integers dividend and divisor, divide two integers without using
     * multiplication, division and mod operator. Return the quotient after dividing
     * dividend by divisor. The integer division should truncate toward zero.
     * 
     * Example 1:
     * 
     * Input: dividend = 10, divisor = 3 Output: 3 
     * 
     * Example 2:
     * 
     * Input: dividend = 7, divisor = -3 Output: -2 
     * 
     * 1, + - 2, 越界 3, = 0 3/5 4, 正常
     * 
     * time : O(logn) space : O(logn)
     * 
     * 
     * @param dividend
     * @param divisor
     * @return
     */
    // this way, the algorithms is to accerlerate faster and if we encounter the barrier, 
    //we slow down and then faster, like collision detection algorithms, decrease fast and 
    // accerlerate slow.
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) sign = -1;
        // here we have to handle dividend is Integer.MIN_VALUE this case, so it cannot be 
        // Integer.MIN_VALUE = -2^31, Integer.MAX_VALUE = 2^31 - 1. so they are different
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        if (ldividend < ldivisor || ldividend == 0) return 0;
        long lres = divide(ldividend, ldivisor);
        int res = 0;
        if (lres > Integer.MAX_VALUE) {
            res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else res = (int)(sign * lres);
        return res;
    }

    public long divide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) return 0;
        long sum = ldivisor;
        long multiple = 1;
        while ((sum + sum) <= ldividend) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + divide(ldividend - sum, ldivisor);
    }

}
