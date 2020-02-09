package hatecode._0001_0999;
import java.util.*;
public class HandOfStraights {
    /*
     * 846. Hand of Straights Alice has a hand of cards, given as an array of
     * integers.
     * 
     * Now she wants to rearrange the cards into groups so that each group is size
     * W, and consists of W consecutive cards.
     * 
     * Return true if and only if she can.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: hand = [1,2,3,6,2,3,4,7,8], W = 3 Output: true Explanation: Alice's
     * hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
     */

    //we sort the hand, interview friendly 
    //and we leverage the pq.remove API,if return true which means they have it and remove succssfully,and so on
    public boolean isNStraightHand2(int[] hand, int W) {
        if (hand == null || hand.length < 1 || hand.length % W != 0) return false;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int temp : hand) pq.offer(temp);
        
        while(!pq.isEmpty()) {
            int temp = pq.poll();
            for(int j = 1; j< W;j++) {
                if (!pq.remove(temp + j)) return false;
            }
        }
        return true;
    }
    //so partition and consective arrays with same length,
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length < 1 || hand.length % W != 0) return false;
        
        Map<Integer, Integer> c = new TreeMap<>();
        for (int i : hand) c.put(i, c.getOrDefault(i, 0)+1);
        for (int it : c.keySet()) {
            if (c.get(it) <= 0) continue;
            //1,1,2,2,3,3 W = 2
            //so we can group them in to 1,2, 2,3, 1,2 like this way, but we can figure out 
            //faster by c.get(it + i) - c.get(it), so we do not check 1 or 2 second time if we have 
            //duplicate numbers
            for (int i = W - 1; i >= 0; --i) {
                if (c.getOrDefault(it + i, 0) < c.get(it)) return false;
                //we reduce the it + i
                c.put(it + i, c.get(it + i) - c.get(it));
            }
        }
           
        return true;
    }
    
    
}