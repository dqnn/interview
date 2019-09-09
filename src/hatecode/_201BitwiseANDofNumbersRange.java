package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : BitwiseANDofNumbersRange
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 201. Bitwise AND of Numbers Range
 */
public class _201BitwiseANDofNumbersRange {

    /**
     * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

     For example, given the range [5, 7], you should return 4.

     time : < O(n) ~ O(1)
     space : O(1)


     * @param m
     * @param n
     * @return
     */

    // so 101 110 111 we will see the the common part will be right answer, if there
    // is
    // one 0 then result will be 0. just need to find common part.
    public int rangeBitwiseAnd(int m, int n) {
        int offset = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            offset++;
        }
        return m << offset;
    }

    // d as max so we can move to left and get the correct common part mask.
    public int rangeBitwiseAnd2(int m, int n) {
        // edge case
        if (m < 0 || n < 0) {
            return -1;
        }

        int d = Integer.MAX_VALUE;
        while ((m & d) != (n & d)) {
            d = d << 1; // find the left common part
        }

        return m & d;
    }
}
