package hatecode._1000_1999;

import java.util.*;
public class _1865FindingPairsWithACertainSum {
/*
1865. Finding Pairs With a Certain Sum
You are given two integer arrays nums1 and nums2. You are tasked to implement a data structure that supports queries of two types:

Add a positive integer to an element of a given index in the array nums2.
Count the number of pairs (i, j) such that nums1[i] + nums2[j] equals a given value (0 <= i < nums1.length and 0 <= j < nums2.length).
Implement the FindSumPairs class:

FindSumPairs(int[] nums1, int[] nums2) Initializes the FindSumPairs object with two integer arrays nums1 and nums2.
void add(int index, int val) Adds val to nums2[index], i.e., apply nums2[index] += val.
int count(int tot) Returns the number of pairs (i, j) such that nums1[i] + nums2[j] == tot.
 

Example 1:

Input
["FindSumPairs", "count", "add", "count", "count", "add", "add", "count"]
[[[1, 1, 2, 2, 2, 3], [1, 4, 5, 2, 5, 4]], [7], [3, 2], [8], [4], [0, 1], [1, 1], [7]]
Output
[null, 8, null, 2, 1, null, null, 11]
*/
    /*
     * thinking process: 
     * Constructor: O(n)/O(n)
     * add: O(1)/O(1)
     * count: O(k)/O(1), k = count of unique of nums2
     * 
     * the problem is similiar to sum of 2, given 2 arrays A and B, 
     * you need to implement add and count functions,
     * add(idx, val), B[index]+=val;
     * count(target) is to find pairs [i,j], i is in A, j is in B so we can 
     * sum them
     */
    Map<Integer, Set<Integer>> mapA, mapB;
    int[] A, B;
    public _1865FindingPairsWithACertainSum(int[] a, int[] b) {
        A= a;
        B= b;
        
        mapA = new HashMap<>();
        mapB = new HashMap<>();
        for(int i = 0; i<A.length;i++) {
            mapA.computeIfAbsent(A[i], v->new HashSet<>()).add(i);   
        }
        for(int i = 0; i<B.length;i++) {
            mapB.computeIfAbsent(B[i], v->new HashSet<>()).add(i);   
        }
        
    }
    
    public void add(int i, int v) {
        Set<Integer> set = mapB.get(B[i]);
        set.remove(i);
        
        B[i] += v;
        mapB.computeIfAbsent(B[i], val->new HashSet<>()).add(i);
        
    }
    
    public int count(int target) {
        int res = 0;
        
        //System.out.println(mapA);
        //System.out.println(mapB);
        Map<Integer, Set<Integer>> sMap = mapA;
        Map<Integer, Set<Integer>> bMap = mapB;
        if (mapA.size() > mapB.size()) {
            sMap = mapB;
            bMap = mapA;
        }
        for(int left : sMap.keySet()) {
            if (bMap.containsKey(target - left))
                res += sMap.get(left).size() * bMap.get(target - left).size();
        }
        
        return res;
    }
}

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */