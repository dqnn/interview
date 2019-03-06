package hatecode;
import java.util.*;
public class ConcatenatedWords {
/*
472. Concatenated Words
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

Example:
Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]

Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]

Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats"; 
 "dogcatsdog" can be concatenated by "dog", "cats" and "dog"; 
"ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
*/
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> res = new ArrayList<>();
        Arrays.sort(words, (a, b)->(a.length() - b.length()));
        
        Set<String> set = new HashSet<>();
        for(String str : words) {
            //helper2 is DFS and helper is DP, dfs is faster
            if (helper2(str, set)) {
                res.add(str);
            }
            set.add(str);
        }
        
        return res;
    }
    
    public boolean helper(String s, Set<String> set) {
        if (set.isEmpty()) return false;
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int i = 1; i<=n; i++) {
            for(int j = 0; j < i; j++) {
                String sub = s.substring(j,i);
                if (dp[j] && set.contains(sub)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[n];
    }
    
    private boolean helper2(String s, Set<String> set){
        for(int i=1;i<s.length();i++){
            if(set.contains(s.substring(0,i))){
                String rightStr=s.substring(i);
                if(set.contains(rightStr) || helper2(rightStr, set)){
                    return true;
                }
            }
        }
        return false;
    }
    
    //this is Trie version, Trie class needs to be re-written
    public List<String> findAllConcatenatedWordsInADict_Trie(String[] words) {
        Trie trie = new Trie();
        List<String> list = new ArrayList<>();
        for (String word : words) {
            trie.insert(word);
        }
        for (String word : words) {
            if (madeOfWords(word, trie.getRoot(), 0, 0)) {
                list.add(word);
            }
        }
        return list;
    }

    private boolean madeOfWords(String word, Trie.TrieNode root, int charIndex, int count) {
        Trie.TrieNode current = root;
        for (int i = charIndex; i < word.length(); i++) {
            char c = word.charAt(i);
            current = current.get(c);
            if (current == null) {
                return false;
            }
            if (current.isEnd()) {
                if (i == word.length()-1) {
                    return count >= 1;
                }
                if (madeOfWords(word, root, i + 1, count + 1)) {
                    return true;
                }
            }
        }
        return false;
    }
    public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.setEnd();
    }

    public TrieNode searchPrefix(String word, TrieNode search) {
        for (int i = 0; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (search.containsKey(curLetter)) {
                search = search.get(curLetter);
            } else {
                return null;
            }
        }
        return search;
    }

    // Returns if the word is in the trie.
    public boolean search(String word, TrieNode search) {
        TrieNode result = searchPrefix(word, search);
        return result != null && result.isEnd();
    }

    public boolean search(String word) {
        return search(word, root);
    }

    public TrieNode searchNode(String word, TrieNode trieNode) {
        TrieNode result = searchPrefix(word, trieNode);
        if (result != null && result.isEnd()) {
            return result;
        }
        return null;
    }

    public boolean startsWith(String prefix, TrieNode search) {
        TrieNode node = searchPrefix(prefix, search);
        return node != null;
    }

    public boolean startsWith(String prefix) {
        return startsWith(prefix, root);
    }

    public TrieNode getRoot() {
        return root;
    }

   class TrieNode {
        public int count = 0;
        // R links to node children
        private TrieNode[] links;

        private final int R = 26;

        private boolean isEnd;

        public TrieNode() {
            links = new TrieNode[R];
        }

        public boolean containsKey(char ch) {
            return links[ch -'a'] != null;
        }
        public TrieNode get(char ch) {
            return links[ch -'a'];
        }
        public void put(char ch, TrieNode node) {
            links[ch -'a'] = node;
            count++;
        }
        public void setEnd() {
            isEnd = true;
        }
        public boolean isEnd() {
            return isEnd;
        }
    }
    }
    
    
    
}