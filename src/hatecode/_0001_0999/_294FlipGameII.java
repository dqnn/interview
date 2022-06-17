package hatecode._0001_0999;

import java.util.*;


/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : FlipGameII
 * Creator : professorX
 * Date : Dec, 2017
 * Description : 294. Flip Game II
 */
public class _294FlipGameII {

    /**
     * You are playing the following Flip Game with your friend: Given a string that contains
     * only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--".
     * The game ends when a person can no longer make a move and therefore the other person will be the winner.

     Write a function to determine if the starting player can guarantee a win.

     For example, given s = "++++", return true. The starting player can guarantee a win by
     flipping the middle "++" to become "+--+".

     HashMap<String, Boolean>


     * time : O(2^N)
     * space : O(2^N)
     * @param s
     * @return
     */
    //if we do not have memo, then it would be O(n!), 
    //T(n) = (n-2) * T(n-2), 
    public boolean canWin(String s) {
        if (s == null || s.length() == 0) return false;
        HashMap<String, Boolean> map = new HashMap<>();
        return helper(s, map);
    }
    //how we play games, using recursive, map is to store results
    //we do not additional visited array because the "base" already changed to another string
    //but Can I win we have to have a visited array because in Can i Win, visited array just store
    //which numbers has been fetched, it does not mean the who win or lose, it changed the base
    //but flip Game II we already have a base or "board" the opponent string
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
    
    //XOR on all sub-games,
    //s.split("[ ]+")-->"++++--++"->"[++++,++]"
    public boolean canWin_Grundy(String s) {
        s = s.replace('-', ' ');
        int G = 0;
        List<Integer> g = new ArrayList<>();
        System.out.println(Arrays.toString(s.split("[ ]+")));
        for (String t : s.split("[ ]+")) {
            int p = t.length();
            if (p == 0) continue;
            while (g.size() <= p) {
                char[] x = t.toCharArray();
                int i = 0, j = g.size() - 2;
                while (i <= j)
                    x[g.get(i++) ^ g.get(j--)] = '-';
                g.add(new String(x).indexOf('+'));
            }
            G ^= g.get(p);
        }
        return G != 0;
    }
        
        //this is O(n) solution, but needs time to understand it
        public boolean canWin_Best(String s) {
            Set<Integer> set = new HashSet<Integer>();
            int currLen = 0;
            for (int i = 0; i <= s.length(); i++) {
                if (i == s.length() || s.charAt(i) == '-') {
                    if (currLen != 0 && currLen % 4 != 1) {
                        currLen = currLen | 1;
                        if (set.contains(currLen)) {
                            set.remove(currLen);
                        } else {
                            set.add(currLen);
                        }
                    }
                    currLen = 0;
                } else {//find +
                    currLen++;
                }
                System.out.println(set);
            }
            if (set.size() == 0) {
                return false;
            }
            return true;
        }
}

