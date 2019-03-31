package hatecode;

import java.util.*;
public class WordSquares {
/*
425. Word Squares
Example 1:

Input:
["area","lead","wall","lady","ball"]

Output:
[
  [ "wall",
    "area",
    "lead",
    "lady"
  ],
  [ "ball",
    "area",
    "lead",
    "lady"
  ]
]

Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
*/
    class TrieNode {
        List<String> startWith;
        Map<Character, TrieNode> children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new HashMap<>();
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();
            for (String w : words) {
                addWord(w);
            }
        }
        
        void addWord(String word) {
            TrieNode node = root;
            for(int i =0 ;i < word.length(); i++) {
                char c = word.charAt(i);
                node.children.computeIfAbsent(c, v->new TrieNode());
                node = node.children.get(c);
                node.startWith.add(word);
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                if (!cur.children.containsKey(ch))
                    return ans;

                cur = cur.children.get(ch);
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    //interview friendly:
    //give a list words,find all possible combinations if they can form a word square
    
    //brute force is O(n! * L^2), L is max length of strings, so we can have a method to 
    //validate all combinations, this is L^2. then we can have n! method to permutation all 
    //possible combinations.
    
    //the improvements here is we combine, Trie and backtracking. 
    //from the problem, we can see there is actually a prefix search, so we can leverage 
    //this function so with backtrcking we can emurate all possible combinations. 
    //O(ln + nc^l), l = word.length(), n = words.size(), c is average count of elements 
    //in map
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words == null || words.length == 0)
            return res;
        int len = words[0].length();
        Trie trie = new Trie(words);
        List<String> resBuilder = new ArrayList<>();
        for (String w : words) {
            resBuilder.add(w);
            search(len, trie, res, resBuilder);
            resBuilder.remove(resBuilder.size() - 1);
        }

        return res;
    }
    //O(c ^ l)/O(n)
    private void search(int len, Trie tr, List<List<String>> ans,
            List<String> resBuilder) {
        if (resBuilder.size() == len) {
            ans.add(new ArrayList<>(resBuilder));
            return;
        }

        int idx = resBuilder.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : resBuilder)
            prefixBuilder.append(s.charAt(idx));
        List<String> startWith = tr.findByPrefix(prefixBuilder.toString());
        for (String sw : startWith) {
            resBuilder.add(sw);
            search(len, tr, ans, resBuilder);
            resBuilder.remove(resBuilder.size() - 1);
        }
    }
}