package hatecode._0001_0999;
import java.util.*;
import java.util.stream.*;
public class _890FindAndReplacePattern {
/*
890. Find and Replace Pattern

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
*/
    //this is to find the pattern for each string, we use a map to store how many unique chars now in 
    //the string, this will be the character value of the string
    //refer to LC 205, Isomorphic strings
    
    //they key of the problem is to find how many unique characters for same length
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int[] p = getPattern(pattern);
        return Arrays.stream(words).filter(e->Arrays.equals(getPattern(e), p)).collect(Collectors.toList());
    }
    
    public int[] getPattern(String in) {
        Map<Character, Integer> map = new HashMap<>();
        int n = in.length();
        //distinct char count from 0->i, for "abb"->[0,1,1] become the signature
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(in.charAt(i), map.size());
            res[i] = map.get(in.charAt(i));
        }
        return res;
    }
    //another solution, so we compare each character, 
    //this cleverly use i as position index or use it as how many unique characters in the strings so far
    //so first p and s are all 0, then if they are the same, maybe 0 or same character, but if not the same
    //definitely will be no match
    public List<String> findAndReplacePattern_compare(String[] words, String pattern) {
        List<String> res= new LinkedList<>();
        for (String w: words){
            int[] p= new int[26], s= new int[26];
            boolean same=true;
            for (int i=0; i<w.length(); i++){
                if (s[w.charAt(i)-'a']!=p[pattern.charAt(i)-'a']){
                    same=false;
                    break;
                }else{
                    s[w.charAt(i)-'a']=p[pattern.charAt(i)-'a']=i+1;
                }
            }
            if (same) res.add(w);
        }
        return res;
    }
}