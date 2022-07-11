package hatecode._1000_1999;

import java.util.*;
import java.util.stream.IntStream;
public class _1762BuildingsWithAnOceanView {
/*
1762. Buildings With an Ocean View
There are n buildings in a line. You are given an 
integer array heights of size n that represents the 
heights of the buildings in the line.

The ocean is to the right of the buildings. A building 
has an ocean view if the building can see the ocean without 
obstructions. Formally, a building has an ocean view if all 
the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have 
an ocean view, sorted in increasing order.

 

Example 1:

Input: heights = [4,2,3,1]
Output: [0,2,3]
*/
    //thinking process: O(n)/O(n)
    
    //monotonic list or stack
    public int[] findBuildings(int[] A) {
        
        List<Integer> res = new ArrayList<>();
        int pre = A.length - 1;
        res.add(A.length - 1);
        for(int i = A.length - 2; i>=0;i--) {
            if (A[i] > A[pre]) {
                res.add(i);
                pre = i;
            }
        }
        
        //res.stream().mapToInt(x->x).toArray(); 
        
/*
        int[] ret = new int[res.size()];
        for(int i = res.size() -1; i>=0;i--) {
            ret[res.size() - 1 - i] = res.get(i);
        }
        return ret;
        */
        
        return IntStream.range(0, res.size())
                                .mapToObj(i -> res.get(res.size()-1-i)).mapToInt(x->x).toArray();
        
        
    }
}