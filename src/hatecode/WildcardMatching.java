package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WildcardMatching
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 44. Wildcard Matching
 */
public class WildcardMatching {
    /**
     * Implement wildcard pattern matching with support for '?' and '*'.

     '?' Matches any single character.
     '*' Matches any sequence of characters (including the empty sequence).

     The matching should cover the entire input string (not partial).

     The function prototype should be:
     bool isMatch(const char *s, const char *p)

     Some examples:
     isMatch("aa","a") → false
     isMatch("aa","aa") → true
     isMatch("aaa","aa") → false
     isMatch("aa", "*") → true
     isMatch("aa", "a*") → true
     isMatch("ab", "?*") → true
     isMatch("aab", "c*a*b") → false

     "bbarc" match = 3 sp = 3
     "*c" star = 0 pp = 1

1. b equals *, 

     time : O(n)
     space : O(1)

     * @param s
     * @param p
     * @return
     */
    // bbarc vs *c is best to demonstrate this question
    //thinking process:
    
    // so compare s and p whther they are same, assume p has ? and *, 
    
    // we use sp and pp as pointe in s and p to mark where we are, match is to point which position
    // string s matched. star is the * index in p
    
    //  while (sp < s.length) becuase s should be longer, and for each char we mainly consider 
    // several use cases
    // 1. we matched several chars and continue, 2 use cases, real char and *
    // 2. we meet star 
    // 3. does not match but previous has star so we continue move
    
    //the best example for this is 
    //bbarc and b?*c, it contains all 3 use cases. 
    
    //use bbarc and b?*c as example, so if char at same position is not the same, 
    public boolean isMatch(String s, String p) {
        //sp means string s pointer
        int sp = 0;
        //pp means  string p pointer
        int pp = 0;
        // match position index in s for star
        int match = 0;
        // means the position of "*" in p
        int star = -1;
        while (sp < s.length()) {
            //1 the chars are the same, 
            //2 p has ? so we can continue
            if (pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
               //this is the place where only sp will move
                sp++;
                pp++;
            //use case aaaaa ***a
            //sp will stay at 0 in s, while pp will move to the end of p
            } else if (pp < p.length() && p.charAt(pp) == '*') {
                star = pp;
                match = sp;
                //so pp has to move to next char since current is * if not, this would be dead loop
                pp++;
            // so here stat can help to continue until to end of s
            } else if (star != -1) {
                //pp will stop next postion next to "*"
                //s= "acdcb"  p = "a*c?b" this use cases, we have to make 
                //pp always to stay behind * because if not, next time s char c = p char p, 
                //and sp ++ and pp++, so this would be wrong
                pp = star + 1;
                //match means position in s move to next, like aaaaa ***a, match+ will move sp to 
                //last char
                match++;
                //so here we need to match to tell sp to move to the position
                sp = match;
            } else return false;
        }
        // this means all left in p are all "*" since s already done, so 
        //we reached to the end of this string
        while (pp < p.length() && p.charAt(pp) == '*') {
            pp++;
        }
        return pp == p.length();
    }
    //DP string match templates, also the templates for any 2D dp matrix
    //the only difference is about dp foruma, for *, regex match is to remove previous 
    //char but wild char cannot
    public boolean isMatch_Templates(String s, String p) {
        if (s == null &&  p == null) return false;
        //please note the len is s.length() + 1
        int r = s.length(), c = p.length();
        boolean[][] dp = new boolean[r + 1][c + 1];
        //initialize as true 
        dp[0][0] = true;
        //why we initialize p first string first? because our calclation is row by row, so 
        //we initialize first row first
        
        //also this is for detecting the s = "" case, we also need initilaize dp[i][0] for p = "" case
        for (int i = 0; i < c; i++) {
            // we always use i+ 1 = i since our end is len + 1
            if (p.charAt(i) == '*' && dp[0][i]) {
                dp[0][i + 1] = true;
            }
        }
        //we also should initialize the dp[r][0] but since if p is not "" then s should dp[i][0] default to false
        //but i will write whole templates as below: 
        for (int i = 0; i < r; i++) {
                dp[i+1][0] = false;
        }
        //we loop two strings, row by row,  our len is 0-N, so our initialization 
        for(int i = 0; i< r; i++){
            for(int j = 0; j < c; j++) {
                //case 1 and 2
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '?') {
                    dp[i+1][j+1] = dp[i][j];
                }
                // the same as common longest sequence because * here can not remove previous char but to any sequence chars
                //previous problem which it could remove previous char so it has to be with dp[i+1][j-2]
                //so we 
                if (p.charAt(j) == '*') {
                        dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1];
                }
            }
        }
        return dp[r][c];
    }

}
