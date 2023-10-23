
package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AlienDictionary
 * Creator : professorX
 * Date : Dec, 2017
 * Description : 269. Alien Dictionary
 */
public class _269AlienDictionary {
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

     * @param A
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
    public static String alienOrder(String[] A) {
        if (A == null || A.length == 0) return "";
        //store each character next characters, we use set to avoid dup
        HashMap<Character, Set<Character>> map = new HashMap<>();
        //record each character indegree, char - 'a' as index
        int[] degree = new int[26];
        int count = 0;
        //save all possible roots, here if it already showed up then continue;
        //every node has at least 1 degree, let's say
        for (String s : A) {
            for (char c : s.toCharArray()) {
                //we only +1 for each char, initialized as 1 for all words
                if (degree[c - 'a'] == 0) {
                    count++;
                    degree[c - 'a'] = 1;
                }
            }
        }

        //this finally will process: w->e->r->t->f. plus line 92, f in degree = 2
        // map = {r=[t], t=[f], e=[r], w=[e]}
        
        //and we update all 
        // possible paths to previous to next word,
        //the order of the words showed the character order,we extract all possible info into the maps
        //and degree, the map only will save from previous character to next different ones. it will only
        //contains part of all characters
        for (int i = 0; i < A.length - 1; i++) {
            //["abc","ab"] ==> "", need to have such case
            if (A[i].length() > A[i+1].length() && A[i].startsWith(A[i+1])) {
                return "";
            }
            char[] cur = A[i].toCharArray();
            char[] next = A[i + 1].toCharArray();
            int len = Math.min(cur.length, next.length);
            for (int j = 0; j < len; j++) {
                if (cur[j] != next[j]) {
                    if (!map.containsKey(cur[j])) {
                        map.put(cur[j], new HashSet<>());
                    }
                    //if we can add to the set, degree ++,
                    //here is one tricky ppoint that we only degree++ when you can find a new path, if it exist path, then 
                    //just drop it 
                    /*
                     * ["ac","ab","zc","zb"]
                     * 
                     * [ac, ab] ---> c-->b
                     * "ab","zc" ---> a--->z
                     * "zc","zb" ---> c--> b
                     * 
                     * we cannot add indgree for b again for ["zc","zb"] because it it same link, 
                     * 
                     * 
                     */
                    if (map.get(cur[j]).add(next[j])) {
                        degree[next[j] - 'a']++;
                    }
                    //we need break here because in a string compare program, if we already find a 
                    //different character, that will decide its order, the character after this will be 
                    //no impact on the order, like abc, baa, a should be before b, so even it has a after, no use
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
        //     w->e->r ->t ->f
        StringBuilder res = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            res.append(c);
            if (map.containsKey(c)) {
                for (char ch : map.get(c)) {
                    //means we have removed one relationship util this node become the root 
                    //of the left graph
                    if (--degree[ch - 'a'] == 1) {
                        queue.offer(ch);
                    }
                }
            }
        }
        //how many distinct characters in the strings,if different which means it is correct, 
        //["abc","ab"] ==> ""
        if (res.length() != count) return "";
        return res.toString();
    }
}
