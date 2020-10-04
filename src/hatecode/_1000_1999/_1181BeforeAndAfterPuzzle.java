package hatecode._1000_1999;

import java.util.*;
import java.util.stream.IntStream;
public class _1181BeforeAndAfterPuzzle {
/*
1181. Before and After Puzzle
Given a list of phrases, generate a list of Before and After puzzles.

A phrase is a string that consists of lowercase English letters and spaces only. 
No space appears in the start or the end of a phrase. There are no consecutive spaces in a phrase.

Before and After puzzles are phrases that are formed by merging two phrases where 
the last word of the first phrase is the same as the first word of the second phrase.

Return the Before and After puzzles that can be formed by every two phrases 
phrases[i] and phrases[j] where i != j. Note that the order of matching two phrases 
matters, we want to consider both orders.

You should return a list of distinct strings sorted lexicographically.

 

Example 1:

Input: phrases = ["writing code","code rocks"]
Output: ["writing code rocks"]
*/
    // thinking process: O(n)/O(n)
    public List<String> beforeAndAfterPuzzles(String[] phrases) {
        int n = phrases.length;
        Map<String, List<Integer>> map = new HashMap<>();
        
        // for the head word of each string in phrases as the key, the value is the index of this string
        //this is the 3 for loop 
        //for(int i = 0; i < n; ++i) {
          //  String head = head(phrases[i]);
            //map.computeIfAbsent(head, v->new ArrayList<>()).add(i);
        //}
        
        IntStream.range(0, phrases.length).forEach(i->map.computeIfAbsent(head(phrases[i]), v->new ArrayList<>()).add(i));
        Set<String> result = new HashSet<>();
        for(int i = 0; i < n; ++i) {
            String tail = tail(phrases[i]);
            if (map.containsKey(tail)) {
                for (Integer j : map.get(tail)) {
                    if (i != j) {
                        // remove the dup and concat
                        result.add(phrases[i] + phrases[j].substring(tail.length()));
                    }
                }
            }
        }
        List<String> list = new ArrayList<>(result);
        Collections.sort(list);
        return list;
    }
    
    static String head(String phrase) {
        String[] array = phrase.split(" ");
        return array[0];
    }
    
    static String tail(String phrase) {
        String[] array = phrase.split(" ");
        return array[array.length - 1];
    }
    
    //use treeset
    public List<String> beforeAndAfterPuzzles_shorter(String[] ph) {
        Map<String, Set<String>> head = new HashMap<>(), tail = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();
        for (String p : ph) {
            String[] sa = p.split(" ");
            head.computeIfAbsent(sa[0], s -> new HashSet<>()).add(p);
            tail.computeIfAbsent(sa[sa.length - 1], s -> new HashSet<>()).add(p);
            count.put(p, 1 + count.getOrDefault(p, 0));
        }
        TreeSet<String> ans = new TreeSet<>();
        for (String end : tail.keySet())
            for (String p : head.getOrDefault(end, Collections.emptySet()))
                for (String t : tail.get(end))
                    if (!t.equals(p) || count.get(p) > 1)
                        ans.add(t + p.substring(end.length()));
        return new ArrayList<>(ans);
    }
}