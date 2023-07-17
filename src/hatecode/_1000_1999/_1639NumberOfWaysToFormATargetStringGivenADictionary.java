package hatecode._1000_1999;

public class _1639NumberOfWaysToFormATargetStringGivenADictionary { 
    /*
    1639. Number of Ways to Form a Target String Given a Dictionary

    You are given a list of strings of the same length words and a string target.

Your task is to form target using the given words under the following rules:

target should be formed from left to right.
To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
Repeat the process until you form the string target.
Notice that you can use multiple characters from the same string in words provided the conditions above are met.

Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.

 

Example 1:

Input: words = ["acca","bbbb","caca"], target = "aba"
Output: 6
Explanation: There are 6 ways to form target.
"aba" -> index 0 ("a cca"), index 1 ("b b bb"), index 3 ("cac a")
"aba" -> index 0 ("a cca"), index 2 ("bb b b"), index 3 ("cac a")
"aba" -> index 0 ("a cca"), index 1 ("b b bb"), index 3 ("acc a") -- first string
"aba" -> index 0 ("a cca"), index 2 ("bb b b"), index 3 ("acc a")
"aba" -> index 1 ("c a ca"), index 2 ("bb b b"), index 3 ("acc a")
"aba" -> index 1 ("c a ca"), index 2 ("bb b b"), index 3 ("cac a")
    */
    
    /*
      w = words.length, m = words[0].length, n = target.length
      thinking process: O(wn + m * min(m, n))/O(mn)

      the problem is to say: given string list as words and target string, each string in words have same length, like below "i"(word matrix), you will need 
      to make string target from left to right, 

      each time you can pick one character from words list, suppose you have one 0-j, then you can only choose from j + 1 column from the word list

      return how many ways you can form target string

       i        j
      acca      aba
      bbbb
      caca
      0123
      
      
     j =0, to form 'a', we can start from column 0, 1, 3,   if we choose, then 'b' have 2 choices, each last 'a' will have 2 choices, 

     if 'a' start from 1, we still have 2 choices, 

     so it is easy that we want to have frequency of each character, cnt[i][26], i is the column index, it represents the count of one character frequency 
     for that column, the better part of this view is to help we won;t choose character before i

     dp[i][j] means for word list matrix as above, the number of ways to form 0-j substring of target with 0-i columns from original word matrix

     we have 2 cases:
     1. if we do not use column j character, then it will be dp[i-1][j] ways, because we always formed the 0-j string
     2. if we use column j character, then it will be dp[i-1][j-1] * cnt[i][target[j-1]-'a']


    
     
     
      
    


      the way how to solve this problem is that: with the example, we can surely know 
    
      
  reference: 

  i   dp table: 
     0 1 2 3      -----j  
  0  1 0 0 0 
  1  1 1 0 0 
  2  1 2 1 0 
  3  1 2 3 0 
  4  1 4 5 6 
     

     
    */
    public static int numWays(String[] words, String t) {
        
        int m = words[0].length(), n = t.length();
        
        long[][] dp = new long[m+1][n+1];
        long mod = (long)1e9+7;
        int[][] cnt = new int[m+1][26];
        
        for(int i = 0; i<words.length; i++) {
            for(int j = 0; j <m; j++) {
                char c = words[i].charAt(j);
                cnt[j+1][c-'a']++;
            }
        }
        
        for(int i = 0; i<=m; i++) {
            dp[i][0] = 1;
            for(int j = 1; j<= Math.min(i, n); j++) {
                dp[i][j] = dp[i-1][j];
                dp[i][j] += dp[i-1][j-1] * cnt[i][t.charAt(j-1) -'a'];
                dp[i][j] %=mod;
            }
        }
        //printDPTable(dp);
        
        return (int)dp[m][n];
        
    }

    public static void printDPTable(long[][] dp) {
        int m = dp.length;
        int n = dp[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        String[] words = {"acca", "bbbb", "caca"};
        String target = "aba";
        System.out.println(numWays(words, target));
    }
}