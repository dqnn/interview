package hatecode;
import java.util.*;
public class RotatedDigits {
/*
788. Rotated Digits
X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A number is valid if each digit remains a digit after rotation. 0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 6 and 9 rotate to each other, and the rest of the numbers do not rotate to any other number and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
*/
    //O(NlgN)/O(lgN), 10 is base brute force, 
    //the problem is to say we given a number, if each digit is 0,1, 8 then rotate it value does not change, 
    //while 2,5, 6, 9 will, others will be not be good number, so count how many good numbers from 1 to N 
    
    
    // so we know only if they at least contain 2,5,6,9 then it would be good, but they cannot have others, like 3,4,7
    //so these 2 sets will be our primary checking source
    public int rotatedDigits2(int N) {
        //0, 1, 8--. them
        //2--5, 6--9
        if (N <= 1) return 0;
        Set<Character> invalidNumSet = new HashSet<>(Arrays.asList('3', '4', '7'));
        Set<Character> validset = new HashSet<>(Arrays.asList('2', '5', '6', '9'));        
        //value must change and still valid number
        int count = 0;
        for(int i = 1; i <= N;i++) {
            boolean validDigit = false;
            char[] chs = String.valueOf(i).toCharArray();
            
            for(int j = 0; j< chs.length; j++) {
                if (invalidNumSet.contains(chs[j])) break;
                if(validset.contains(chs[j])) validDigit = true;
                if (j == chs.length - 1 && validDigit) {
                    count++;
                }
            }
        }
        return count;
    }
    
 /*
 We can use dynamic programming to solve this efficiently. Our state will be how many digits i we have written, whether we have previously written a jth digit lower than the jth digit of N, and whether we have previously written a digit from 2569. We will represent this state by three variables: i, equality_flag, involution_flag.

dp(i, equality_flag, involution_flag) will represent the number of ways to write the suffix of N corresponding to these above conditions. The answer we want is dp(0, True, False).

Algorithm

If equality_flag is true, the ith digit (0 indexed) will be at most the ith digit of N. For each digit d, we determine if we can write d based on the flags that are currently set.

In the below implementations, we showcase both top-down and bottom-up approaches. The four lines in the top-down approach (Python) from for d in xrange(... to before memo[...] = ans clearly illustrates the recursive relationship between states in our dynamic programming.


 */ //O(lg(N))/O(lg(N)) 10 is base? need more time on this solution
    public int rotatedDigits(int N) {
        char[] A = String.valueOf(N).toCharArray();
        int K = A.length;

        int[][][] memo = new int[K+1][2][2];
        memo[K][0][1] = memo[K][1][1] = 1;
        for (int i = K - 1; i >= 0; --i) {
            for (int eqf = 0; eqf <= 1; ++eqf)
                for (int invf = 0; invf <= 1; ++invf) {
                    // We will compute ans = memo[i][eqf][invf],
                    // the number of good numbers with respect to N = A[i:].
                    // If eqf is true, we must stay below N, otherwise
                    // we can use any digits.
                    // Invf becomes true when we write a 2569, and it
                    // must be true by the end of our writing as all
                    // good numbers have a digit in 2569.
                    int ans = 0;
                    for (char d = '0'; d <= (eqf == 1 ? A[i] : '9'); ++d) {
                        if (d == '3' || d == '4' || d == '7') continue;
                        boolean invo = (d == '2' || d == '5' || d == '6' || d == '9');
                        ans += memo[i+1][d == A[i] ? eqf : 0][invo ? 1 : invf];
                    }
                    memo[i][eqf][invf] = ans;
                }
        }

        return memo[0][1][0];
    }
    
    //another simpler DP, O(N)/O(N)
    //thinking process: 
    //we use dp[] 0, 1, 2 to whether number i is good number or not. 
    //so when i < 10, we can easily to get value for each dp[i]
    //when i >= 10, we break i into i /10 and i % 10, 
    //if we can leverage previous conclusion
    public int rotatedDigits_DP(int N) {
        //dp[i] = 0 means invalid number, 1 valid but same, 2 valid not same
        int[] dp = new int[N + 1];
        int count = 0;
        for(int i = 0; i <= N; i++){
            //this is to assign values for dp begin values
            if(i < 10){
                if(i == 0 || i == 1 || i == 8) dp[i] = 1;
                else if(i == 2 || i == 5 || i == 6 || i == 9){
                    dp[i] = 2;
                    count++;
                }
            } else {
                int a = dp[i / 10], b = dp[i % 10];
                if(a == 1 && b == 1) dp[i] = 1;
                //number i is valid when one is 2,5,6,9,another is in 0, 1 ,8, so here
                //we need to use >=
                else if(a >= 1 && b >= 1){
                    dp[i] = 2;
                    count++;
                }
            }
        }
        return count;
    }
}