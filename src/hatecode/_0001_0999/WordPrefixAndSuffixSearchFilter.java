package hatecode._0001_0999;

import java.util.*;
public class WordPrefixAndSuffixSearchFilter {
/*
745. Prefix and Suffix Search
Given many words, words[i] has weight i.

Design a class WordFilter that supports one function, WordFilter.f(String prefix, String suffix). It will return the word with given prefix and suffix with maximum weight. If no word exists, return -1.

Examples:

Input:
WordFilter(["apple"])
WordFilter.f("a", "e") // returns 0
WordFilter.f("b", "") // returns -1
*/
    Trie root;
    class Trie{
        Map<Character, Trie> map;
        int w;
        
        public Trie() {
            this.map = new HashMap<>();
        }
        
        
    }
    
    public void add(String word, int w) {
            Trie node = root;
            for(int i = 0; i< word.length(); i++) {
                char ch = word.charAt(i);
                node.map.computeIfAbsent(ch, v->new Trie());
                node = node.map.get(ch);
                node.w = w;
            }
    }
    
    public int startsWith(String prefix) {
            Trie cur = root;
            for (char c : prefix.toCharArray()) {
                Trie next = cur.map.get(c);
                if (next == null) {
                    return -1;
                }
                cur = next;
            }
            return cur.w;
        }
    
    
    public WordPrefixAndSuffixSearchFilter(String[] words) {
       root = new Trie();
        int idx = 0;
       for(String word : words) {
           //override the weight 
           for(int i = 0; i<= word.length(); i++) {
               add(word.substring(i, word.length()) + "{" + word, idx);
           }
           idx++;
       }
    }
    
    public int f(String prefix, String suffix) {
        return startsWith(suffix + "{" + prefix);
    }
    
    
    
    
    
    //simple solution
    String[] in;
    public void WordFilter_map(String[] words) {
        in = words;
    }
    
    public int f_map(String prefix, String suffix) {
        for(int i = in.length - 1; i>=0; i--) {
            if (in[i].startsWith(prefix) && in[i].endsWith(suffix)) return i;
        }
        
        return -1;
    }
}

/**
 * Your WordFilter object will be instantiated and called as such:
 * WordFilter obj = new WordFilter(words);
 * int param_1 = obj.f(prefix,suffix);
 */