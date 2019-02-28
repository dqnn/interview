package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ScrambleString
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 87. Scramble String
 */
public class ScrambleString {

    /**
Given a string s1, we may represent it as a binary tree by partitioning 
it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap 
its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", 
it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, 
determine if s2 is a scrambled string of s1.

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
     time : O(n!) T(n) = n T(n-1)
     space : O(n)

     * @param s1
     * @param s2
     * @return
     */

    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;

        int[] letters = new int[26];
        int len = s1.length();
        for (int i = 0; i < len; i++) {
            letters[s1.charAt(i) - 'a']++;
            letters[s2.charAt(i) - 'a']--;
        }
        // this is to check letters are the same
        for (int i = 0; i < 26; i++) {
            if (letters[i] != 0) return false;
        }
        for (int i = 1; i < len; i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i))
                    && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0, i), s2.substring(len - i))
                    && isScramble(s1.substring(i), s2.substring(0, len - i))) return true;
        }
        return false;
    }
    
    // this is better solution on Complexity O()n ^ 4
    
    // this problem is needed to remembered and discussed
    public boolean isScramble2(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        int len = s1.length();
        /**
         * Let F(i, j, k) = whether the substring S1[i..i + k - 1] is a scramble of S2[j..j + k - 1] or not
         * Since each of these substrings is a potential node in the tree, we need to check for all possible cuts.
         * Let q be the length of a cut (hence, q < k), then we are in the following situation:
         * 
         * S1 [   x1    |         x2         ]
         *    i         i + q                i + k - 1
         * 
         * here we have two possibilities:
         *      
         * S2 [   y1    |         y2         ]
         *    j         j + q                j + k - 1
         *    
         * or 
         * 
         * S2 [       y1        |     y2     ]
         *    j                 j + k - q    j + k - 1
         * 
         * which in terms of F means:
         * k is the cut length
         * F(i, j, k) = for some 1 <= q < k we have:
         *  (F(i, j, q) AND F(i + q, j + q, k - q)) OR (F(i, j + k - q, q) AND F(i + q, j, k - q))
         *  
         * Base case is k = 1, where we simply need to check for S1[i] and S2[j] to be equal 
         * */
        // templates for e dimension DP
        //i,j, k, k as elements count to count s1 [i, i + k - 1] vs S2, [j , j + k -1] are scramble or not
        
        //i means s1 start index, j means s2 start index, k means length of the 
        //compared string
        boolean [][][] F = new boolean[len][len][len + 1];
        for (int k = 1; k <= len; ++k)
            for (int i = 0; i + k <= len; i++)
                for (int j = 0; j + k <= len; j++)
                    if (k == 1)
                        // first k, just like initialize the value
                        F[i][j][k] = s1.charAt(i) == s2.charAt(j);
                    else {
                        //(F(i, j, q) AND F(i + q, j + q, k - q)) means first S1 X1 S2 X1
                        //F(i + q, j + q, k - q) means S1 X2 and S2 and Y2
                        // k - q means the length between j +q => j+k -1, j + k - 1 -(k + q) + 1
                        for (int q = 1; q < k && !F[i][j][k]; q++) {
                            F[i][j][k] = (F[i][j][q] && F[i + q][j + q][k - q]) 
                            
                            //F[i][j + k - q][q] means S1 X1--> S2 Y2
                            // i is beginning of S1, 
                           // this time, q is from end of S2, so since len is k, so start
                           //point would be j + k - q
                           //F[i + q][j][k - q] same as previous
                            || (F[i][j + k - q][q] && F[i + q][j][k - q]);
                        }
                    }
        return F[0][0][len];
    }
}
