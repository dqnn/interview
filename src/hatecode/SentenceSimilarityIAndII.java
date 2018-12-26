package hatecode;
import java.util.*;
public class SentenceSimilarityIAndII {
/*
734. Sentence Similarity I and II
Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.

For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].

Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and "good" are similar, "great" and "good" are not necessarily similar.

However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.

Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified similar word pairs.

Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].


*/
    public static boolean areSentencesSimilarI(String[] word1, String[] word2, String[][] pairs) {
        if (word1 == null && word2 == null) return true;
        if (word1 == null || word2 == null || word1.length != word2.length) return false;
        Map<String, Set<String>> map = new HashMap<>();
        for(String[] pair : pairs) {
            map.computeIfAbsent(pair[0], V->new HashSet<>()).add(pair[1]);
            map.computeIfAbsent(pair[1], V->new HashSet<>()).add(pair[0]);
        }
        
        for(int i = 0; i<word1.length;i++) {
            if (word1[i].equals(word2[i])) continue;
            if (!map.containsKey(word1[i]) || !map.containsKey(word2[i])) return false;
            if (!map.get(word1[i]).contains(word2[i]) || !map.get(word2[i]).contains(word1[i])) return false;
        }
        return true;
    }
  //II is transitive, others are the same
  //if if transitive, so it would be a graph, for each word, we need to find whether these words
    //are connected, if yes, then return yes
    //O(N * M) N is words1 length, M is pairs length
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) {
            return false;
        }
        
        Map<String, Set<String>> graph = new HashMap<>();
        for(String[] pair : pairs) {
            graph.computeIfAbsent(pair[0], V->new HashSet<>()).add(pair[1]);
            graph.computeIfAbsent(pair[1], V->new HashSet<>()).add(pair[0]);
        }
        
        for (int i = 0; i < words1.length; i++) {
            //this has to be here because some words may not in paris but they
            //are the same
            if (words1[i].equals(words2[i])) continue;           
            if (!dfs(graph, words1[i], words2[i], new HashSet<>())) return false;
        }
        
        return true;
    }
    
    private boolean dfs(Map<String, Set<String>> graph, String source, String target, Set<String> visited) {
        //if there was no similiar words, then false
        if (graph.get(source) == null) return false;
        if (graph.get(source).contains(target)) return true;
        
        //graph bsf visits
        for (String next : graph.get(source)) {
            if (visited.contains(next)) continue;
            visited.add(next);
            if (dfs(graph, next, target, visited))  return true;
        }
        return false;
    }
}