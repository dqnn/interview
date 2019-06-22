package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordLadder
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 127. Word Ladder
 */
public class WordLadder {
    /**
Given two words (beginWord and endWord), and a dictionary's word list, find the length of 
shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
     return its length 5.


     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]
     As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",

     time : O(n * 26 ^ L) L is length(word)
     time : O(n)

     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
//thinking process:
    
    // the problem is want to calc the length of string transformation from begin to end word. 
    
    // so we start from the begining word, change one char one time, suppose "hit", it has 3 chars and each char has 
    // 25 possible except itself, for example, ait, bit, etc, and we use ait /bit to detect whether is it in dic, if it is there,
    // then we find a path, and we add to the q, if equals to endword which  means already reached to the smallest path since we 
    // BFS, one level is one step, so we have to visit all nodes in q at one time or it would be DFS, in that way, we are not 
    //easy to calc the smallest steps
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<String>(wordList);
        
        if (!set.contains(endWord)) return 0;
        if (set.contains(beginWord)) {
            set.remove(beginWord);
        }
        
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        //beginning is one step
        int step = 1;
        while(!q.isEmpty()) {
            step += 1;
            int size = q.size();
            for(int k = 0; k < size; k++) {
                String word = q.poll();
                for(int i = 0; i < word.length(); i++) {
                    char[] chs = word.toCharArray();
                    for(char j = 'a'; j <= 'z'; j++) {
                        if (chs[i] == j) continue;
                        chs[i] = j;
                        String temp = new String(chs);
                        if (set.contains(temp)) {
                            if (temp.equals(endWord)) {
                                return step;
                            }
                            q.offer(temp);
                            // we have remove from the list, beacuse 
                            //1 BFS no way to meet same word again
                            // 2. we don't want retreat to previous visited node
                            set.remove(temp);
                        }
                    }
                }
            } 
        }
        return 0;
    }
    
    // two end, this is the best
/*
"hit"
"cog"
["hot","dot","dog","lot","log","cog"]


shortSet-[hit]--q--[hot]--dic-[lot, log, dot, cog, dog]
shortSet-[hot]--q--[lot, dot]--dic-[log, cog, dog]
shortSet-[cog]--q--[log, cog, dog]--dic-[]
 */
    //thinking process:
    //
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);

        if (!dict.contains(endWord)) return 0;
        if (dict.contains(beginWord)) dict.remove(beginWord);

        Set<String> beginSet = new HashSet<>(Arrays.asList(beginWord));
        Set<String> endSet = new HashSet<>(Arrays.asList(endWord));

        int steps = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            steps += 1;
            if (beginSet.size() > endSet.size()) {
                Set<String> tmp = beginSet;
                beginSet = endSet;
                endSet = tmp;
            }

            Set<String> newBeginSet = new HashSet<>();
            for (String w : beginSet) {
                char[] chs = w.toCharArray();
                for (int i = 0; i < chs.length; ++i) {
                    char ch = chs[i];
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (chs[i] == c) continue;
                        chs[i] = c;
                        String t = new String(chs);
                        // we are trying to connecting two Sets, so 
                        //from abov eg, we can see dict will be empty since 
                        //all came to endSet. so we need to check endSet first
                        if (endSet.contains(t)) return steps;
                        
                        if (!dict.contains(t)) continue;
                        dict.remove(t);
                        newBeginSet.add(t);
                    }
                    chs[i] = ch;
                }
            }
            System.out.println(String.format("beginSet-%s--q--%s--dic-%s", beginSet, newBeginSet, dict));
            beginSet = newBeginSet;
        }
        return 0;
    }

}
