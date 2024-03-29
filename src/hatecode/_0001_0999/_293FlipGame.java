package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FlipGame
 * Creator : professorX
 * Date : July, 2018
 * Description : 293. Flip Game
 */
public class _293FlipGame {
    /**
     * You are playing the following Flip Game with your friend: Given a string that contains only 
     * these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". 
     * The game ends when a person can no longer make a move and therefore the other person will be the winner.
Write a function to compute all possible states of the string after one valid move.
     * For example, given s = "++++", after one move, it may become one of the following states:

     [
     "--++",
     "+--+",
     "++--"
     ]
     If there is no valid move, return an empty list [].

     time : O(n)
     space : O(n);


     * @param s
     * @return
     */
    // such simple solution, we use substring to get it
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1, s.length()));
            }
        }
        return res;
    }
    
    public List<String> generatePossibleNextMoves_SB(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 2) return res;
        int i = 0;
        StringBuilder sb = new StringBuilder(s);
        while(i + 1 < s.length()) {
            if (s.charAt(i) == '+' && s.charAt(i) == s.charAt(i+1)) {
                sb.replace(i, i+2,"--");
                res.add(sb.toString());
            }
            sb.setLength(0);
            sb.append(s);
            i++;
        }
        
        return res;
    }
}
