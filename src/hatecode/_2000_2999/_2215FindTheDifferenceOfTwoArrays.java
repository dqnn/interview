package hatecode._2000_2999;

import java.util.stream.*; 
import java.util.*; 


public class _2215FindTheDifferenceOfTwoArrays {
    /*
    2215. Find the Difference of Two Arrays
    Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:
    
    answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
    answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
    Note that the integers in the lists may be returned in any order.
    
     
    
    Example 1:
    
    Input: nums1 = [1,2,3], nums2 = [2,4,6]
    Output: [[1,3],[4,6]]
    */
        /*
         * thinking process: O(n+m)
         * 
         */
        public List<List<Integer>> findDifference(int[] A, int[] B) {
            Set<Integer> set1 = Arrays.stream(A).boxed().collect(Collectors.toSet());
            Set<Integer> set2 = Arrays.stream(B).boxed().collect(Collectors.toSet());
            for(int a : A){
                set2.remove(a);
            }
            
            for(int b : B) {
                set1.remove(b);
            }
            
            List<List<Integer>> res = new ArrayList<>();
            res.add(set1.stream().collect(Collectors.toList()));
            res.add(set2.stream().collect(Collectors.toList()));
            
            return res;
        }
    }