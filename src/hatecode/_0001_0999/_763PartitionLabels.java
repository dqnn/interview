package hatecode._0001_0999;
import java.util.*;
public class _763PartitionLabels {
/*
763. Partition Labels
A string S of lowercase letters is given. We want to partition this string into 
as many parts as possible so that each letter appears in at most one part, 
and return a list of integers representing the size of these parts.

Example 1:
Input: S = "ababcbacadefegdehijhklij"
Output: [9,7,8]
*/
    // thinking process: O()
    //so the problem is to say given a string s, we want to partition the string into several 
    //substring so each substring can have all chars which would not disappear in other partitions
    //and as many as possible
    
    //so we use sliding window, and we remember each char last character, everytime for each char
    //we can get the char last position, but thats not ideal, 
    //so we need to find the char disappear last in the string and as end char. 
    public List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() < 1) return res;
        
        int[] last = new int[26];
        for(int i = 0; i< s.length(); i++) last[s.charAt(i) - 'a'] = i;
        
        int left = 0, prev = 0;
        for(int i = 0; i< s.length(); i++) {
            left = Math.max(left, last[s.charAt(i) - 'a']);
            if (left == i) {
                res.add(i - prev + 1);
                prev = left + 1;
            }
        }
        return res;
    }
}