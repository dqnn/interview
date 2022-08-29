package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DivideTwoIntegers
 * Date : Nov, 2017
 * Description : 29. Divide Two Integers
 */
public class _029DivideTwoIntegers {
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
     * @param ld
     * @param d
     * @return
     */
    //thinking process: given two integer, output the ld/d result
    //so it means we need to use + and - to implement / 
    // this way, the algorithms is to accelerate faster and if we encounter the barrier, 
    //we slow down and then faster, like collision detection algorithms, decrease fast and 
    // accelerate slow.
    public int divide(int ld, int d) {
        int sign = 1;
        if ((ld > 0 && d < 0) || (ld < 0 && d > 0)) sign = -1;
        
        //like ld = -2^31, d = -1, it will overflow
        // here we have to handle dividend is Integer.MIN_VALUE this case, so it cannot be 
        // Integer.MIN_VALUE = -2^31, Integer.MAX_VALUE = 2^31 - 1. so they are different
        long ldividend = Math.abs((long)ld);
        long ldivisor = Math.abs((long)d);
        if (ldividend < ldivisor || ldividend == 0) return 0;
        
        long lres = helper(ldividend, ldivisor);
        int res = 0;
        
        // [ld = -2147483648, d = -1], it will be bigger than integer max
        if (lres > Integer.MAX_VALUE) {
            res = (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else res = (int)(sign * lres);
        return res;
    }
    
    
    //O(lgn)/O(lgn)
    public long helper(long ld, long d) {
        if (ld < d) return 0;
        long sum = d;
        long multiple = 1;
        // =, this will reduce some depth of the helper, like  ld = 8, d= 2, with = or no =, code will be 
        //correct
        // 
        while ((sum + sum) <= ld) {
            sum += sum;
            multiple += multiple;
        }
        return multiple + helper(ld - sum, d);
    }

}
