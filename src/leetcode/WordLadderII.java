package leetcode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordLadderII
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 126. Word Ladder II
 */
public class WordLadderII {
    /**
     * Given two words (beginWord and endWord), and a dictionary's word list,
     * find all shortest transformation sequence(s) from beginWord to endWord, such that:

     Only one letter can be changed at a time
     Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
     For example,

     Given:
     beginWord = "hit"
     endWord = "cog"
     wordList = ["hot","dot","dog","lot","log","cog"]

     Return
     [
     ["hit","hot","dot","dog","cog"],
     ["hit","hot","lot","log","cog"]
     ]

     BFS + DFS

     无向图 -> BFS -> 树 -> DFS -> 结果

     hit -> hot -> dot -> dog - cog
                -> lot -> log - cog

     map : hot (hit)
           dot (hot)
           lot (hot)
           dog (dot)
           log (lot)
           cog (dog,log)

     time : O(V + E) * wordList(max(length))  不确定
            O(n ^ 2)
     space : O(n)

     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */

    //thinking process:
    //the problem is to give begin and endword, and diction, so we need to output the all 
    //possible path of the transformation
    
    //we can use DFS to visit from the begin to end word
    
    // we use visited and unvisited set to record the string we have transformed. we use 
    //Map<String, List<String> to record all possible word transformation, 
    //unvisitedSet means the word in dicts we have not visited
    //visitedSet means the word in dicts we have visited
    
    //we use visitedSet.add(newWord) to judge whether newWord was always in visited set or not. 
    // we use curLevel as 
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (wordList.size() == 0) return res;
        //this means current how many # of words in current q
        int curQueNum = 1;
        int nextQueNum = 0;
        boolean found = false;

        Queue<String> queue = new LinkedList<>();
        HashSet<String> unvisited = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();

        HashMap<String, List<String>> map = new HashMap<>();

        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            String word = queue.poll();
            curQueNum--;
            for (int i = 0; i < word.length(); i++) {
                StringBuilder builder = new StringBuilder(word);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    builder.setCharAt(i, ch);
                    String newWord = builder.toString();
                    //this means it is reachable, so we need to record this into visit and remove from
                    //unvisited set
                    if (unvisited.contains(newWord)) {
                        //if visited does not have this word, so it would return true
                        if (visited.add(newWord)) {
                            queue.offer(newWord);
                            nextQueNum++;
                        }
                        // map has such newWord
                        if (map.containsKey(newWord)) {
                            map.get(newWord).add(word);
                        } else {
                            List<String> list = new ArrayList<>();
                            list.add(word);
                            map.put(newWord, list);
                        }
                        //if found then we don't need to visit again bcause there is 
                        //no way repeated on same level we need to find another level
                        if (newWord.equals(endWord)) {
                            found = true;
                        }
                    }
                }
            }
//curNum是指当前queue里面单词的个数，每poll出一个单词时，curNum就减1，当curNum==0时，说明当前level处理完毕，
            //准备要处理下一个level了，所以才将nextNum赋值给curNum
            if (curQueNum == 0) {
                if (found) break;
                curQueNum = nextQueNum;
                nextQueNum = 0;
                unvisited.removeAll(visited);
                visited.clear();
            }
        }
        //after all done, this would like to be
        //map-{lot=[hot], log=[lot], dot=[hot], cog=[dog, log], hot=[hit], dog=[dot]}
        //we always pass new ArrayList
        dfs(res, new ArrayList<>(), map, endWord, beginWord);
        return res;
    }
    //then we use dfs to visit the map to get begin to end string,
    //we firstly visit the end word, and from end word to begin word and we always remove first one 
    //as retreat
    private void dfs(List<List<String>> res, List<String> list, HashMap<String, List<String>> map, String endWord, 
            String startWord) {
        if (endWord.equals(startWord)) {
            list.add(0, startWord);
            res.add(new ArrayList<>(list));
            // list.remove(list.size - 1)
            list.remove(0);
            return;
        }
        list.add(0, endWord);
        if (map.get(endWord) != null) {
            //we search from map's list from one to another
            for (String s : map.get(endWord)) {
                dfs(res, list, map, s, startWord);
            }
        }
        //this removes first element which should be the tail of all these words
        list.remove(0);
    }
}
