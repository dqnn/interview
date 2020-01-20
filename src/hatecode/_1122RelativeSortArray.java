package hatecode;

import java.util.Arrays;
import java.util.*;

public class _1122RelativeSortArray {
/*
1122. Relative Sort Array
Given two arrays arr1 and arr2, the elements of arr2 are distinct, and all elements in arr2 are also in arr1.

Sort the elements of arr1 such that the relative ordering of items in arr1 are the same as in arr2.  Elements that don't appear in arr2 should be placed at the end of arr1 in ascending order.

 

Example 1:

Input: arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
Output: [2,2,2,1,4,3,3,9,6,7,19]
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say: given two arrays, A1 and A2, A2 are all showing up in
    //A1, we want to sort A1 according to each number position in A2, if not in A2, then
    //place in last but relatively its position in A1
    
    //O(n)/O(1)
    //cnt[] is the array to record the frequency of each number in A1, 
    //after we record, then we assign the value each in A2 to A1 with frequence in 
    //cnt, then after that, we go through cnt and place the number in A1
    //this way we hacked the length of the array, but time complexity is better
    public int[] relativeSortArray_best(int[] A1, int[] A2) {
        int[] cnt = new int[1001];
        for(int n : A1) cnt[n]++;
        int i = 0;
        for(int n : A2) {
            while(cnt[n]-- > 0) {
                A1[i++] = n;
            }
        }
        for(int n = 0; n < cnt.length; n++) {
            while(cnt[n]-- > 0) {
                A1[i++] = n;
            }
        }
        return A1;
    }
    
    //this solution is tricky, O(nlgn + O(m))/O(m)
    //the action is actually sorting, so if element is in A2, then we just use its index
    //in A2, if not, we use 1000 + A1, it could satisfy 
    //1. biggest index compared to other elements in A2, 
    //2. relative and better position in A1
    public int[] relativeSortArray(int[] A1, int[] A2) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i =0; i<A2.length; i++) map.put(A2[i],i);
        
        Integer[] arr10= Arrays.stream(A1).boxed().toArray(Integer[]::new);
        Arrays.sort(arr10, (a, b)->(map.getOrDefault(a, a+1000) - map.getOrDefault(b, b+1000)));
        for(int i = 0; i< arr10.length; i++) {
            A1[i] = arr10[i];
        } 
        return A1;
    }
}