package _0001_0999;

import java.util.*;


public class _208ImplementTrie{

    /*
    208. Implement Trie (Prefix Tree)
    A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 

Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]
    */
    
    class TrieNode {
        char c;
        Map<Character, TrieNode> children;
        boolean isWord;
        String s;
        
        public TrieNode(char c, boolean isW) {
            this.c = c;
            this.isWord = isW;
            children = new HashMap<>();
        }
    }

    TrieNode root = null;
    public _208ImplementTrie() {
        root = new TrieNode('1', false);
    }
    
    public void insert(String word) {
        TrieNode cur = root;
        
        for(char c: word.toCharArray()) {
            cur.children.computeIfAbsent(c, v->new TrieNode(c, false));
            cur = cur.children.get(c);
        }
        
        cur.isWord = true;
        cur.s = word;
    }
    
    public boolean search(String w) {
        TrieNode cur = root;
        for(char c: w.toCharArray()) {
            if(!cur.children.containsKey(c)) return false;
            cur = cur.children.get(c);
        }
        
        return cur.isWord;
    }
    
    //add a little more favor that find out all candiates and return 
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        
        for(char c: prefix.toCharArray()) {
            if(!cur.children.containsKey(c)) return false;
            cur = cur.children.get(c);
        }
        
        List<String> res = new ArrayList<>();
        helper(cur, res);
        
        System.out.println(res);
        
        return true;
    }
    
    private void helper(TrieNode root, List<String> res) {
        for(char c: root.children.keySet()) {
            TrieNode node = root.children.get(c);
            if(node.isWord) {
                res.add(node.s);
            }
            helper(node, res);
        }
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */