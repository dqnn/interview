package hatecode._0001_0999;
import java.util.*;
public class _791CustomSortString {
/*
791. Custom Sort String
S and T are strings composed of lowercase letters. 
In S, no letter occurs more than once.

S was sorted in some custom order previously. 
We want to permute the characters of 
T so that they match the order that S was sorted. 
More specifically, if x occurs 
before y in S, then x should occur before y in the 
returned string.

Return any permutation of T (as a string) that satisfies 
this property.

Example :
Input: 
S = "cba"
T = "abcd"
Output: "cbad"
 */
    //interview friendly, O(n)/O(1), since they are lower case letters only
    //thinking process, bucket sort
    public String customSortString2(String S, String T) {
        if (S == null || T == null) return "";
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i< S.length();i++) map.put(S.charAt(i), i);
        
        Map<Character, Integer> map2 = new HashMap<>();
        for(int i = 0; i< T.length();i++) {
            map2.put(T.charAt(i), map2.getOrDefault(T.charAt(i), 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b)->(map.getOrDefault(a.getKey(), T.length()) - map.getOrDefault(b.getKey(), T.length())));
        pq.addAll(map2.entrySet());
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            for(int i = 0; i< entry.getValue();i++) sb.append(entry.getKey());
        }
        
        return sb.toString();
    }
    
    public String customSortString(String S, String T) {
        int[] bucket = new int[26];
        for (char c : T.toCharArray()) {
            bucket[c - 'a']++;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : S.toCharArray()) {
            for (int i = 0; i < bucket[c - 'a']; i++) {
                sb.append(c);
            }
            //mark bucket is 0
            bucket[c - 'a'] = 0;
        }
        //the left, which is not in S, their order is not considered
        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                sb.append((char) (i + 'a'));
            }
        }
        
        return sb.toString();
    }
}