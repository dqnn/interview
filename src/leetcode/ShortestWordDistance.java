package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ShortestWordDistance
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class ShortestWordDistance {
    /**
     * 243. Shortest Word Distance
     * Given a list of words and two words word1 and word2, return the shortest distance
     * between these two words in the list.

     For example,
     Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

     Given word1 = “coding”, word2 = “practice”, return 3.
     Given word1 = "makes", word2 = "coding", return 1.

     Note:
     You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

     space : O(1);
     * @param words
     * @param word1
     * @param word2
     * @return
     */

    //time : O(n^2);
    public static int shortestDistance(String[] words, String word1, String word2) {
        int res = words.length;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                for (int j = 0; j < words.length; j++) {
                    if (words[j].equals(word2)) {
                        res = Math.min(res, Math.abs(i - j));
                    }
                }
            }
        }
        return res;
    }

    //time : O(n)， interview friendly, so 
    // we only want to get res when a and b index are set
    //
    public static int shortestDistance2(String[] words, String word1, String word2) {
        int res = words.length;
        int a = -1;
        int b = -1;
        // so one time only word1 or word2 can match, and yes, they are nearest
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                a = i;
            } else if (words[i].equals(word2)) {
                b = i;
            }
            if (a != -1 && b != -1) {
                res = Math.min(res, Math.abs(a - b));
            }
        }
        return res;
    }
    
    
    public int shortestDistance3(String[] words, String word1, String word2) {
        if (words == null || words.length < 1) {
            return 0;
        }
        Map<String, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], v->new ArrayList<>()).add(i);
        }
        
        List<Integer> x = map.get(word1);
        List<Integer> y = map.get(word2);
        int res = Integer.MAX_VALUE;
        if (x == null || y == null) {
            return 0;
        } else {
            for(int temp1 : x) {
                for(int temp2 : y) {
                    res = Math.min(res, Math.abs(temp1 - temp2));
                }
            }
        }
        return res;
    }
}
