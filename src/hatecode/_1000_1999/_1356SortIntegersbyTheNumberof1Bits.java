package hatecode._1000_1999;

import java.util.*;
public class _1356SortIntegersbyTheNumberof1Bits {
/*
1356. Sort Integers by The Number of 1 Bits
Given an integer array arr. You have to sort the integers in the array in ascending order by the number of 1's in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.

Return the sorted array.

 

Example 1:

Input: arr = [0,1,2,3,4,5,6,7,8]
Output: [0,1,2,4,8,3,5,6,7]
*/
    //thinking process: O(n)/O(1)
    
    //use >> to count how many 1 are there,
    
    //note we need *1000 + i to have a unumber to make it sort correctly, 
    //for example, 0011 and 1100, they have 2 1 but they need to be in different position
    private int countOneBit(int a) {
        int res = 0;
        while(a != 0) {
            res += a & 1;
            a= a >> 1;
        }
        return res;
    }
    
    
   public int[] sortByBits(int[] A) {
        return Arrays.stream(A)
            .boxed()
            .sorted(Comparator.comparingInt(i->countOneBit(i) * 10000 + i))
            .mapToInt(i->i)
            .toArray();
    }
    
   
}