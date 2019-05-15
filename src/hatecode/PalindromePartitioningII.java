package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : PalindromePartitioningII
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 132. Palindrome Partitioning II
 * tags: two pointers, dp
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
    This can be solved by two pointers:

   cut[i] is the minimum of cut[j - 1] + 1 (j <= i), if [j, i] is palindrome.
   If [j, i] is palindrome, [j + 1, i - 1] is palindrome, and c[j] == c[i].
   The 2nd point reminds us of using dp (caching).

   a   b   a   |   c  c
                   j  i
          j-1  |  [j, i] is palindrome
      cut(j-1) +  1

 */
    // interview friendly:
    // we want to know in string s, where to cut the string so each string partition is 
    //palindrome and cuts has smallest number, so 
    // we want to have array cuts[] will record for s 0->s.length, cuts[i]= s[i]'s 
    //minimal cuts, so cuts[i] = min(i, cuts[i-1] + 1) why? because i is the index of string
    // min should be maxium in s[i], 
    
    // think about this way. when a | b a  c  c,  the best is there is no palindrome, so it will be 
    // n- 1 cuts,  the best is whole string is paindrome, so cut will be 0;
    
    // when we know cuts[i - 1], how can we know cuts[i], so when we have a new character in the end
    // of the string, we want to know whether the new char can help to reduce the cuts or at most +1.
    
    // so we assume cuts[i] = i first, and we detect j = 0-> i, i mean the new char, at j's position,
    // if plindrome[j+1][i] means true,which help to dup calculation of palindrome again and again, 
    // so we could be sure that we only need to care s[j] and s[i], 
    
    // remeber this templates
    public int minCut(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int n = s.length();
        //cuts[i] means string 0->i, the max cut
        int[] cuts = new int[n];
        
        boolean[][] isPalindrome = new boolean[n][n];
        
        for(int i = 0; i < n; i++) {
            //cuts initialize, each character is one palindrome and this is max for j -> i substring
            int min = i;
            for(int j = 0; j <= i; j++) {
                // j +1, i - 1. means string between j and i, 
                // so this means i and j are outest char and if internal is palindrome, so whole string
                // will be palindrome
                //j+1 > i-1 -> 2 > i-j, which means i = j or j+1=i, 
                if(s.charAt(i) == s.charAt(j) && (i == j || i == j+1 || isPalindrome[j+1][i-1])) {
                    isPalindrome[j][i] = true;
                    //min default is index i which is maxium cuts, 
                    //so how the code runs:
                    //every time, when i is fixed, we initialize the max possuble cut value min = i, 
                    //then from j ->i, we try to detect the longest palindrome so we can reduce the cuts, 
                    //every time we will remember the min cuts with the string length, so we use cuts[i] here
                    min = j == 0 ? 0 : Math.min(min, cuts[j-1] + 1);
                }
            }
            cuts[i] = min;
        }
        return cuts[n -1];
    }

    //recursion solutions, best performance and it use expand from one central point to both sides
    public int minCut_Recursion(String s){
        int len = s.length();
        int[] f = new int[len];
        for (int i = 0; i < len; i++){
            f[i] = i;
        }
        for (int i = 0; i < len; i++){
            search(s, f, i, i);
            search(s, f, i, i + 1);
        }
        return f[len - 1];
    }
    
    private void search (String s, int[] f, int left, int right){
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            if (left == 0){
                f[right] = 0;
            } else {
                f[right] = Math.min(f[right], f[left - 1] + 1);
            }
            left--;
            right++;
        }
        
    }

}
