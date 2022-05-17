package hatecode._0001_0999;

import java.util.*;
/**
 * Project Name : Leetcode 
 * Package Name : leetcode 
 * File Name : AddandSearchWord
 * Date : Sep, 2018 
 * Description : TODO
 */
public class _211AddandSearchWord {
    /**
     * 211. Add and Search Word - Data structure design
     * Design a data structure that supports the following two operations:

     void addWord(word)
     bool search(word)
     search(word) can search a literal word or a regular expression string 
     containing only letters a-z or .. A . means it can represent any one letter.

     For example:

     addWord("bad")
     addWord("dad")
     addWord("mad")
     search("pad") -> false
     search("bad") -> true
     search(".ad") -> true
     search("b..") -> true

     */
    /** Initialize your data structure here. */
    //this is better DS,because 
    //we did not waste any mem as use a map instead char[26]
    private class Trie{
        char c;
        Map<Character, Trie> map;
        boolean isWord;
        String word;
        public Trie() {
            this.map = new HashMap<>();
            this.isWord = false;
            this.word= "";
        }
    }
    
    Trie root;
    public _211AddandSearchWord() {
        this.root = new Trie();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if(word == null || word.length() < 1) return;
        
        Trie cur = root;
        for(char ch: word.toCharArray()) {
            if(!cur.map.containsKey(ch)) {
                cur.map.put(ch, new Trie());
            }
            cur = cur.map.get(ch);
        }
        cur.isWord = true;
        cur.word = word;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    //better
    public boolean search(String word) {
        if(word == null || word.length() < 1) return false;
        
        return helper(word, root, 0);
    }
    
    private boolean helper(String w, Trie root, int pos) {
        if(pos == w.length()) return root.isWord;
        
        char cur = w.charAt(pos);
        if(cur == '.') {
            for(Trie node:  root.map.values()) {
                if(node == null) continue;
                if (helper(w, node, pos+1)) return true;
            }
            return false;
        } else {
            if(root.map.get(cur) == null) return false;
            else return helper(w, root.map.get(cur), pos+1);
        }
    }
}
