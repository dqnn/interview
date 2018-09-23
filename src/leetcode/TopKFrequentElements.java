package leetcode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : TopKFrequentElements
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : TODO
 */
public class TopKFrequentElements {
    /**
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

     * @param nums
     * @param k
     * @return
     */

    // PriorityQueue : time : O(nlogn) space : O(n)
    public List<Integer> topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            maxHeap.add(entry);
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {  // O(klogn)
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }

    // TreeMap : time : O(nlogn) space : O(n)
    public List<Integer> topKFrequent3(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (freqMap.containsKey(freq)) {
                freqMap.get(freq).add(num);
            } else {
                freqMap.put(freq, new LinkedList<>());
                freqMap.get(freq).add(num);
            }
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {  // O(klogn)
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }

    // Bucket sort : time : O(n) space : O(n)
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] bucket = new List[nums.length + 1];
        for (int num : map.keySet()) {
            int freq = map.get(num);
            if (bucket[freq] == null) {
                bucket[freq] = new LinkedList<>();
            }
            bucket[freq].add(num);
        }

        List<Integer> res = new ArrayList<>();
        for (int i = bucket.length - 1; i >= 0 && res.size() < k; i--) {
            if (bucket[i] != null) {
                res.addAll(bucket[i]);
            }
        }
        /*
        for (int i = bucket.length - 1; i >= 0; i--) {
            int j = 0;
            while (bucket[i] != null && j < bucket[i].size() && k > 0) {
                res.add(bucket[i].get(j++));
                k--;
            }
        }*/
        return res;
    }
    
    // we use Map to count frequency and use priority queue to sort
    public List<Integer> topKFrequent5(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length < 1) {
            return res;
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        for(int temp : nums) {
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        
        Queue<int[]> q = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            q.offer(new int[]{entry.getKey(), entry.getValue()});
                
        }
        for(int i = 0; i< k;i++) {
            res.add(q.poll()[0]);
        }
        return res;
    }
}
