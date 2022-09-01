package hatecode._0001_0999;
import java.util.*;
public class _767ReorganizeString {
/*
767. Reorganize String
Given a string S, check if the letters can be rearranged so that two characters
 that are adjacent to each other are not the same.

If possible, output any possible result.  If not possible, return the 
empty string.

Example 1:

Input: S = "aab"
Output: "aba"
Example 2:

Input: S = "aaab"
Output: ""
*/
    //O(n) O(26), 
    /*
     * this problem contains special use case:
     * lllvo, lvlol,, we cannot lvoll, so we have to prioritize the 
     * most frequency elements
     * 
     * 
     * the best way is to place most frequency element on even position, 0, 2, 4..
     * others are on odd position
     * 
     * 
     */
    /* thinking process: O(n)/O(1)
     * 
     * we count each character frequency, then we check whether we can 
     * have a valid answer. maxLen <= (s.length()+1)/2, aab
     * 
     * then we place most frequency on 0, 2,4...
     * then we start to place other elements on 1, 3 ...
     * 
     * 
     */
    public String reorganizeString(String s) {
        int[] count = new int[26];

        for(char c: s.toCharArray()) {
            count[c-'a']++;
        }
        
        int max = -1, maxIdx = -1;
        for(int i = 0; i<26;i++) {
            if (count[i] > max) {
                max = count[i];
                maxIdx = i;
            }
        }
        
        if (max > (s.length() + 1)/2) return "";
        
        char[] res = new char[s.length()];
        int idx = 0;
        while(count[maxIdx] > 0) {
            res[idx] = (char)(maxIdx + 'a');
            idx += 2;
            count[maxIdx]--;
        }
        
        for(int i = 0; i<26;i++) {
            if (i == maxIdx) continue;
            while(count[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                
                res[idx] = (char)(i+'a');
                idx += 2;
                count[i]--;
            }
        }
        
        return new String(res);
        
    }
    
    //if 2 * count(char) > len(s) + 1, then we return "";
    
    
    public String reorganizeString_BF(String s) {
        if (s == null || s.length() < 1) return s;
        Map<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        Queue<Map.Entry<Character, Integer>> q = new PriorityQueue<>((a, b)->(b.getValue() - a.getValue()));
        q.addAll(map.entrySet());
        //here is the forumla,it means the most frequent character must be smaller than half of the length of string s
        // + 1 because odd even numbers
        if (2* q.peek().getValue() > s.length() + 1) return "";
        StringBuilder sb = new StringBuilder();
        while(q.size() >= 2) {
            Map.Entry<Character, Integer> entry1 = q.poll(), entry2 = q.poll();
            sb.append(entry1.getKey() +""+ entry2.getKey());
            if(entry1.getValue() > 1) {
                entry1.setValue(entry1.getValue()-1);
                q.offer(entry1);
            }
            if(entry2.getValue() > 1) {
                entry2.setValue(entry2.getValue()-1);
                q.offer(entry2);
            }
            
        }
        //if >0 then must have only one, but we are not sure its value is 1 or more
        if (q.size() > 0) sb.append(q.poll().getKey());
        //if last one is more than 1, then we cannot do it
        return sb.toString();
    }
}