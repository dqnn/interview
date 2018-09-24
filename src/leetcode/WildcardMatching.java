package leetcode;

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
    public boolean isMatch(String s, String p) {
        //sp means string s pointer
        int sp = 0;
        //pp means  string p pointer
        int pp = 0;
        // match position in s
        int match = 0;
        // means the position of "*"
        int star = -1;
        while (sp < s.length()) {
            //1 the chars are the same, 
            //2 p has ? so we can continue
            if (pp < p.length() && (s.charAt(sp) == p.charAt(pp) || p.charAt(pp) == '?')) {
                sp++;
                pp++;
            //
            } else if (pp < p.length() && p.charAt(pp) == '*') {
                star = pp;
                match = sp;
                pp++;
            // so here stat can help to continue
            } else if (star != -1) {
                pp = star + 1;
                match++;
                sp = match;
            } else return false;
        }
        // this means pp still stay at * position
        while (pp < p.length() && p.charAt(pp) == '*') {
            pp++;
        }
        return pp == p.length();
    }
}
