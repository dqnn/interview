package hatecode._1000_1999;
public class _1064FixedPoint {
/*
1064. Fixed Point
Given an array A of distinct integers sorted in ascending order, return the smallest index i that satisfies A[i] == i.  Return -1 if no such i exists.

 

Example 1:

Input: [-10,-5,0,3,7]
Output: 3
*/
    //thinking perocess: O(lgn)/O(1)
    
    //the problem is to say: given an array, return smallest i which 
    //A[i] = i, else return -1;
    
    //so the key is to understand when to move l and r.
    //if A[m] < m which means the value is smaller than index, this position should 
    //place a bigger value, so we should search in on right space
    //if it equals then it should be right boarder so r = mid;
    
    //another key point is for 2nd template, we have another way
    //the key is:
    //if r = A.length then we should check l == A.length or not 
    //the 2nd way is better if we do not need to consider the length case?
    public int fixedPoint(int[] A) {
        int l = 0, r = A.length;
        while(l < r) {
            int m = l + (r - l)/2;
            if (A[m] < m) l = m + 1;
            else r = m;
        }
        if(l == A.length || A[l] != l) return -1;
        else return l;
        
    }
    
    public int fixedPoint_2ndBS(int[] A) {
        int l = 0, r = A.length - 1;
        while(l < r) {
            int m = l + (r - l)/2;
            if (A[m] < m) l = m + 1;
            else r = m;
        }
        return A[l] == l ? l : -1;
        
    }
    
    public int fixedPoint_BF(int[] A) {
        for(int i = 0; i< A.length; i++) {
            if(A[i] == i) return i;
        }
        return -1;
    }
}