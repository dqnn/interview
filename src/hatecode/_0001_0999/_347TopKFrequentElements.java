package hatecode._0001_0999;

import java.util.*;
public class _347TopKFrequentElements {
/*
347. Top K Frequent Elements
Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.

 

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
*/
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given an integer array, we want to return 
    //top k most frequently elements.
    
    //we use bucket sort to store the frequency, and visit back from 
    //bucket[bucket.length -1], so it will contain highest frequency numbers
    public int[] topKFrequent(int[] A, int k) {
        List<Integer>[] buckets = new ArrayList[A.length + 1];
        
        Map<Integer, Integer> map = new HashMap<>();
        Arrays.stream(A).forEach(e->map.put(e, map.getOrDefault(e, 0) + 1));
        
       // System.out.println(map);
        
        for(int a : map.keySet()) {
            int fre = map.get(a);
            if (buckets[fre] == null) buckets[fre] = new ArrayList<>();
            buckets[fre].add(a);
        }
        
        int[] res = new int[k];
        int index = 0;
        for(int i = buckets.length-1; i>=0;i--) {
            
            if (buckets[i]==null || buckets[i].size() == 0) continue;
            int size = buckets[i].size();
            
            while (index < k && size >0) {
                res[index] = buckets[i].get(size-1);
                size--;
                index++;
            }
        }
        
        return res;
    }
}