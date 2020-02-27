package hatecode._0001_0999;
import java.util.*;
public class _676ImplementMagicDictionary {
/*
676. Implement Magic Dictionary
Implement a magic directory with buildDict, and search methods.

For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.

For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is in the dictionary you just built.

Example 1:
Input: buildDict(["hello", "leetcode"]), Output: Null
Input: search("hello"), Output: False
Input: search("hhllo"), Output: True
Input: search("hell"), Output: False
Input: search("leetcoded"), Output: False
*/
    
    class Trie{
        Map<Character, Trie> map;
        boolean isWord;
        
        public Trie() {
            map = new HashMap<>();
        }
    }

    /** Initialize your data structure here. */
    Trie root;
    public _676ImplementMagicDictionary() {
        root = new Trie();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for(String w: dict) {
            Trie node = root;
            for(char ch : w.toCharArray()) {
                node.map.computeIfAbsent(ch, v->new Trie());
                node = node.map.get(ch);
            }
            node.isWord = true;
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String w) {
        Trie node = root;
        char[] chs = w.toCharArray();
        for(int i = 0;i < chs.length; i++) {
            char ch = chs[i];
            for(char c ='a'; c<='z'; c++) {
                if (c == ch) continue;
                char org = ch;
                chs[i] = c;
                if (helper(new String(chs), node)) return true;
                chs[i] = org;
            }
        }
        //return node.isWord; 
        //we should return false because we have to change one character
        return false;
    }
    
    private boolean helper(String s, Trie node) {
        for(int i = 0; i< s.length(); i++) {
            char ch = s.charAt(i);
            if (node.map.containsKey(ch)) {
                node = node.map.get(ch);
            } else return false;
        }
        return node.isWord;
    }
    
    
    
    //this is solution without 26*
    Map<String, List<int[]>> map = new HashMap<>();
   
    //will build a map like following
    //"hello" -> {"ello":[[0, 'h']], "hllo":[[1, 'e']], "helo":[[2, 'l'],[3, 'l']], "hell":[[4, 'o']]}
    /** Build a dictionary through a list of words */
    public void buildDict_Best(String[] dict) {
        for (String s : dict) {
            for (int i = 0; i < s.length(); i++) {
                String key = s.substring(0, i) + s.substring(i + 1);
                int[] pair = new int[] {i, s.charAt(i)};
                
                List<int[]> val = map.getOrDefault(key, new ArrayList<int[]>());
                val.add(pair);
                
                map.put(key, val);
            }
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search_Best(String word) {
        for (int i = 0; i < word.length(); i++) {
            String key = word.substring(0, i) + word.substring(i + 1);
            if (map.containsKey(key)) {
                for (int[] pair : map.get(key)) {
                    if (pair[0] == i && pair[1] != word.charAt(i)) return true;
                }
            }
        }
        return false;
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */