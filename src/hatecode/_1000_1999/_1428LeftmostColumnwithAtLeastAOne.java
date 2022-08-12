package hatecode._1000_1999;

import java.util.*;
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

public class _1428LeftmostColumnwithAtLeastAOne {
/*
1428. Leftmost Column with at Least a One
A row-sorted binary matrix means that all elements are 0 or 1 and each row of the matrix is sorted in non-decreasing order.

Given a row-sorted binary matrix binaryMatrix, return the index (0-indexed) of the leftmost column with a 1 in it. If such an index does not exist, return -1.

You can't access the Binary Matrix directly. You may only access the matrix using a BinaryMatrix interface:

BinaryMatrix.get(row, col) returns the element of the matrix at index (row, col) (0-indexed).
BinaryMatrix.dimensions() returns the dimensions of the matrix as a list of 2 elements [rows, cols], which means the matrix is rows x cols.
Submissions making more than 1000 calls to BinaryMatrix.get will be judged Wrong Answer. Also, any solutions that attempt to circumvent the judge will result in disqualification.

For custom testing purposes, the input will be the entire binary matrix mat. You will not have access to the binary matrix directly.


Input: mat = [[0,0],[1,1]]
Output: 0

*/
    class BinaryMatrix {
             public int get(int row, int col) { return 0;}
             public List<Integer> dimensions() { return new ArrayList<>();}
   };
   
   
   
   //O(mlgn)/O(1) not best
   
   //thinking process: 
   //the problem is to say: given one interface which you can access a cell value by
   //get(i ,j), the matrix is sorted each row non-decreasing, each cell can only have 0 and 1.
   //return the most-left column index.
   
   //example:[[0,0],[1,1]] --> 0
   
   //binary search on each row.
    public int leftMostColumnWithOne(BinaryMatrix m) {
        List<Integer> d = m.dimensions();
        int res = -1;
        for(int i = 0; i<d.get(0); i++) {
            int temp = helper(m, i, d.get(1));
            if (temp >= 0 && (temp < res || res == -1)) {
                res = temp;
            }
        }
        
        return res;
    }
    
    private int helper(BinaryMatrix m, int r, int c) {
        
        int i = 0, j = c -1;
        while(i < j) {
            int mid = i + (j - i)/ 2;
            if (m.get(r, mid) != 1) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        //System.out.println(i);
        
        if (m.get(r, i) == 1) return i;
        else return -1;
    }
    
    
    
    //O(m+n)/O(1)
    
    //best solution, the key is start from top left then for each step, 
    //you can only have two choices, one is down another one is left.
    //because if current cell is 1, then the answer should be now or smaller,
    //if it 0, then you just need to go down, because its uppper are 0
    public int leftMostColumnWithOne_Best(BinaryMatrix m) {
        int res = -1;
        List<Integer> d = m.dimensions();
        int r = 0, c = d.get(1)- 1;
        
        while(r < d.get(0) && c >=0) {
            if (m.get(r, c) == 1) {
                res = c;
                c--;
            } else {
                r++;
            }
        }
        
        return res;
    }
    
    //Greedy + Binary search
    //the solution is based on Greedy and BS,
    
    //we only do BS on have potential better answer than previous one
    public int leftMostColumnWithOne_Best_Interview_friendly(BinaryMatrix m) {
        List<Integer> dim = m.dimensions();
        
        int r = dim.get(0), c = dim.get(1);
        int i = 0, j = c - 1;
        int res = Integer.MAX_VALUE;
        while(i < r && j < c) {
          //if current cell[i,j] == 1, the one down to it is 0, then next line is not what we want,
          //we should skip it,
            if (m.get(i, j) == 0) {
                i++;
                continue;
            }
            
            int mid = helper_Best(m, i, j);
            res = Math.min(res, mid);
            i++;
            j = mid;
        }
        
        return res == Integer.MAX_VALUE ? -1: res;
    }
    
    private int helper_Best(BinaryMatrix m, int i, int j) {
        int l = 0, r = j;
        while(l < r) {
            int mid = l + (r -l)/2;
            if (m.get(i, mid) == 0) {
                l = mid + 1;
            } else r = mid;
        }
        
        //no matter 1 or 0, we return l;
        return l; 
    }
}