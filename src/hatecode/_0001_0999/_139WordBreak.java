package hatecode._0001_0999;

import java.util.*;


/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordBreak
 * Date : Sep, 2018
 * Description : 139. Word Break
 */
public class _139WordBreak {

    /**
     * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
     * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
     * You may assume the dictionary does not contain duplicate words.

     For example, given
     s = "leetcode",
     dict = ["leet", "code"].

     Return true because "leetcode" can be segmented as "leet code".

     time : O(n^2);
     space : O(n);

     s = "leetcode",
     dict = ["leet", "code"]


     * @param s
     * @param wordDict
     * @return
     */
    // thinking progress:O(n^3)/O(n)
    // the problem is say yes or no when wordDict contains substrings of s or 
    //not space separated
    
    // typical DP, 0 <= j <i, 
    // dp[j] means substring s[0, j) is in dic, so dp[i] = dp[j] && dic.conains(s[j,i)
    
    //key: 1. i = s.length() 
    //2 i < i cannot be =
    // for last char we have to access, we always use dp[n +1]
    public boolean wordBreak(String s, List<String> wordDict) {
        if(s == null || s.length() < 1) return false;
        Set<String> set = new HashSet<>(wordDict);
        
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            //we break because already find it, so we skip to next i
            // this for loop is mainly to verify j, i is in the dict or not
            for (int j = 0; j < i; j++) {
                String str = s.substring(j, i);
                if(dp[j] && set.contains(str)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
    
    //DFS solutions
    private boolean[] visited = null;
    private Set<String> dic = null;
    public boolean wordBreak_DFS(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        visited = new boolean[s.length()+1];
        dic = new HashSet<>(wordDict);
        //wordDict.stream().forEach(e->dic.add(e));
        return helper(s,0);
    }
    
    private boolean helper(String s, int start) {
        if (start == s.length()) return true;
        visited[start] = true;
        
        for(int i = start + 1; i<= s.length(); i++) {
            String sub = s.substring(start, i);
            if (dic.contains(sub) && !visited[start + sub.length()]) {
                if (helper(s, i)) return true;
            }
        }
        return false;
    }
}
