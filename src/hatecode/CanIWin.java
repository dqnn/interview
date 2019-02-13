package hatecode;
import java.util.*;
public class CanIWin {
/*
464. Can I Win
Example:

Input:
maxChoosableInteger = 10
desiredTotal = 11

Output:
false
Explanation:
No matter which integer the first player choose, the first player will lose.
The first player can choose an integer from 1 up to 10.
If the first player choose 1, the second player can only choose integers from 2 up to 10.
The second player will win by choosing 10 and get a total = 11, which is >= desiredTotal.
Same with other integers chosen by the first player, the second player will always win.
*/
    //O(n2^n)/O()
    //another version is  Map<String, Boolean> map; string is to dump the boolean[] visited array, Arrays.toString(visited)
    Map<Integer, Boolean> map;
    boolean[] visited;
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 1+2..+maxChoosableInteger = sum
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger /2;
        if (desiredTotal > sum) return false;
        if (desiredTotal <= 0) return true;
        
        map = new HashMap<>();
        visited = new boolean[maxChoosableInteger + 1];
        //pass desiredTotal because every time we can reduce one number from 1- maxChoosableInteger
        return helper(desiredTotal);
    }
    
    public boolean helper(int desired) {
        if (desired <= 0) return false;//why == 0 is false
        
        //another way to do:  String choseStr = Arrays.toString(chosen); 
        //using string to boolean map 
        int key = format(visited);
        if (!map.containsKey(key)) {
            //used.length = 1 + maxChoosableInteger
            for(int i = 1; i< visited.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
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