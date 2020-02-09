package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : SumofTwoIntegers
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 371. Sum of Two Integers
 */
public class SumofTwoIntegers {
    /**
     * Calculate the sum of two integers a and b, 
     * but you are not allowed to use the operator + and -.

     Example:
     Given a = 1 and b = 2, return 3.

     time : O(1) / O(n)
     space : O(1)

     * @param a
     * @param b
     * @return
     */
    public int getSum(int a, int b) {
        if (a == 0) return b;
        if (b == 0) return a;

        while (b != 0) {
            //get the same
            int carry = a & b;
            // get different, actually 
            a = a ^ b;
            // b is only 1 digits integer
            b = carry << 1;
        }

        return a;
    }
}
