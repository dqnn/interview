package hatecode._1000_1999;

import java.util.*;

public class _1570DotProductOfTwoSparseVectors {
/*
1570. Dot Product of Two Sparse Vectors
Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?

 

Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
*/
    
    //thinking process: O(min(a, b))/O(min(a, b)). min(a, b) how many non 0 in A and B
    Map<Integer, Integer> map = new HashMap<>();
    _1570DotProductOfTwoSparseVectors(int[] A) {
        for(int i = 0; i<A.length; i++) {
            if (A[i]!=0) map.put(i, A[i]);
        }
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(_1570DotProductOfTwoSparseVectors vec) {
        if (map.size() > vec.map.size()) {
            return vec.dotProduct(this);
        }
        //System.out.println(map);
        int res = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int i = entry.getKey(), j =entry.getValue();
            res += j * vec.map.getOrDefault(i, 0);
        }
        
        return res;
    }
    
   
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);