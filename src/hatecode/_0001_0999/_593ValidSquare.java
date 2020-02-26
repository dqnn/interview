package hatecode._0001_0999;

import java.util.Arrays;

public class _593ValidSquare {
/*
593. Valid Square
Given the coordinates of four points in 2D space, return whether the four points could construct a square.

The coordinate (x,y) of a point is represented by an integer array with two integers.

Example:
Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
Output: True
Note:

All the input integers are in the range [-10000, 10000].
A valid square has four equal sides with positive length and four equal angles (90-degree angles).
Input points have no order.

*/  //thinking process:
    
    //if we permute all possible solutions, we permuate all 4 nodes, P1P2P3P4 they are the same 
    //as P2P3P4P1
    public double dist(int[] p1, int[] p2) {
        return (p2[1] - p1[1]) * (p2[1] - p1[1]) + (p2[0] - p1[0]) * (p2[0] - p1[0]);
    }
    public boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        return dist(p1,p2) > 0 
                && dist(p1, p2) == dist(p2, p3) 
                && dist(p2, p3) == dist(p3, p4) 
                && dist(p3, p4) == dist(p4, p1) 
                && dist(p1, p3) == dist(p2, p4);
    }
    public boolean validSquare2(int[] p1, int[] p2, int[] p3, int[] p4) {
        return check(p1, p2, p3, p4) || check(p1, p3, p2, p4) || check(p1, p2, p4, p3);
    }
    
    //this is better, we use sort, follow up: followup: 
    //给n个点，问总共正方形的个数，最后会要求优化到O(n^2)
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int[][] p={p1,p2,p3,p4};
        //if x is the same, then we sort on y asc
        Arrays.sort(p, (l1, l2) -> l2[0] == l1[0] ? l1[1] - l2[1] : l1[0] - l2[0]);
        
        return dist(p[0], p[1]) != 0 
                && dist(p[0], p[1]) == dist(p[1], p[3]) 
                && dist(p[1], p[3]) == dist(p[3], p[2]) 
                && dist(p[3], p[2]) == dist(p[2], p[0])   
                && dist(p[0], p[3]) == dist(p[1],p[2]);
    }
    //another good solutions
    public boolean validSquare3(int[] p1, int[] p2, int[] p3, int[] p4) {
        //6 edges
        long[] lengths = {
                length(p1, p2), length(p2, p3), length(p3, p4), 
                length(p4, p1), length(p1, p3), length(p2, p4) }; 
        long max = 0, nonMax = 0;
        for (long len : lengths) {
            max = Math.max(max, len);
        }
        int count = 0;
        for (int i = 0; i < lengths.length; i++) {
            if (lengths[i] == max)
                count++;
            else
                nonMax = lengths[i]; // non diagonal side.
        }
        if (count != 2)
            return false; // diagonals lenghts have to be same.

        for (long len : lengths) {
            if (len != max && len != nonMax)
                return false; // sides have to be same length
        }
        return true;
    }

    private long length(int[] p1, int[] p2) {
        return (long) Math.pow(p1[0] - p2[0], 2) + (long) Math.pow(p1[1] - p2[1], 2);
    }
}