package hatecode;

import java.util.*;
public class _1087BraceExpansion {
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
    
    //thinking process:
    //given a string s, we want to use multiple multiple factors, like 
    //(a+b)*c = ac + bc, so here is the same, output all factors as above example
    
    //
    public String[] expand(String s) {
        TreeSet<String> set = new TreeSet<>();
        helper(s, 0, "", set);
        return set.toArray(new String[set.size()]);
    }
    
    private void helper(String s, int pos, String res, TreeSet<String> set) {
        if (pos == s.length()) {
            set.add(res);
            return;
        }
        if (s.charAt(pos) == '{') {
            int index = pos + 1;

            while (index < s.length() && s.charAt(index) != '}') index++;

            String[] options = s.substring(pos + 1, index).split(",");

            for (String option : options)
                helper(s, index + 1, res + option, set);
        } else helper(s, pos + 1, res + s.charAt(pos), set);
    }
    
    //recursive solution
    public String[] expand_Recursive(String S) {
        // TreeSet to sort
        TreeSet<String> set = new TreeSet<>();
        if (S.length() == 0) {
            return new String[]{""}; 
        } else if (S.length() == 1) {
            return new String[]{S};
        }
        if (S.charAt(0) == '{') {
            int i = 0; // keep track of content in the "{content}"
            while (S.charAt(i) != '}') {
                i++;
            }
            String sub = S.substring(1, i);
            String[] subs = sub.split(",");
            String[] strs = expand(S.substring(i + 1)); // dfs
            for (int j = 0; j < subs.length; j++) {
                for (String str : strs) {
                    set.add(subs[j] + str);
                }
            }
        } else {
            String[] strs = expand(S.substring(1));
            for (String str : strs) {
                set.add(S.charAt(0) + str);
            }
        }
        return set.toArray(new String[0]);
    }
}