
package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AlienDictionary
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 269. Alien Dictionary
 */
public class AlienDictionary {
    /**
     * There is a new alien language which uses the latin alphabet.
     * However, the order among letters are unknown to you. You receive a list of non-empty words 
     * from the dictionary,
     * where words are sorted lexicographically by the rules of this new language. Derive the 
     * order of letters in this language.

     Example 1:
     Given the following words in dictionary,

     [
     "wrt",
     "wrf",
     "er",
     "ett",
     "rftt"
     ]
     The correct order is: "wertf".

     Example 2:
     Given the following words in dictionary,

     [
     "z",
     "x"
     ]
     The correct order is: "zx".

     Example 3:
     Given the following words in dictionary,

     [
     "z",
     "x",
     "z"
     ]
     The order is invalid, so return "".

     图 -> 入度为0 -> BFS

     count = 5

     degree :
     w : 1
     e : 1
     r : 1
     t : 1
     f : 2

     time : (V + E) -> O(n * words(max))
     space : O(n) -> O(26) -> O(1)

     * @param words
     * @return
     */
    //thinking process:
    //given list of words, the words are sorted lexi order by this language, so to get the letter order
    
    //like [z,x] means z are before x, so z x z are incorrect dictionary, 
    //thinking about each letter is a node, each word consist nodes,so we have a map,
    //Character ->Set<Character>, we add each character and same position next char to this map
    //"wrt","wrf"--> w-{w},r->{r},t-{f}, 
    
    //and we have a degree to store each node in degree, like above f indegree + 1
    
    //this problems shows how to leverage degree in graph, also it showed
    //1. how to build graph using map
    //2. how to use indegree to indicate there are roots node
    //3. toplogic sort by PQ
    public static String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        //store each character next characters, we use set to avoid dup
        HashMap<Character, Set<Character>> map = new HashMap<>();
        //record each character indegree, char - 'a' as index
        int[] degree = new int[26];
        int count = 0;
        //save all possible roots, here if it already showed up then continue;
        for (String word : words) {
            for (char c : word.toCharArray()) {
                //we only +1 for each char, initialized as 1 for all words
                if (degree[c - 'a'] == 0) {
                    count++;
                    degree[c - 'a'] = 1;
                }
            }
        }

        //this finally will process: w->e->r->t->f. plus line 92, f in degree = 2
        //[wrt, wrf, er, ett, rftt]- > compare wrt, wrf, w->{r, f}, r->{w, f}, 
        //wrf->er, w->{r,f,e}, r->{w,f,e},f->{e, r}, and we update all 
        // possible paths to previous to next word
        for (int i = 0; i < words.length - 1; i++) {
            char[] cur = words[i].toCharArray();
            char[] next = words[i + 1].toCharArray();
            int len = Math.min(cur.length, next.length);
            for (int j = 0; j < len; j++) {
                if (cur[j] != next[j]) {
                    if (!map.containsKey(cur[j])) {
                        map.put(cur[j], new HashSet<>());
                    }
                    //if we can add to the set, degree ++, 
                    if (map.get(cur[j]).add(next[j])) {
                        degree[next[j] - 'a']++;
                    }
                    break;
                }
            }
        }
        //we add all possible roots to the queue
        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < 26; i++) {
            //this means they are only 1 degree, which means they are the 
            //roots of all dictionary
            if (degree[i] == 1) {
                queue.offer((char)('a' + i));
            }
        }
        //add the root to the results and BFS the Map
        //wrt, er, 
        //    w-> r
        //    \  /
        //     e  <- t
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            res.append(c);
            if (map.containsKey(c)) {
                for (char ch : map.get(c)) {
                    //means we have removed other relationship util it become the root 
                    //of the left graph
                    if (--degree[ch - 'a'] == 1) {
                        queue.offer(ch);
                    }
                }
            }
        }
        //
        if (res.length() != count) return "";
        return res.toString();
    }
}
