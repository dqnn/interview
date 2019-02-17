package hatecode;
import java.util.*;
public class MostCommonWord {
/*
819. Most Common Word
Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.

 

Example:

Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
*/
    //simple solution but need to understand the regex and stream
    public String mostCommonWord(String p, String[] banned) {
        Set<String> ban = new HashSet<>(Arrays.asList(banned));
        Map<String, Integer> count = new HashMap<>();
        String[] words = p.replaceAll("\\W+" , " ").toLowerCase().split("\\s+");
        for (String w : words) if (!ban.contains(w)) count.put(w, count.getOrDefault(w, 0) + 1);
        //collections max and Map entry method
        return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}