package hatecode._0001_0999;
import java.util.*;
public class _727MinimumWindowSubsequence {
    /*
     * 727. Minimum Window Subsequence Given strings S and T, find the minimum
     * (contiguous) substring W of S, so that T is a subsequence of W.
     * 
     * If there is no such window in S that covers all characters in T, return the
     * empty string "". If there are multiple such minimum-length windows, return
     * the one with the left-most starting index.
     * 
     * Example 1:
     * 
     * Input: S = "abcdebdde", T = "bde" Output: "bcde" Explanation: "bcde" is the
     * answer because it occurs before "bdde" which has the same length. "deb" is
     * not a smaller window because the elements of T in the window must occur in
     * order.
     */  
    /*
    dp[i][j] stores the starting index of the substring where T has length i and S has length j.

So dp[i][j would be:
if T[i - 1] == S[j - 1], this means we could borrow the start index from dp[i - 1][j - 1] to make the current substring valid;
else, we only need to borrow the start index from dp[i][j - 1] which could either exist or not.

Finally, go through the last row to find the substring with min length and appears first.
    */
    
    //Two Pointers,
    public static String minWindow(String S, String T) {
        char[] s = S.toCharArray(), t = T.toCharArray();
        int sIdx = 0, tIdx = 0, sLen = s.length, tLen = t.length, start = -1, len = sLen;
        while(sIdx < sLen) {
            //first move sindex to first match
            if(s[sIdx] == t[tIdx]) {
                //tIndex actually first move to last idx then move back
                if(++tIdx == tLen) {
                    //check feasibility from left to right of T
                    int end = sIdx+1;
                    //check optimization from right to left of T
                    while(--tIdx >= 0) {
                        while(s[sIdx--] != t[tIdx]);
                    }
                    ++sIdx;
                    ++tIdx;
                    //record the current smallest candidate
                    if(end - sIdx < len) {
                        len = end - sIdx;
                        start = sIdx;
                    }
                }
            }
            ++sIdx;
        }
        return start == -1? "" : S.substring(start, start + len);
    }
    //dp[i][j] means: for S(0~i) & T(0~j), the "largest" starting index in S which 
    //matches T
    public String minWindow5(String S, String T) {
        int[][] M = new int[S.length()][T.length()];
        for(int i = 0; i < S.length(); i ++){
        // largest starting index matches only first char in T
            if(S.charAt(i) == T.charAt(0)) M[i][0] = i;
            else {
             // S, T both have 1 char, if !match, fill -1 means we 
             //can't find a substring for S(0) & T(0)
                if(i == 0) M[i][0] = -1;    
                else M[i][0] = M[i - 1][0];
            }
        }
        for(int j = 1; j < T.length(); j ++){
            for(int i = 0; i < S.length(); i ++){
                if(S.charAt(i) == T.charAt(j)){
                    if(i == 0) M[i][j] = -1;       //  Actually, if j > i, M[i][j] is always -1, cause we cant find a substring of a shorter string matches a longer string
                    else M[i][j] = M[i - 1][j - 1];  // As share2017 mentioned in his post
                }else{
                    if(i == 0) M[i][j] = -1;      
                    else M[i][j] = M[i - 1][j];  
                }
            }
        }
        int start = 0;
        int len = Integer.MAX_VALUE;
        for(int i = 0; i < S.length(); i ++){
            if(M[i][T.length() - 1] != -1){
                if(i - M[i][T.length() - 1] + 1 < len){
                    len = i - M[i][T.length() - 1] + 1;
                    start = M[i][T.length() - 1];
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : S.substring(start, start + len);
    }
    //same as above, but use +1 trick to make code less
    public String minWindow6(String S, String T) {
        int m = T.length(), n = S.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j + 1;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (T.charAt(i - 1) == S.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        int start = 0, len = n + 1;
        for (int j = 1; j <= n; j++) {
            if (dp[m][j] != 0) {
                if (j - dp[m][j] + 1 < len) {
                    start = dp[m][j] - 1;
                    len = j - dp[m][j] + 1;
                }
            }
        }
        return len == n + 1 ? "" : S.substring(start, start + len);
    }
    public String minWindow3(String S, String T) {
        int[][] dp = new int[2][S.length()];

        for (int i = 0; i < S.length(); ++i)
            dp[0][i] = S.charAt(i) == T.charAt(0) ? i : -1;

        /*At time j when considering T[:j+1],
          the smallest window [s, e] where S[e] == T[j]
          is represented by dp[j & 1][e] = s, and the
          previous information of the smallest window
          [s, e] where S[e] == T[j-1] is stored as
          dp[~j & 1][e] = s.
        */
        for (int j = 1; j < T.length(); ++j) {
            int last = -1;
            Arrays.fill(dp[j & 1], -1);
            //Now we would like to calculate the candidate windows
            //"dp[j & 1]" for T[:j+1].  'last' is the last window seen.
            for (int i = 0; i < S.length(); ++i) {
                if (last >= 0 && S.charAt(i) == T.charAt(j))
                    dp[j & 1][i] = last;
                if (dp[~j & 1][i] >= 0)
                    last = dp[~j & 1][i];
            }
        }

        //Looking at the window data dp[~T.length & 1],
        //choose the smallest length window [s, e].
        int start = 0, end = S.length();
        for (int e = 0; e < S.length(); ++e) {
            int s = dp[~T.length() & 1][e];
            if (s >= 0 && e - s < end - start) {
                start = s;
                end = e;
            }
        }
        return end < S.length() ? S.substring(start, end+1) : "";
    }
    
    
    
    //TLE solution
    //b-{1,5}, d->{3,6,7}, e->{4,8}
    //to get{start, end}, start must from b, end must from e
    // 
    int res = Integer.MAX_VALUE;
    int len  = Integer.MAX_VALUE;
    public String minWindow2(String s, String t) {
        if (s == null || s.length() < 1 || t ==null || t.length() < 1) return "";
        
        Map<Character, List<Integer>> map =new HashMap<>();
        for(int i = 0; i<t.length();i++) {
            char ch = t.charAt(i);
            for(int j = 0; j < s.length();j++) {
                if (ch == s.charAt(j)) {
                    map.computeIfAbsent(ch, V-> new ArrayList<>()).add(j);
                }
            }
            if (map.get(ch) == null) return "";
        }
        System.out.println(map);
        helper(t, map, new ArrayList<>(), 0);
        if (res == Integer.MAX_VALUE || len == Integer.MAX_VALUE) return "";
        return s.substring(res,res +len);
    }
    
    private void helper(String t, Map<Character, List<Integer>> map, List<Integer> list, int start) {
        if (list.size() == t.length()) {
            //System.out.println("list:" + list);
            int curLen = list.get(list.size() - 1) - list.get(0) + 1;
            if ( curLen < len || curLen == len && list.get(0) < res) {
                len = curLen;
                res = list.get(0);
            }
            return;
        }
        
        for(int i = start; i<t.length();i++) {
            List<Integer> loop = map.get(t.charAt(i));
              for(int temp : loop) {
                  if (list.size() > 0 && list.get(list.size() -1) >= temp) continue;
                  list.add(temp);
                  helper(t, map, list, i+1);
                  list.remove(list.size() -1);
              }
        }
    }
    public static void main(String[] args) {
        System.out.println(minWindow("abcdebdde", "bde"));
    }
}