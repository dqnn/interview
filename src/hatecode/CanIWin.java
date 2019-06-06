package hatecode;
import java.util.*;
public class CanIWin {
    /*
     * 464. Can I Win 
     * 
     * In the "100 game," two players take turns adding, to a running
     * total, any integer from 1..10. The player who first causes the running total
     * to reach or exceed 100 wins.
     * 
     * What if we change the game so that players cannot re-use integers?
     * 
     * For example, two players might take turns drawing from a common pool of
     * numbers of 1..15 without replacement until they reach a total >= 100.
     * 
     * Given an integer maxChoosableInteger and another integer desiredTotal,
     * determine if the first player to move can force a win, assuming both players
     * play optimally.
     * 
     * You can always assume that maxChoosableInteger will not be larger than 20 and
     * desiredTotal will not be larger than 300. 
     * 
     * Example:
     * 
     * Input: maxChoosableInteger = 10 desiredTotal = 11
     * Output: false 
     * Explanation: No matter which integer the first player choose,
     * the first player will lose. The first player can choose an integer from 1 up
     * to 10. If the first player choose 1, the second player can only choose
     * integers from 2 up to 10. The second player will win by choosing 10 and get a
     * total = 11, which is >= desiredTotal. Same with other integers chosen by the
     * first player, the second player will always win.
     player1 ->  0
              /| ...\
  player2 -> 1 2 ....max 
            /|\ ..../ | \
player1 -> 2 3...  1  2 ..max-1
           ...                \
player1 ->   /      |     \   loose
player2 -> loose   win   loose
     */
    //O(n2^n)/O()
    //another version is  Map<String, Boolean> map; 
    //string is to dump the boolean[] visited array, Arrays.toString(visited)
    
    //TODO: compare this to first or last digit game, another is to compare the new 21 game.
    //how we can have a template for all the games, Cats and mouse, candy crush, etc
    Map<Integer, Boolean> map;
    boolean[] visited;
    public boolean canIWin(int n, int target) {
        // 1+2..+maxChoosableInteger = sum
        int sum = (1 + n) * n /2;
        if (target > sum) return false;
        if (n >= target || target <= 0) return true;
        
        map = new HashMap<>();
        visited = new boolean[n + 1];
        //pass desiredTotal because every time we can reduce one number from 1- maxChoosableInteger
        return helper(target);
    }
    
    public boolean helper(int desired) {
        //10,11, here we needs = 0, 
        if (desired <= 0) return false;
        
        //another way to do:  String key = Arrays.toString(visited); [true, false,]
        //using string to boolean map 
        int key = format(visited);
        if (!map.containsKey(key)) {
            //used.length = 1 + maxChoosableInteger
            for(int i = 1; i< visited.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    //if we can find second player false which means first are winner
                    if (!helper(desired - i)) {
                        map.put(key, true);
                        visited[i] = false;
                        return true;
                    }
                    visited[i] = false;
                }
            }
            map.put(key, false);
        }
        return map.get(key); 
    }
     // transfer boolean[] to an Integer 
    
     private int format(boolean[] status) {
        int num = 0;
        for (boolean b: status) {
            num <<= 1;
            if (b) num |= 1;
        }
        return num;
    }
}