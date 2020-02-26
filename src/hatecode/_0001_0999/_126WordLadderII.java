package hatecode._0001_0999;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordLadderII
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 126. Word Ladder II
 */
public class _126WordLadderII {
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

        boolean found = false;

        Queue<String> queue = new LinkedList<>();
        HashSet<String> unvisited = new HashSet<>(wordList);
        HashSet<String> visited = new HashSet<>();

        HashMap<String, List<String>> map = new HashMap<>();

        queue.offer(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while(size-- > 0) { 
                String word = queue.poll();
                for (int i = 0; i < word.length(); i++) {
                    StringBuilder builder = new StringBuilder(word);
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == builder.charAt(i)) continue;
                        
                        builder.setCharAt(i, ch);
                        String newWord = builder.toString();
                        if (unvisited.contains(newWord)) {
                            if (visited.add(newWord)) {
                                queue.offer(newWord);
                            }
                            map.computeIfAbsent(newWord, v->new ArrayList<>()).add(word);
                            if (newWord.equals(endWord)) {
                                found = true;
                            }
                        }
                    }
                }
            }
            if (found) break;
            unvisited.removeAll(visited);
            visited.clear();
        }
        dfs(res, new ArrayList<>(), map, endWord, beginWord);
        return res;
    }
    //then we use dfs to visit the map to get begin to end string,
    //we firstly visit the end word, and from end word to begin word and we always remove first one 
    //as retreat
    private void dfs(List<List<String>> res, List<String> list, Map<String, List<String>> map, String endWord, 
            String startWord) {
        if (endWord.equals(startWord)) {
            list.add(0, startWord);
            res.add(new ArrayList<>(list));
            // list.remove(list.size - 1)
            list.remove(0);
            return;
        }
        if (!map.containsKey(endWord)) return;
        
        list.add(0, endWord);
        //we search from map's list from one to another
        for (String s : map.get(endWord)) {
            dfs(res, list, map, s, startWord);
        }
        //this removes first element which should be the tail of all these words
        list.remove(0);
    }
    
    //we use two Sets to both walk from end to middle so this is fastest solution, 
    //
    public List<List<String>> findLadders_Best(String beginWord, String endWord, List<String> wordsList) {
        List<List<String>> result = new ArrayList<>(); 
        if (wordsList == null || wordsList.size() < 1) return result; 

        Set<String> dicts = new HashSet<>(wordsList);
        if (!dicts.contains(endWord)) return result; 

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Map<String, Set<String>> map = new HashMap<>();
        //to fill the map  with all possible routes
        beginSet.add(beginWord);
        endSet.add(endWord);
        bfs(map, beginSet, endSet, dicts, false);
        //get the answer from the map
        List<String> list = new ArrayList<>();
        list.add(beginWord); 
        dfs(map, result, list, beginWord, endWord);
        return result;
    }
    private void bfs(Map<String, Set<String>> map, Set<String> beginSet, Set<String> endSet, Set<String> dicts, boolean reverse) {
        // Processed all the word in start
        if (beginSet.size() == 0) return; 
        
        dicts.removeAll(beginSet);
        Set<String> tmp = new HashSet<>();
        boolean finish = false; 
        for (String begin : beginSet) {
            char[] chs = begin.toCharArray();
            for (int i = 0; i < chs.length; i++) {
                char old = chs[i];
                for (char c = 'a' ; c <='z'; c++) {
                    if(old == c) continue; 

                    chs[i] = c;
                    String nextStr = new String(chs);
                    if (!dicts.contains(nextStr)) continue;

                    if (endSet.contains(nextStr)) finish = true; 
                    else tmp.add(nextStr);

                    String key = reverse ? nextStr : begin;
                    String value = reverse ? begin : nextStr;
                    map.computeIfAbsent(key, v->new HashSet<>()).add(value);
                }
                // restore after processing
                chs[i] = old; 
            }
        }
        if (!finish) {
            // Switch the start and end if size from start is bigger;
            //since we loop from begin, so we want faster
            if (tmp.size() > endSet.size()) bfs(map, endSet, tmp, dicts, !reverse);
            else bfs(map, tmp, endSet, dicts, reverse);
        }
    }
    private void dfs (Map<String, Set<String>> map, 
                      List<List<String>> result , List<String> list, 
                      String beginWord, String endWord) {
        if(beginWord.equals(endWord)) {
            result.add(new ArrayList<>(list));
            return; 
        }
        if (!map.containsKey(beginWord)) {
            return; 
        }
        for (String word : map.get(beginWord)) {
            list.add(word);
            dfs(map, result, list, word, endWord);
            list.remove(list.size() - 1);
        }
    }
}
