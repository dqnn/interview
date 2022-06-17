package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : CountingBits
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 338. Counting Bits
 */
public class _338CountingBits {
    /**
     * Given a non negative integer number num. For every numbers i in the range 0 ≤
     * i ≤ num calculate the number of 1's in their binary representation and return
     * them as an array.
     *
     * https://www.cnblogs.com/grandyang/p/5294255.html
     * 
     * Example:
     * 
     * For num = 5 you should return [0,1,1,2,1,2].
     * 
     * 8 = 1000 0111 = 0000 i&(i-1)
     * 
     * i bit '1' i&(i-1) 0 0000 0 0 ----------------------- 1 0001 1 0000 1
     * ----------------------- 2 0010 1 0000 1 3 0011 2 0010 2
     * ----------------------- 4 0100 1 0000 5 0101 2 0100 6 0110 2 0100 7 0111 3
     * 0110 ----------------------- 8 1000 1 0000 9 1001 2 1000 10 1010 2 1000 11
     * 1011 3 1010 12 1100 2 1000 13 1101 3 1100 14 1110 3 1100 15 1111 4 1110
     * 
     * time : O(n) space : O(n)
     * 
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        // edge case
        if (num < 0) {
            return null;
        }
        int[] res = new int[num + 1];
        res[0] = 0;
        for (int i = 1; i <= num; i++) {
            // here is the trick, that adjacent numbers common part
            // could help to build the formula
            res[i] = res[i & (i - 1)] + 1;
        }
        return res;
    }

    public int[] countBits2(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        // we use dp to store the 1 in number
        // so i >> 1 means we dp[8] = dp[4] + i & 1;
        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    // so this is we want to count the same part of each number
    // 1100, 1101, 1110,1111, so 11 is the same no matter other postions change.
    //
    public int[] countBits3(int num) {
        int[] ret = new int[num + 1];
        ret[0] = 0;
        int pow = 1;
        for (int i = 1, t = 0; i <= num; i++, t++) {
            if (i == pow) {
                pow *= 2;
                t = 0;
            }
            ret[i] = ret[t] + 1;
        }
        return ret;
    }
}
