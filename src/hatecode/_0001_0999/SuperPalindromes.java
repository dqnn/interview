package hatecode._0001_0999;

import java.util.*;
public class SuperPalindromes {
/*
906. Super Palindromes
Let's say a positive integer is a superpalindrome if it is a palindrome, and it is also the square of a palindrome.

Now, given two positive integers L and R (represented as strings), return the number of superpalindromes in the inclusive range [L, R].

 

Example 1:

Input: L = "4", R = "1000"
Output: 4
*/
    // same as 564
    //thinking process: give a number interval as stirng [L, R], return the x L <=x <=R, 
    //which x can satisfy y * y = x and x, y are palindrome
    
    //
        public int superpalindromesInRange(String L, String R) {
        Long l = Long.valueOf(L), r = Long.valueOf(R);
        int result = 0;
        for (long i = (long)Math.sqrt(l); i * i <= r;) {
            long p = nextP(i);
            if (p * p <= r && isP(p * p)) {
                result++;
            }
            i = p + 1;
        }
        return result;
    }
    //we use input l return the smallest palindrom which is greater than l, for example 121, then it will return 1221
    //cands have 3 number, 10^len - 1=99999..., split l into two parts, each one is able to have its own 
    //palindrom, then we have 3 palindrom, we get the min one
    private long nextP(long l) {
        String s = "" + l;
        int len = s.length();
        List<Long> cands = new LinkedList<>();
        cands.add((long)Math.pow(10, len) - 1);
        String half = s.substring(0, (len + 1) / 2);
        String nextHalf = "" + (Long.valueOf(half) + 1);
        String reverse = new StringBuilder(half.substring(0, len / 2)).reverse().toString();
        String nextReverse = new StringBuilder(nextHalf.substring(0, len / 2)).reverse().toString();
        cands.add(Long.valueOf(half + reverse));
        cands.add(Long.valueOf(nextHalf + nextReverse));
        long result = Long.MAX_VALUE;
        for (long i : cands) {
            if (i >= l) {
                result = Math.min(result, i);
            }
        }
        return result;
    }
    //verify the number is palindrome
    private boolean isP(long l) {
        String s = "" + l;
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }
}