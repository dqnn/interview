package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by professorX on 28/07/2017.
 */
public class _350IntersectionofTwoArraysII {
    /**
     * 350. Intersection of Two Arrays II
     * Given two arrays, write a function to compute their intersection.

     Example:
     Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].


     * @param A
     * @param B
     * @return
     */

    // HashMap, time : O(n), space : O(n);
    public  int[] intersect(int[] A, int[] B) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i],map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        for (int i = 0; i < B.length; i++) {
            if (map.containsKey(B[i])) {
                if (map.get(B[i]) > 0) {
                    ret.add(B[i]);
                    map.put(B[i], map.get(B[i]) - 1);
                }
            }
        }
        
        return ret.stream().mapToInt(k->k).toArray();
    }

    // Arrays.sort time : O(nlogn) space : O(n);
    public int[] intersect2(int[] A, int[] B) {
        if (A == null || B == null || A.length < 1 || B.length < 1) {
            return new int[]{};
        }
        List<Integer> res = new ArrayList<>();
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] > B[j]) {
                j++;
            } else if (A[i] < B[j]) {
                i++;
            } else {
                res.add(B[j]);
                i++;
                j++;
            }
        }
        
        return res.stream().mapToInt(k->k).toArray();
    }
}

