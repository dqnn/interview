package hatecode._2000_2999;

public class _2002MaximumProductOfTheLengthOfTwoPalindromicSubsequences {
/*
2002. Maximum Product of the Length of Two Palindromic Subsequences

Given a string s, find two disjoint palindromic subsequences of s such that the product of their lengths is maximized. The two subsequences are disjoint if they do not both pick a character at the same index.

Return the maximum possible product of the lengths of the two palindromic subsequences.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters. A string is palindromic if it reads the same forward and backward.

 

Example 1:

example-1
Input: s = "leetcodecom"
Output: 9
Explanation: An optimal solution is to choose "ete" for the 1st subsequence and "cdc" for the 2 subsequence.
The product of their lengths is: 3 * 3 = 9.

*/

/*
 * thinking process: O(3^n)/O(n)
 * 
 * the problem is to say: given one string, return the max product of its length of its 2  sequences, 
 * the subsequences cannot have any character in common, disjoint set
 * 
 * for example  3* 3 = 9
 *   e   t       e
 * l e e t c o d e c o m
 *         c   d   c
 * 
 * 
 * the ask is the return the max product of two sequences, this reminds me about dp[i][j], the substring [i, j] but it would 
 * be pretty difficult to compute the length, 
 * 
 * so if we think about brute force, let's say we start from position 0 to form string, to make sure the two string have no 
 * same charcter, one character can belong to s1, or s2, or neither, 
 * 
 * so we can have below tree
 *     leetcodecom
 *       /  |    \
 *    ""    "l"  ""
 *    "l"   ""   ""
 *  / | \ 
 * 
 * 
 */
    int max = 0;
    public int maxProduct(String s) {
        if (s == null || s.length() < 1) return 0;

        char[] chs = s.toCharArray();
        helper(chs, 0, "", "");

        return max;
    }

    private void helper(char[] A, int pos, String s1, String s2) {
        if(pos >= A.length) {
            if(isPalin(s1) && isPalin(s2)) {
                max = Math.max(max, s1.length() * s2.length());
            }
            return;
        }

        helper(A, pos+1, s1 + A[pos], s2);
        helper(A, pos+1, s1, s2+ A[pos]);
        helper(A, pos+1, s1, s2);
    }


    private boolean isPalin(String s) {
        int l = 0, r = s.length() -1;

        while(l < r) {
            if(s.charAt(l) == s.charAt(r)) {
                l++;
                r--;
                continue;
            }
            else return false;
        }

        return true;
    }
}
