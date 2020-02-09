package hatecode._0001_0999;

import java.util.*;
class _970PowerfulInteger {
/*
970. Powerful Integers
Given two positive integers x and y, an integer is powerful if it is equal to x^i + y^j for some integers i >= 0 and j >= 0.

Return a list of all powerful integers that have value less than or equal to bound.

You may return the answer in any order.  In your answer, each value should occur at most once.

 

Example 1:

Input: x = 2, y = 3, bound = 10
Output: [2,3,4,5,7,9,10]
Explanation: 
2 = 2^0 + 3^0
3 = 2^1 + 3^0
4 = 2^0 + 3^1
5 = 2^1 + 3^1
7 = 2^2 + 3^1
9 = 2^3 + 3^0
10 = 2^0 + 3^2
*/
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> set = new HashSet<>();
        
        for(int a = 1; a < bound; a *= x) {
            for(int b = 1; a + b <= bound; b *= y) {
                set.add(a + b);
                //means b never change, then we just need 1 time is enough
                if(y == 1) break; 
            }
            //a no change, so 1 time is enough
            if(x == 1) break;
        }
        
        return new ArrayList<>(set);
    }
}