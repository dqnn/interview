package hatecode;

import java.util.*;


/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FlipGameII
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 294. Flip Game II
 */
public class FlipGameII {

    /**
     * You are playing the following Flip Game with your friend: Given a string that contains
     * only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
     * The game ends when a person can no longer make a move and therefore the other person will be the winner.

     Write a function to determine if the starting player can guarantee a win.

     For example, given s = "++++", return true. The starting player can guarantee a win by
     flipping the middle "++" to become "+--+".

     HashMap<String, Boolean>


     * time : O(2^N) 不确定
     * space : O(2^N)
     * @param s
     * @return
     */

    public boolean canWin(String s) {
        if (s == null || s.length() == 0) return false;
        HashMap<String, Boolean> map = new HashMap<>();
        return helper(s, map);
    }
    //how we play games, using recursive, map is to store results
    private boolean helper(String s, HashMap<String, Boolean> map) {
        if (map.containsKey(s)) return map.get(s);
        // for loop here make sure to visit all possible starting status
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                // opponent fail
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                // opponent fail then means we win
                if (!helper(opponent, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        // until here means we fail
        map.put(s, false);
        return false;
    }
}

