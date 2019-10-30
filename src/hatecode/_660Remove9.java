package hatecode;

import java.util.*;
public class _660Remove9 {
/*
660. Remove 9
Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...

So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...

Given a positive integer n, you need to return the n-th integer after removing. Note that 1 will be the first integer.

Example 1:
Input: 9
Output: 10
*/
    
    
    
    //thinking process: The problem is to say to give 1->n numbers, remove all numbers which contains 9 digit, then return the n-th number
    
    //the key is to calculate how many numbers which contains 9 from 1-> n, 
    //
    //O(logn)/O(1)
    public int newInteger(int n) {
        int ans = 0;
        //1,10,100,1000
        int base = 1;
        //calculate 9 as radix, what is n-th number
        while (n > 0){
            ans += n % 9 * base;
            n /= 9;
            base *= 10;
        }
        return ans;
    }
    //O(1)/O(1)，9 as radix, 
    //8,9,10->8,10 means radix as 9 sequence
    public int newInteger_Best(int n) {
        return Integer.parseInt(Integer.toString(n, 9));
    }

    //like this better, because it showed how we can process it.  
    //and it applies to other digits, like 7 or 4,
    //
    int[] dp;
    public int newInteger_BruteForce(int n) {
        if (n < 9) return n;
        dp = new int[10];
        //dp[n] corresponds to range[10^(n - 1), 10^n - 1], 
        //e.g. dp[2] is the number of 9 within [10, 99]
        dp[0] = 0;
        dp[1] = 1;
        int p = 10;
        for (int i = 2; i < 10; i++) {
            dp[i] = dp[i - 1] * 9 + p;
            p *= 10;
        }
        int l = n;
        int r = Integer.MAX_VALUE;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (mid - numOf9(mid) >= n) {  // make sure the result does not contains 9
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
    private int numOf9(int x) {  // 10 <= x <= MAX_INT
        int res = 0;
        int num = x;
        int i = 0;
        int p = 1;
        while (num != 0) {
            int lastdigit = num % 10;
            if (lastdigit == 9) {
                res = 9 * dp[i] + x % p + 1;
            } else {
                res += lastdigit * dp[i];
            }
            i++;
            p *= 10;
            num /= 10;
        }
        return res;
    }
}