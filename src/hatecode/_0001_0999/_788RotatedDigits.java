package hatecode._0001_0999;
import java.util.*;
public class _788RotatedDigits {
/*
788. Rotated Digits
X is a good number if after rotating each digit individually by 180 degrees, we get a valid number that is different from X.  Each digit must be rotated - we cannot choose to leave it alone.

A good number is valid if each digit remains a digit after rotation. 
0, 1, and 8 rotate to themselves; 2 and 5 rotate to each other; 
6 and 9 rotate to each other, 
and the rest of the numbers do not rotate to any other number 
and become invalid.

Now given a positive number N, how many numbers X from 1 to N are good?

Example:
Input: 10
Output: 4
*/
    //O(NlgN)/O(lgN), 10 is base, brute force, 
    //the problem is to say we given a number, if each digit is 0,1, 8 
    //then rotate it value does not change, 
    //while 2,5, 6, 9 will, others will be not be good number, 
    //so count how many good numbers from 1 to N 
    
    
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
            
            //we do not need to worry about 1, 8 0 numbers, because if a number
            //like 18, 10, validDigits will be false
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
  * Best on TC: O(lgn)/O(lgn)
  */
    /*
     * Pow 7 as base means we have 7 choices, sets 2
     * Pow 3 as base means we have to remove 3 choices, sets1
     */
    public int rotatedDigits(int A) {
        char[] chs = String.valueOf(A).toCharArray();
        int res = 0;
        HashSet<Integer> digits = new HashSet<>();
        HashSet<Integer> set1 = new HashSet<>(Arrays.asList(0,1,8));
        HashSet<Integer> set2 = new HashSet<>(Arrays.asList(0, 1, 8, 2, 5, 6, 9));

        for (int i = 0; i < chs.length; i++) {
            int digit = chs[i] - '0';
            for (int j = 0; j < digit; j++) {
                if (set2.contains(j)) {
                    res += (int)Math.pow(7, chs.length - i - 1);
                }
                //all previous chars are 0, 1, 8 including j 
                if (set1.containsAll(digits) && set1.contains(j)) {
                    res -= (int)Math.pow(3, chs.length - i - 1);
                }
            }
            digits.add(digit);
            if (!set2.contains(digit)) {
                return res;
            }

        }

        return res + (!set1.containsAll(digits) ? 1 : 0);
    }
    
    //another simpler DP, O(N)/O(N)
    //thinking process: 
    /*
     *  dp[i] = 0, invalid number
        dp[i] = 1, valid and same number
        dp[i] = 2, valid and different number
     */
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