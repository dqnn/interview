package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : RearrangeStringkDistanceApart
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 358. Rearrange String k Distance Apart
 */
public class RearrangeStringkDistanceApart {

    /**
Given a non-empty string s and an integer k, rearrange the string such that the same 
characters are at least distance k from each other.

All input strings are given in lowercase letters. If it is not possible to rearrange 
the string, return an empty string "".

Example 1:

Input: s = "aabbcc", k = 3
Output: "abcabc" 
Explanation: The same letters are at least distance 3 from each other.
Example 2:

Input: s = "aaabc", k = 3
Output: "" 
Explanation: It is not possible to rearrange the string.
Example 3:

Input: s = "aaadbbcc", k = 2
Output: "abacabcd"
Explanation: The same letters are at least distance 2 from each other.

     Another possible answer is: "abcabcda"

     The same letters are at least distance 2 from each other.


     * @param s
     * @param k
     * @return
     */

    // time : O(n) space : O(n)
    public String rearrangeString(String s, int k) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int[] count = new int[26];
        int[] valid = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int nextLetter = findNext(count, valid, i);
            if (nextLetter == -1) {
                return "";
            }
            res.append((char)('a' + nextLetter));
            valid[nextLetter] = i + k;
            count[nextLetter]--;
        }
        return res.toString();
    }

    private int findNext(int[] count, int[] valid, int index) {
        int max = 0, res = -1;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > max && valid[i] <= index) {
                res = i;
                max = count[i];
            }
        }
        return res;
    }

    // time : O(nlogn) space : O(n)
    // we have two queues, one is <Char, Integer>, we sort by counts, so first one 
    // is the best candidate, how can we keep K distance which means we need to 
    //maintain n queue which help to find the candidate, we maintain two queues
    // one is chars in line which help us to build the string, another one is  k queue
    //which only keep k length of chars, so every time we visit two queues, 
    // we get oen char from first queue into our string and pass to next queue
    // if next queue size is bigger than k then we need to poll to first queue if 
    // count > 0; 
    // last is to check whether length is same as string since there is use case that
    //we never satisfy it
    public String rearrangeString2(String s, int k) {
        if (s == null || s.length() < 1 || k < 0) {
            return "";
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq =
                new PriorityQueue<>((a, b) -> (b.getValue() - a.getValue()));
        pq.addAll(map.entrySet());

        Queue<Map.Entry<Character, Integer>> queue = new LinkedList<>();
        StringBuilder res = new StringBuilder();

        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> cur = pq.poll();
            res.append(cur.getKey());
            cur.setValue(cur.getValue() - 1);
            queue.offer(cur);
            if (queue.size() < k) continue;
            //get first and put back to queue
            Map.Entry<Character, Integer> front = queue.poll();
            if (front.getValue() > 0) {
                pq.offer(front);
            }
        }
        return res.length() == s.length() ? res.toString() : "";
    }
}
