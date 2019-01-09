package hatecode;
import java.util.*;
public class ExpressiveWords {
/*
809. Expressive Words
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  Here, we have groups, of adjacent letters that are all the same character, and adjacent characters to the group are different.  A group is extended if that group is length 3 or more, so "e" and "o" would be extended in the first example, and "i" would be extended in the second example.  As another example, the groups of "abbcccaaaa" would be "a", "bb", "ccc", and "aaaa"; and "ccc" and "aaaa" are the extended groups of that string.

For some given string S, a query word is stretchy if it can be made to be equal to S by extending some groups.  Formally, we are allowed to repeatedly choose a group (as defined above) of characters c, and add some number of the same character c to it so that the length of the group is 3 or more.  Note that we cannot extend a group of size one like "h" to a group of size two like "hh" - all extensions must leave the group extended - ie., at least 3 characters long.

Given a list of query words, return the number of words that are stretchy. 

Example:
Input: 
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation: 
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not extended.
*/
    
    class StretchPattern {
        char ch;
        int count;
        public StretchPattern(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
        
        public boolean equals(StretchPattern other) {
            if (other == null) return false;
            if (ch == other.ch && (count == other.count || count >=3)) {
                return true;
            }
            return false;
        }
        public String toString() {
            return "ch=" + ch +";" + "count=" + count;
        }
    }
    public List<StretchPattern> covertStr2Ptn(String s) {
        List<StretchPattern> res = new ArrayList<>();
        for(int i = 0; i< s.length(); i++) {
            int j = i;
            while(i + 1 < s.length() && s.charAt(i + 1)==s.charAt(i)) i++;
            res.add(new StretchPattern(s.charAt(j), i - j +1));
        }
        return res;
    }
    //O(QK)/O(K), K is max(word), Q is words.length
    public int expressiveWords2(String S, String[] words) {
        if (words == null || words.length < 1) return 0;
        int res = 0;
        if (S == null || S.length() < 1) {
            for(String word : words) {
                if (word == null && S == null 
                    || word != null && S != null && word.equals(S)) {
                    res ++;
                }
            }
        }
        List<StretchPattern> src = covertStr2Ptn(S);
        for(String word : words) {
            if (word == null || S.length() < word.length()) continue;
            List<StretchPattern> dst = covertStr2Ptn(word);
            if (src.size() != dst.size()) continue;
            int i = 0;
            for(;i< src.size();i++) {
                if (!src.get(i).equals(dst.get(i))) {
                    break;
                }
            }
            if (i == src.size()) res++;
        }
        return res;
    }
    
    
    public int expressiveWords(String S, String[] words) {
        int res = 0;
        for (String W : words) if (check(S, W)) res++;
        return res;
    }
    //two pointers
    public boolean check2(String S, String W) {
        int n = S.length(), m = W.length(), j = 0;
        for (int i = 0; i < n; i++)
            if (j < m && S.charAt(i) == W.charAt(j)) j++;
            else if (i > 1 && S.charAt(i) == S.charAt(i - 1) && S.charAt(i - 1) == S.charAt(i - 2));
            else if (0 < i && i < n - 1 && S.charAt(i - 1) == S.charAt(i) && S.charAt(i) == S.charAt(i + 1));
            else return false;
        return j == m;
    }
    //4 pointers
     public boolean check(String S, String W) {
        int n = S.length(), m = W.length(), i = 0, j = 0;
        for (int i2 = 0, j2 = 0; i < n && j < m; i = i2, j = j2) {
            if (S.charAt(i) != W.charAt(j)) return false;
            while (i2 < n && S.charAt(i2) == S.charAt(i)) i2++;
            while (j2 < m && W.charAt(j2) == W.charAt(j)) j2++;
            if (i2 - i != j2 - j && i2 - i < Math.max(3, j2 - j)) return false;
        }
        return i == n && j == m;
    }
}