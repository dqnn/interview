package hatecode;
import java.util.*;
class ArithmeticSlicesISubsequence {
/*
446. Arithmetic Slices II - Subsequence
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, these are arithmetic sequences:

1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9
The following sequence is not arithmetic.

1, 1, 2, 5, 7

A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array 
is any sequence of integers (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.

A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], 
A[P1], ..., A[Pk-1], A[Pk] is arithmetic. In particular, this means that k ≥ 2.

The function should return the number of arithmetic subsequence slices in the array A.

The input contains N integers. Every integer is in the range of -231 and 231-1 and 0 ≤ N ≤ 1000. The output is guaranteed to be less than 231-1.


Example:

Input: [2, 4, 6, 8, 10]

Output: 7

Explanation:
All arithmetic subsequence slices are:
[2,4,6]
[4,6,8]
[6,8,10]
[2,4,6,8]
[4,6,8,10]
[2,4,6,8,10]
[2,6,10]
*/
    
  //the same as  128. Longest Consecutive Sequence
    //O(n^2)
    public static int numberOfArithmeticSlices(int[] A) {
    int res = 0;
    Map<Integer, Integer>[] map = new Map[A.length];
        
    for (int i = 0; i < A.length; i++) {
        map[i] = new HashMap<>(i);
            
        for (int j = 0; j < i; j++) {
            long diff = (long)A[i] - A[j];
            if (diff <= Integer.MIN_VALUE || diff > Integer.MAX_VALUE) continue;
            //first when i scan the map, suppose [2,4,6,8,10], i =1, j = 0;
            // then d = 2, c1 = 0, c2= 0, so we would put(2, 1) into map[i],
            //i moved to i = 2, j = 0, again, d= 4, map[2] = (4,1), in map[1] 
            // c2 = 1, why we use c2 is because when i moved, we found that 
            //idx={0,1,2}, their diff is the same
            int d = (int)diff;
            int c1 = map[i].getOrDefault(d, 0);
            int c2 = map[j].getOrDefault(d, 0);
            res += c2;
            map[i].put(d, c1 + c2 + 1);
        }
    }
        
    return res;
}
    //O(2^n)/O(n)
    public int numberOfArithmeticSlices2(int[] A) {
        if (A == null || A.length < 3) return 0;
        List<List<Integer>> res = new ArrayList<>();
        helper(res, new ArrayList<>(), A, 0);
       
        return res.size();
    }
    
    private void helper(List<List<Integer>> res, List<Integer> list, int[]A, int start) {
        if (start > A.length) {
            return;
        }
        
        if (list.size() >= 3) {
            //System.out.println("list" + list);
            int i = 1;
            while(i < list.size() - 1 && ((long)list.get(i+1) - list.get(i) == list.get(i) - list.get(i-1)) 
                  && ((long)list.get(i+1) - list.get(i) <= Integer.MAX_VALUE)
                  && ((long)list.get(i+1) - list.get(i) >= Integer.MIN_VALUE)
                  && ((long)list.get(i) - list.get(i - 1) <= Integer.MAX_VALUE)
                  && ((long)list.get(i) - list.get(i - 1) >= Integer.MIN_VALUE)) {
                i++;
            }
            //note, if it is correct, ew continue, else return, since we need to remove this element
            if (i != list.size() - 1)  {
                return;
            }
            else {
                res.add(new ArrayList<>(list));
            }
            //System.out.println("res" + res);
        }
        for(int i = start; i< A.length; i++) {
            list.add(A[i]);
            helper(res, list, A, i + 1);
            list.remove(list.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        int[] in = {2,4,6};
        System.out.println(numberOfArithmeticSlices(in));
    }
}