package hatecode;

import java.util.*;
public class StreamOfCharacters {
/*
1032. Stream of Characters
Implement the StreamChecker class as follows:

StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to newest, including this letter just queried) spell one of the words in the given list.
 

Example:

StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist

*/
    
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord;
        public TrieNode(){};
    }
    
    TrieNode root;
    StringBuilder sb;
    private void addWord(String word) {
        TrieNode node = root;
        for(int i = word.length() - 1; i>=0; i--) {
            char ch = word.charAt(i);
            TrieNode temp = node.children.get(ch);
            if (null == temp) {
                temp = new TrieNode();
                node.children.put(ch, temp);
            }
            node = temp;
        }
        node.isWord = true;
    }
    public StreamOfCharacters(String[] words) {
        sb = new StringBuilder();
        root = new TrieNode();
        for(String str: words) {
            addWord(str);
        }
        System.out.println(root.children);
    }
    
   
    
    public boolean query(char letter) {
        sb.append(letter);
        TrieNode node = root;
        String str = sb.toString();
        for(int i = str.length() - 1; i>=0;i--) {
            char ch = str.charAt(i);
            node = node.children.get(ch);
            if (node == null) return false;
            
            if (node.isWord) return true;
        }
        return false;
    }
}

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */