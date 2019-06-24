package hatecode;

import java.util.*;
class BraceExpansion {
/*
1087. Brace Expansion
A string S represents a list of words.

Each letter in the word has 1 or more options.  If there is one option, the letter is represented as is.  If there is more than one option, then curly braces delimit the options.  For example, "{a,b,c}" represents options ["a", "b", "c"].

For example, "{a,b,c}d{e,f}" represents the list ["ade", "adf", "bde", "bdf", "cde", "cdf"].

Return all words that can be formed in this manner, in lexicographical order.

 

Example 1:

Input: "{a,b}c{d,e}f"
Output: ["acdf","acef","bcdf","bcef"]
*/
    public String[] expand(String S) {
        TreeSet<String> set=new TreeSet<>();
        backtracking(S,0,"",set);
        return set.toArray(new String[set.size()]);
    }
    
    private void backtracking(String s,int pos,String cur,TreeSet<String> res) {
        if(pos==s.length()) {
            res.add(cur);
            return;
        }
        if(s.charAt(pos)=='{') {
            int index=pos+1;
            
            while(index < s.length() && s.charAt(index) != '}') index++;
            
            String[] options=s.substring(pos+1,index).split(",");
            for(String option:options) backtracking(s,index+1,cur+option,res);
        } else {
            backtracking(s,pos+1,cur+s.charAt(pos),res);
        }
    }
}