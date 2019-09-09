package hatecode;

import java.util.*;
public class _672BulbSwitcherII {
/*
672. Bulb Switcher II

There is a room with n lights which are turned on initially and 4 buttons on the wall. After performing exactly m unknown operations towards buttons, you need to return how many different kinds of status of the n lights could be.

Suppose n lights are labeled as number [1, 2, 3 ..., n], function of these 4 buttons are given below:

Flip all the lights.
Flip lights with even numbers.
Flip lights with odd numbers.
Flip lights with (3k + 1) numbers, k = 0, 1, 2, ...
 

Example 1:

Input: n = 1, m = 1.
*/
    public int flipLights_Base(int n, int m) {
        Set<Integer> seen = new HashSet<>();
        n = Math.min(n, 6);
        int shift = Math.max(0, 6-n);
        for (int cand = 0; cand < 16; ++cand) {
            int bcount = Integer.bitCount(cand);
            if (bcount % 2 == m % 2 && bcount <= m) {
                int lights = 0;
                if (((cand >> 0) & 1) > 0) lights ^= 0b111111 >> shift;
                if (((cand >> 1) & 1) > 0) lights ^= 0b010101 >> shift;
                if (((cand >> 2) & 1) > 0) lights ^= 0b101010 >> shift;
                if (((cand >> 3) & 1) > 0) lights ^= 0b100100 >> shift;
                seen.add(lights);
            }
        }
        return seen.size();
    }
    
    
    public int flipLights(int n, int m) {
        n = Math.min(n, 3);
        if (m == 0) return 1;
        if (m == 1) return n == 1 ? 2 : n == 2 ? 3 : 4;
        if (m == 2) return n == 1 ? 2 : n == 2 ? 4 : 7;
        return n == 1 ? 2 : n == 2 ? 4 : 8;
    }
}