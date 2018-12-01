package leetcode;

import java.util.Stack;

public class PourWater {
/*
We are given an elevation map, heights[i] representing the height of the terrain at that 
index. The width at each index is 1. After V units of water fall at index K, 
how much water is at each index?

Water first drops at index K and rests on top of the highest terrain or water at that 
index. Then, it flows according to the following rules:

If the droplet would eventually fall by moving left, then move left.
Otherwise, if the droplet would eventually fall by moving right, then move right.
Otherwise, rise at it's current position.
Here, "eventually fall" means that the droplet will eventually be at a lower level 
if it moves in that direction. Also, "level" means the height of the terrain plus any 
water in that column.
We can assume there's infinitely high terrain on the two sides out of bounds of the 
array. Also, there could not be partial water being spread out evenly on more than 
1 grid block - each unit of water has to be in exactly one block.

Example 1:
Input: heights = [2,1,1,2,1,2,2], V = 4, K = 3
Output: [2,2,2,3,2,2,2]
 */
    //thinking process:  given a list of terrains, pour V water on index K, water will 
    //flow to other terrains, but if they are the same height, it will flow back. return 
    //last stable list of terrains. new height = old + water, like trap rain
    
    // for left, we find the most left index which has lowest height, and we pour water there,
    //same for right. but one kye is that index =K need to +1 because water is there no matter 
    //it flow to other terrains or not.
    //O(VN)/O(1)
    public int[] pourWater(int[] h, int V, int K) {
        if (h == null || h.length < 1 || K < 0 || K >= h.length || V <= 0) {
            return h;
        }

        while(V > 0) {
            int index = K;
            //for lef. look for decrease trend and first increase.
            //the index indicate the lowest value in the decrease trend
            for(int i = K -1; i>=0; i--) {
                if (h[i] > h[index]) {
                    break;
                } else if (h[i] < h[index]) {
                    index = i;
                }
            }
            if (index != K) {
                h[index]++;
                V--;
                continue;
            }
            
            //here index = K
            for(int i = K+1; i<h.length;i++) {
                if (h[i] > h[index]) {
                    break;
                } else if (h[i] < h[index]){
                    index = i;
                }
            }
            //no matter we find it, we need to +1 becasue water is there on K or other index
            h[index]++;
            V--;
        }
        return h;
    }
    
    
    public void scanLeft(Stack<Integer> left, int[] heights, int start) {
        for (int i = start - 1; i >= 0 && heights[i] <= heights[i + 1]; --i)
            if (heights[i] < heights[i + 1])
                left.push(i);
    }
    
    public void scanRight(Stack<Integer> right, int[] heights, int start) {
        for (int i = start + 1; i < heights.length && heights[i] <= heights[i - 1]; ++i)
            if (heights[i] < heights[i - 1])
                right.push(i);
    }
    
    //another solution stack based. so stack is used to store the index which in decrease trend
    //they is trick that if h[i] = h[i+1] is need in the loop condition, but next maybe smaller and 
    //water can flow there
    public int[] pourWater2(int[] heights, int V, int K) {
        Stack<Integer> left = new Stack<>(), right = new Stack<>();
        
        scanLeft(left, heights, K);
        scanRight(right, heights, K);
        
        while(V-- > 0){
            if (!left.empty()) {
                int current = left.pop();  // it must be that current < K and heights[current] < heights[K]
                heights[current] += 1;
                scanLeft(left, heights, current + 1);
            }
            else if (!right.empty()) {
                int current = right.pop();  // it must be that current > K and heights[current] < heights[K]
                heights[current] += 1;
                scanRight(right, heights, current - 1);
            }
            else {
                heights[K] += 1;
                scanLeft(left, heights, K);
                scanRight(right, heights, K);
            }
        }
        
        return heights;
    }
    //recursive solution, 
    //the trick is the design of drop function, l means drop left, r means drop right
    public int[] pourWater3(int[] heights, int V, int K) {
        while (V-- > 0)
            drop(heights, K, heights[K] + 1, true, true);
        return heights;
    }
   private boolean drop(int[] h, int i, int j, boolean l, boolean r) {
        if (l && i > 0            && h[i - 1] <= h[i] && drop(h, i - 1, h[i], l, false)) return true;
        if (r && i < h.length - 1 && h[i + 1] <= h[i] && drop(h, i + 1, h[i], false, r)) return true;
        // means they are equals to water index, V is on index j = h[K] +1,
        if (h[i] == j) return false;
        h[i]++;
        return true;
    }
}