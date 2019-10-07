package hatecode;

import java.util.*;
public class _1040MovingStonesUntilConsecutiveII {
/*
1040. Moving Stones Until Consecutive II
On an infinite number line, the position of the i-th stone is given by stones[i].  Call a stone an endpoint stone if it has the smallest or largest position.

Each turn, you pick up an endpoint stone and move it to an unoccupied position so that it is no longer an endpoint stone.

In particular, if the stones are at say, stones = [1,2,5], you cannot move the endpoint stone at position 5, since moving it to any position (such as 0, or 3) will still keep that stone as an endpoint stone.

The game ends when you cannot make any more moves, ie. the stones are in consecutive positions.

When the game ends, what is the minimum and maximum number of moves that you could have made?  Return the answer as an length 2 array: answer = [minimum_moves, maximum_moves]

 

Example 1:

Input: [7,4,9]
Output: [1,2]
Explanation: 
We can move 4 -> 8 for one move to finish the game.
Or, we can move 9 -> 5, 4 -> 6 for two moves to finish the game.
*/
    //thinking process: 
    
    //the problem is to say: given an array A, each value means a position which
    //i-th stone placed, so you can move the endpoint stone to any other positions to
    //make the stone not end point stone.
    
    //return [min_move, max_move]
    //[7,4,9], min_move: we can only move 4->8, 
    //for max move, we can move 9 to 6, then 7->5,
    //so the last situation is that they are consective on one line
    
    //so for the max possible move, we have two options, 
    //a. we can move first into the middle = A[n-1] - A[1] - n + 2
    //b. we can move last into the middle = A[n-2] - A[0] -n + 2
    //because we can always find a move if there is empty position
    
    //for the min possible move, we use a sliding window from left, 
    //
   public int[] numMovesStonesII(int[] A) {
        Arrays.sort(A);
        int n = A.length, low = n;
        int high = Math.max(A[n - 1] - A[1] - n + 2, 
                            A[n - 2] - A[0] - n + 2);
        //n-sliding window to find the possible min move
        //this part is the key and it is tricky
        
        //we use a length of n - 1 to measure the empty slots in the 
        //window, for example，[7,4,9]->[4,7,9]
        //the min move, 4->8, 
        //[6,5,4,3,10]->[3,4,5,6,10]
        //so we have 7, 8, 9 unoccupied slot, then 
        int l = 0;
        for (int r = 0; r < n; ++r) {
            while (A[r] - A[l] >= n) ++l;
            //if the window length = n - 1 or we have n -2 position
            if (r - l + 1 == n - 1 
                    && A[r] - A[l] == n - 2)
                low = Math.min(low, 2);
            else low = Math.min(low, n - (r - l + 1));
        }
        return new int[] {low, high};
    }
}