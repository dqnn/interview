package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : 212. Word Search II
 */
public class _212WordSearchII {
    /**
     * Given a 2D board and a list of words from the dictionary, find all words in the board.

     Each word must be constructed from letters of sequentially adjacent cell, 
     where "adjacent" cells are those horizontally or vertically neighboring. 
     The same letter cell may not be used more than once in a word.

     For example,
     Given words = ["oath","pea","eat","rain"] and board =

     [
     ['o','a','a','n'],
     ['e','t','a','e'],
     ['i','h','k','r'],
     ['i','f','l','v']
     ]
     Return ["eat","oath"].

     time : O(m * n * TrieNode)
     space : TrieNode

     * @param board
     * @param words
     * @return
     */
//thinking process:
    
    // given a list words and 2D array of chars, visit in 2D board by adjacent cells, so output a list which lies 
    //in words and board
    
    // we can use search path by brutal force, but that would not be efficient, so we created 
    //triNode to improve
    // so if board[i][j] match the char in triNode tree, then we continue, if not, we just 
    //return from this path.
    
    //brute force is O(mn 4^k)
    
    //remember how to build triNode and how to search from it
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                helper(board, i, j, root, res);
            }
        }
        return res;
    }

    public void helper(char[][] board, int i, int j, TrieNode p, List<String> res) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return;
        char c = board[i][j];
        if (c == '#' || p.next[c - 'a'] == null) return;
        p = p.next[c - 'a'];
        if (p.word != null) {
            res.add(p.word);
            p.word = null;
        }
        board[i][j] = '#';
        helper(board, i - 1, j, p, res);
        helper(board, i + 1, j, p, res);
        helper(board, i, j + 1, p, res);
        helper(board, i, j - 1, p, res);
        board[i][j] = c;
    }

    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode p = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (p.next[i] == null) {
                    p.next[i] = new TrieNode();
                }
                p = p.next[i];
            }
            p.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        //this was to mark it is the end of the string
        String word;
    }
}
