package leetcode;
import java.util.*;
class ReorganizeString {
/*
767. Reorganize String
Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
*/
    //O(n) O(26), if 2 * count(char) > len(s) + 1, then we return "";
    public String reorganizeString(String s) {
        if (s == null || s.length() < 1) return s;
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a, b)->(b.getValue() - a.getValue()));
        q.addAll(map.entrySet());
        //here is the forumla
        if (2* q.peek().getValue() > s.length() + 1) return "";
        StringBuilder sb = new StringBuilder();
        while(q.size() >= 2) {
            Map.Entry<Character, Integer> entry1 = q.poll(), entry2 = q.poll();
            sb.append(entry1.getKey() +""+ entry2.getKey());
            int count = entry1.getValue();
            if (count > 1) {
                entry1.setValue(count - 1);
                q.offer(entry1);
            }
            count = entry2.getValue();
            if (count > 1) {
                entry2.setValue(count - 1);
                q.offer(entry2);
            }
            
        }
        //if >0 then must have only one, but we are not sure its value is 1 or more
        if (q.size() > 0) sb.append(q.poll().getKey());
        //if last one is more than 1, then we cannot do it
        return sb.toString();
    }
}