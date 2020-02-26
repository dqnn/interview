package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : DecodeWays
 * Creator : duqiang
 * Date : Nov, 2017
 * Description : 91. Decode Ways
 */
public class _091DecodeWays {
    /**
     * A message containing letters from A-Z is being encoded to numbers using the 
     * following mapping:

     'A' -> 1
     'B' -> 2
     ...
     'Z' -> 26
     Given an encoded message containing digits, determine the total number of 
     ways to decode it.

     For example,
     Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

     The number of ways decoding "12" is 2.


     time : O(n)


     * @param s
     * @return
     */

    // space : O(n)
    // 这个题目就是限制性的feic 数列
    // 假设所有的数字都有效，且左右两两相邻数之间也有效（1到26）,那么有如下规律
    // numDecodings(s) = numDecodings(s.substring(1)) + numDecodings(s.substring(2))
    public int numDecodings(String s) {
        // edge case
        if (s == null || s.length() < 1 || s.charAt(0) == '0') {
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1];
        //here also is one key that initialized as 1 because 
        //"12" this case, we have 2 ways of decoding, so default every character would have 1
        //way to decode
        dp[0] = 1;
        //edge case check always make sure no '0' startswith
        dp[1] = 1;
        //we do not have overlap because we consider one digit and two digits, they are totally 
        //differently
        for (int i = 2; i <= len; i++) {
            // 226--> first is [2,6)-->2
            // 226--> second is 22, so to get positon 2
            // we have two way possible, 1 is one way to postion i, another is 2 step to
            // position i
            // but we have to check whether each is valid, if not, then there would be only
            // one way.
            int first = Integer.valueOf(s.substring(i - 1, i));
            int second = Integer.valueOf(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1]; // originally dp[i] should be 0
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
            
            /*
             * this is another way of no need to trans to integer
             String first = s.substring(i-1,i);
            String second = s.substring(i-2, i);
            
            if (second.compareTo("10") >= 0 && second.compareTo("26") <= 0) {
                dp[i] += dp[i-2];
            }
            if (first.compareTo("1") >=0) {
                dp[i] += dp[i-1];
            }
             */
            
            
        }
        return dp[len];

    }

    //space : O(1)
    public int numDecodings2(String s) {
        // edge case
        if (s == null || s.length() < 1 || s.charAt(0) == '0') {
            return 0;
        }

        // c2 is one character less than c1
        // 优化：既然是斐波纳数列，那么就能使用双值迭代的方式取代用一个数组进行记录。
        int c1 = 1, c2 = 1;
        for (int i = 1; i < s.length(); i++) {
            // if the 0 character, there will be only 1 method to use 0, that;s [1-9]0
            // so here c1 would be the same as before.
            if (s.charAt(i) == '0') {
                c1 = 0;
            }
            // if previous character is 1 then we have 2 way to interpret.
            // if it is under 26 the same
            if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) <= '6') {
                // c1 means
                c1 = c1 + c2;
                c2 = c1 - c2;

                // if they are out of scope,
            } else {
                c2 = c1;
            }
        }
        return c1;

    }

    public int numDecodings3(String s) {
        // edge case
        if (s == null || s.length() < 1 || s.charAt(0) == '0') {
            return 0;
        }
        int n = s.length();
        int[] memo = new int[n + 1];
        memo[n] = 1;
        memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

        for (int i = n - 2; i >= 0; i--)
            if (s.charAt(i) == '0') continue;
            else
                memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ?
                        memo[i + 1] + memo[i + 2] : memo[i + 1];

        return memo[0];
    }
}
