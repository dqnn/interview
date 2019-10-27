package hatecode;

import java.util.*;
public class _1065IndexPairsOfAString {
/*
1065. Index Pairs of a String
Given a text string and words (a list of strings), return all index pairs [i, j] so that the substring text[i]...text[j] is in the list of words.

 

Example 1:

Input: text = "thestoryofleetcodeandme", words = ["story","fleet","leetcode"]
Output: [[3,7],[9,13],[10,17]]
*/
    
    //thinking process: O(w*l + t*l), 
    //w = words.length, l =avg(words), t =text.length()
    
    //so the problem is to say given a string text and a dictionary,
    //we want to find [start, end] indexes which are substring in words
    
    //
    public int[][] indexPairs(String text, String[] words) {
        Trie t = new Trie();
        for(String w: words) t.addWords(w);
        return t.findIndexes(text);
    }
    
    class Trie {
        private class Pair {
            TrieNode n;
            int index;
            Pair(TrieNode n, int i) {
                this.n = n;
                index = i;
            }
        }
        
        private class TrieNode {
            TrieNode[] children;
            boolean isLeaf;
            TrieNode() {
                children = new TrieNode[26];
                isLeaf = false;
            }
            
            boolean containsChild(char c) {
                return (children[c - 'a'] != null);
            }
        }
        
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        
        private void addWords(char[] ch, int idx, TrieNode parent) {
            if(parent.children[ch[idx] - 'a'] == null) {
                parent.children[ch[idx] - 'a'] = new TrieNode();
            }
            if(idx == ch.length - 1) {
                parent.children[ch[idx] - 'a'].isLeaf = true;
            }
            else {
                addWords(ch, idx+1, parent.children[ch[idx] - 'a']);
            }
        }
        
        public void addWords(String w) {
            addWords(w.toCharArray(), 0, root);
        }
        
        public int[][] findIndexes(String text) {
            char[] ch = text.toCharArray();
            List<int[]> l = new ArrayList<>();
            
            for(int i = 0; i < ch.length; ++i) {
                if(root.containsChild(ch[i])) {
                    TrieNode cur = root;
                    for(int j = i; j < ch.length && cur != null; ++j) {
                        cur = cur.children[ch[j] - 'a'];
                        if(cur != null && cur.isLeaf) l.add(new int[]{i, j});
                    }
                }
            }
                    
            return l.toArray(new int[l.size()][]);
        }
    }
}

