package leetcode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromePartitioningII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 132. Palindrome Partitioning II
 */
public class PalindromePartitioningII {
    /**
     * Given a string s, partition s such that every substring of the partition is a 
     * palindrome.

     Return the minimum cuts needed for a palindrome partitioning of s.

     For example, given s = "aab",
     Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

     [][] isPalindrome
     [] cuts
     i   j
     abcba s.charAt(i) = s.charAt(j) && isPalindrome[i+1][j-1]


     time : O(n^2)
     space : O(n^2)

     * @param s
     * @return
     */
    /*
    This can be solved by two points:

   cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
   If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
   The 2nd point reminds us of using dp (caching).

   a   b   a   |   c  c
                   j  i
          j-1  |  [j, i] is palindrome
      cut(j-1) +  1
    */
    public int minCut(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int len = s.length();
        int[] cuts = new int[len];
        
        boolean[][] isPalindrome = new boolean[len][len];
        
        for(int i = 0; i < len; i++) {
            //cuts initialize
            int min = i;
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j < 2 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                    min = j == 0? 0 : Math.min(min, cuts[j-1] + 1);
                }
            }
            cuts[i] = min;
        }
        return cuts[len -1];
    }

}
