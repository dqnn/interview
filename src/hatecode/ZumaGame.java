package hatecode;
public class ZumaGame {
    /*
 488. Zuma Game
Think about Zuma Game. You have a row of balls on the table, 
colored red(R), yellow(Y), blue(B), green(G), and white(W). You also have several 
balls in your hand.

Each time, you may choose a ball in your hand, and insert it into the row 
(including the leftmost place and rightmost place). Then, if there is a group of 
3 or more balls in the same color touching, remove these balls. Keep doing this 
until no more balls can be removed.

Find the minimal balls you have to insert to remove all the balls on the table. 
If you cannot remove all the balls, output -1.

Examples:

Input: "WRRBBW", "RB"
Output: -1
Explanation: WRRBBW -> WRR[R]BBW -> WBBW -> WBB[B]W -> WW

Input: "WWRRBBWW", "WRBRW"
Output: 2
Explanation: WWRRBBWW -> WWRR[R]BBWW -> WWBBWW -> WWBB[B]WW -> WWWW -> empty

Input:"G", "GGGGG"
Output: 2
Explanation: G -> G[G] -> GG[G] -> empty 

Input: "RBYYBBRRB", "YRBGB"
Output: 3
Explanation: RBYYBBRRB -> RBYY[Y]BBRRB -> RBBBRRB -> RRRB -> B -> B[B] -> BB[B] -> empty 

Note:
You may assume that the initial row of balls on the table won’t have any 3 or more 
consecutive balls with the same color.
The number of balls on the table won't exceed 20, and the string represents these 
balls is called "board" in the input.
The number of balls in your hand won't exceed 5, and the string represents these 
balls is called "hand" in the input.
Both input strings will be non-empty and only contain characters 'R','Y','B','G','W'.

    */
    int MAXCOUNT = 6;   // the max balls you need will not exceed 6 since "The number of balls in your hand won't exceed 5"

    public int findMinStep(String board, String hand) {
        if (board == null || board.length() < 1) {
            return 0;
        }
        if (hand == null || hand.length() < 1) {
            return -1;
        }
        
        int[] h = new int[26];
        for(char ch: hand.toCharArray()) {
            h[ch - 'A'] ++;
        }
        // append a "#" to avoid special process while j==board.length, make the code shorter.
        int res = helper(board + "#", h);
        return res == MAXCOUNT ? -1 : res;
    }
    
    private int helper(String s, int[] h) {
        s = burst(s);
        if (s.equals("#")) return 0;
        int res = MAXCOUNT, need = 0;
        for(int i = 0, j=0; j < s.length(); j++) {
            if (s.charAt(i) == s.charAt(j)) continue;
            need = 3 - (j -i); //balls need to remove current consecutive balls.
            //permutation templates, how could i understand? 
            //
            if (h[s.charAt(i) - 'A'] >= need) {
                h[s.charAt(i) - 'A'] -= need;
                res = Math.min(res, need + helper(s.substring(0,i) + s.substring(j), h));
                h[s.charAt(i) - 'A'] += need;
            }
            i = j;
        }
        return res;
    }
    
    private String burst(String s) {
        for(int i =0, j =0; j < s.length(); j++) {
            if (s.charAt(j) == s.charAt(i)) continue;
            //i is not included
            if (j - (i + 1) + 1 >= 3) {
                return burst(s.substring(0,i) + s.substring(j));
            //move i to j position so we can move faster
            } else {
                i = j;
            } 
        }
        return s;
    }
}