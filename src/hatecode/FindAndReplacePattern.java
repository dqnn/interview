package hatecode;
import java.util.*;
import java.util.stream.*;
class FindAndReplacePattern {
/*
890. Find and Replace Pattern

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.
*/
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
}