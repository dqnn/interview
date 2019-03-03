package hatecode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class DecodeWaysII {
    /*
     * 639. Decode Ways II A message containing letters from A-Z is being encoded to
     * numbers using the following mapping way:
     * 
     * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Beyond that, now the encoded string can also
     * contain the character '*', which can be treated as one of the numbers from 1
     * to 9.
     * 
     * Given the encoded message containing digits and the character '*', return the
     * total number of ways to decode it.
     * 
     * Also, since the answer may be very large, you should return the output mod
     * 109 + 7.
     * 
     * Example 1: Input: "*" Output: 9
     */
    
        //thinking process: 
        //we have two types different character interpreting, 
    // we target last status, dp[i] which means s(0,i - 1) how many decode ways, let's see
    // relationship with dp[i-1] and dp[i-2],why i -1 and i -2 because 
    // example, 13*3, we can divide two interpreting, like (13*)3 and 13(*3), that's why 
    //and the result is two types sum.
/*
 For dp[i-1]:

                  /           \
                 /             \
            s[i-1]='*'    s[i-1]>0     
                |               |
          + 9*dp[i-1]        + dp[i-1]

             
        For dp[i-2]:

                   /                                  \
                  /                                    \  
              s[n-2]='1'||'2'                         s[n-2]='*'
              /            \                       /             \     
        s[n-1]='*'         s[n-1]!='*'          s[n-1]='*'       s[n-1]!='*'
         /       \               |                  |              /         \
  s[n-2]='1'  s[n-2]='2'   num(i-2,i-1)<=26         |         s[n-1]<=6    s[n-1]>6
      |            |             |                  |              |            |
 + 9*dp[i-2]   + 6*dp[i-2]     + dp[i-2]       + 15*dp[i-2]    + 2*dp[i-2]   + dp[i-2]
 */
    
    //for dp[i-1], (13*)3, we can see 
    public static int numDecodings(String s) {
        /* initial conditions */
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0') {
            return 0;
        }
        dp[1] = (s.charAt(0) == '*') ? 9 : 1;

        /* bottom up method */
        for (int i = 2; i <= s.length(); i++) {
            char first = s.charAt(i - 2);
            char second = s.charAt(i - 1);

            // For dp[i-1], (13*)3 case, we only think s[i-1] as one single character,
            //in this case, it would only two cases:
            //dp[i] += dp[i-1] (second > '0') or 
            //dp[i] +=  9 * dp[i-1] (second = "*")
            if (second == '*') dp[i] += 9 * dp[i - 1];
            else if (second > '0') dp[i] += dp[i - 1];

            // we consider i-1 and i-2 as one character to decode
            
            //so first one must be 1, 2 or * if not, dp[i] just be the same as dp[i-2]
            if (first == '*') {
                if (second == '*')  dp[i] += 15 * dp[i - 2];
                else if (second <= '6')  dp[i] += 2 * dp[i - 2];
                else dp[i] += dp[i - 2];
            } else if (first == '1' || first == '2') {
                if (second == '*') {
                    if (first == '1')  dp[i] += 9 * dp[i - 2];
                    else dp[i] += 6 * dp[i - 2]; // first == '2'
                 //this means two number,like 19, 25,etc, but there would be only 1 way to 
                    //decode
                } else if (((first - '0') * 10 + (second - '0')) <= 26) {
                    dp[i] += dp[i - 2];
                }
            }

            dp[i] %= Math.pow(10, 9) + 7;
        }
        /* Return */
        return (int) dp[s.length()];
    }
    
    public static void main(String[] args) {
        String in = "13*3";
        System.out.println(numDecodings(in));
    }
    
    @Test
    public void testInput1start() {
        assertEquals(18, numDecodings("1*"));
    }
    
    @Test
    public void testInputstart() {
        assertEquals(9, numDecodings("*"));
    }
    
    // * = 1, 18  * = 2, 15, * =(3,9), 7 x 9 = 63, so 96
    @Test
    public void testInputstarts() {
        assertEquals(96, numDecodings("**"));
    }
}