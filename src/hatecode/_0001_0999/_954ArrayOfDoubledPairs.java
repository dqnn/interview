package hatecode._0001_0999;

import java.util.*;
public class _954ArrayOfDoubledPairs {
/*
954. Array of Doubled Pairs
Given an array of integers A with even length, return true if and only if it is possible to reorder it such that A[2 * i + 1] = 2 * A[2 * i] for every 0 <= i < len(A) / 2.

 

Example 1:

Input: [3,1,3,6]
Output: false
*/
/*
Intuition
Let's see a simple case
Assume all interger are positive, for example [2,4,4,8].
We have one x = 2, we need to match it with one 2x = 4.
Then one 4 is gone, we have the other x = 4.
We need to match it with one 2x = 8.
Finaly no number left.

Why we start from 2?
Because it's the smallest and we no there is no x/2 left.
So we know we need to find 2x

What if the case negative?
One way is that start from the biggest (with abosolute value smallest),
and we aplly same logic.

Another way is that start from the smallest (with abosolute value biggest),
and we try to find x/2 each turn.


Explanation
Count all numbers.
Loop all numbers on the order of its absolute.
We have counter[x] of x, so we need the same amount of 2x wo match them.
If c[x] > c[2 * x], then we return false
If c[x] <= c[2 * x], then we do c[2 * x] -= c[x] to remove matched 2x.
Don't worry about 0, it doesn't fit the logic above but it won't break our algorithme.

In case count[0] is odd, it won't get matched in the end.
(Anyway you can return false earlier here)

In case count[0] is even, we still have c[0] <= c[2 * 0].
And we still need to check all other numbers.
*/
    
    //thinking process；
    //given an array, find if we reorder the array, we can find for any i, A[2*i + 1] = 2 * A[2*i]
    
    //we use a treeMap to sort all values, then we start from small to big, 
    //if 
    //TODO: write mroe comments on this solution
    public boolean canReorderDoubled(int[] A) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int a : A) map.put(a, map.getOrDefault(a, 0) + 1);
        
        for (int x : map.keySet()) {
            if (map.get(x) == 0) continue;
            int want = x < 0 ? x / 2 : x * 2;
            //x frequency bigger than want freq
            if (x < 0 && x % 2 != 0 || map.get(x) > map.getOrDefault(want, 0))
                return false;

            map.put(want, map.get(want) - map.get(x));
        }
        return true;
    }
}