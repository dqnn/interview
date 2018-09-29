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
                pp++;
            // so here stat can help to continue until to end of s
            } else if (star != -1) {
                //pp will stop next postion next to "*"
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
}
