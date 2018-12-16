package hatecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

class TopKFrequentWords {
/*
692. Top K Frequent Words
Given a non-empty list of words, return the k most frequent elements.

Your answer should be sorted by frequency from highest to lowest. 
If two words have the same frequency, then the word with the lower alphabetical 
order comes first.

Example 1:
Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
Output: ["i", "love"]
Explanation: "i" and "love" are the two most frequent words.
    Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:
Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
Output: ["the", "is", "sunny", "day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
    with the number of occurrence being 4, 3, 2 and 1 respectively.
Note:
You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Input words contain only lowercase letters.
Follow up:
Try to solve it in O(n log k) time and O(n) extra space.

 */
    //O(nlgn) and O(n)
    //not: str1.compareTo(str2), if str1 is lexi order is smaller than str2, this value will be < 0
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        if (words == null || words.length < 1 || k < 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for(String str : words) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b)->(b.getValue() == a.getValue() ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()));
        pq.addAll(map.entrySet());
        while(!pq.isEmpty()) {
            if (res.size() == k) {
                break;
            }
            Map.Entry<String, Integer> cur = pq.poll();
            res.add(cur.getKey());
        }
        return res;
        
    }
    
     // interview friendly 
    //thinking process: given a list of strings, return top k high frequency strings
    //so we use map as count for each string and add them into a pq, the key is we only need
    //k position in pq,so we can reduce the complexity there. 
    //N means n elements, lgk means we need lgk time to find correct position in pq
     //O(nlgk + k) and O(n + k)
    // so we keeping a fixed k position pq, and the queue lambda is reversed compared to 
    //solution 1, 
    
    //last we use Collections.reverse() to get correct output
    public List<String> topKFrequent2(String[] w, int k) {
        List<String> res = new ArrayList<>();
        if (w == null || w.length < 1 || k < 0) {
            return res;
        }
        Map<String, Integer> map = new HashMap<>();
        for(String str : w) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b)->(b.getValue() == a.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()));
        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        while(!pq.isEmpty()) {
            res.add(pq.poll().getKey());
        }
        Collections.reverse(res);
        
        return res;
        
    }
}