package hatecode._0001_0999;
import java.util.*;
public class FindAnagramMappings {
/*
760. Find Anagram Mappings
Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.

We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.

These lists A and B may contain duplicates. If there are multiple answers, output any of them.

For example, given

A = [12, 28, 46, 32, 50]
B = [50, 12, 32, 46, 28]
We should return
[1, 4, 3, 2, 0]
*/
    public int[] anagramMappings2(int[] A, int[] B) {
        if (A == null || B == null || A.length != B.length) {
            return new int[]{};
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i< B.length; i++) map.put(B[i], i);
        int[] res = new int[B.length];
        for(int i = 0; i< A.length;i++) res[i] = map.get(A[i]);
        return res;
    }
    
    public int[] anagramMappings(int[] A, int[] B) {
        int[] result = new int[A.length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            map.computeIfAbsent(B[i], k -> new ArrayList<>()).add(i);
        }
        for (int i = 0; i < A.length; i++) {
            result[i] = map.get(A[i]).remove(map.get(A[i]).size() - 1);
        }
        return result;
    }
}