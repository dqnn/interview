package hatecode._0001_0999;
import java.util.*;
public class _524LongestWordInDictionaryThroughDeleting {
/*
524. Longest Word in Dictionary through Deleting
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given string. If there are more than one possible results, return the longest word with the smallest lexicographical order. If there is no possible result, return the empty string.

Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]

Output: 
"apple"

*/
    public String findLongestWord2(String s, List<String> d) {
        if (s == null || s.length() < 1 || d == null || d.size() < 1) return "";
        PriorityQueue<String> pq = 
            new PriorityQueue<>((a,b)->(a.length() == b.length() ? a.compareTo(b) : b.length() - a.length()));
        pq.addAll(d);
        while(!pq.isEmpty()) {
            String str = pq.poll();
            int i = 0, j= 0, count = 0;
            for(; i < str.length(); i++) {
                //if (count == str.length()) break;
                while(j < s.length() && count < str.length()) {
                    if (s.charAt(j++) == str.charAt(i)) {
                        count++;
                        break;
                    }
                }
            }
            if (count == str.length()) return str;
        }
        return "";
    }

    public String findLongestWord(String s, List<String> d) {
        String longest = "";
        for (String dictWord : d) {
            int i = 0;
            for (char c : s.toCharArray())
                if (i < dictWord.length() && c == dictWord.charAt(i))
                    i++;

            if (i == dictWord.length() && dictWord.length() >= longest.length())
                if (dictWord.length() > longest.length() || dictWord.compareTo(longest) < 0)
                    longest = dictWord;
        }
        return longest;
    }
}