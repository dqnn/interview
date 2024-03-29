package hatecode._1000_1999;

import java.util.*;
import java.util.stream.IntStream;

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
    //find the array which has less non 0 digits
    Map<Integer, Integer> map = new HashMap<>();
    _1570DotProductOfTwoSparseVectors(int[] A) {
        IntStream.range(0, A.length).filter(i -> A[i] != 0).forEach(i -> map.put(i, A[i]));
    }
    
	// Return the dotProduct of two sparse vectors
    public int dotProduct(_1570DotProductOfTwoSparseVectors vec) {
        if (map.size() > vec.map.size()) {
            return vec.dotProduct(this);
        }
        //System.out.println(map);
        int res = 0;
        for(int k : map.keySet()) {
            res += map.get(k) * vec.map.getOrDefault(k, 0);
        }
        
        return res;
    }
    
    
    //one variation is to use bit operation, 
    //
    /*
    int multipleTwoSparseMatrix(int[] A, int[] B) {
        
    }
    */
   
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);