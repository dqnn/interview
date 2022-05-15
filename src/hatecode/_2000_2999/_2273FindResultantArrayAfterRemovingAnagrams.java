package hatecode._2000_2999;

import java.util.*;
public class _2273FindResultantArrayAfterRemovingAnagrams {
/*
2273. Find Resultant Array After Removing Anagrams
You are given a 0-indexed string array words, where words[i] consists of lowercase English letters.

In one operation, select any index i such that 0 < i < words.length and words[i - 1] and words[i] are anagrams, and delete words[i] from words. Keep performing this operation as long as you can select an index that satisfies the conditions.

Return words after performing all operations. It can be shown that selecting the indices for each operation in any arbitrary order will lead to the same result.

An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase using all the original letters exactly once. For example, "dacb" is an anagram of "abdc".

 

Example 1:

Input: words = ["abba","baba","bbaa","cd","cd"]
Output: ["abba","cd"]
*/
    
    //interview friendly, 
    //thinking process: the problem is to say: given a string array,
    //if A[i] and A[i+1] have same character, then we should remove A[i+1] and keep
    //A[i],return the last
    
public List<String> removeAnagrams_Interview(String[] A) {
    List<String> res= new ArrayList<>();
    int n= A.length;
    for(int base=0;base<n;){
        int nextPos=base+1;
        while( nextPos<n && isSimiliar(A[base],A[nextPos]) ) nextPos++;
        
        res.add(A[base]);
        base=nextPos;
    }
    return res;
}
//function for checking if two string are anagrams or not
boolean isSimiliar(String p,String q){
    int[] cnt= new int[26];
    for(int i=0;i<p.length();i++) cnt[p.charAt(i)-'a']++;
    for(int i=0;i<q.length();i++) cnt[q.charAt(i)-'a']--;
    for(int i:cnt) if(i!=0) return false;
    return true;
}
    public List<String> removeAnagrams(String[] words) {
        Map<String, Queue<Integer>> map = new HashMap<>();
        
        for(int i = 0; i < words.length; i++) {
            char[] chs = words[i].toCharArray();
            TreeMap<Character, Integer> countMap = new TreeMap<>();
            for(char c: chs) {
                countMap.put(c, countMap.getOrDefault(c, 0) + 1);
            }
            StringBuilder sb = new StringBuilder();
            for(Map.Entry<Character, Integer> entry: countMap.entrySet()) {
                sb.append(entry.getKey());
                sb.append(entry.getValue());
            }
            map.computeIfAbsent(sb.toString(), v->new LinkedList<>()).add(i);
        }
        //System.out.println(map);
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<String, Queue<Integer>> entry: map.entrySet()) {
            Queue<Integer> q = entry.getValue();
            int cur = q.poll();
            res.add(cur);
            while(!q.isEmpty()) {
                int temp = q.poll();
                if (cur + 1 != temp) {
                    res.add(temp);
                }
                cur = temp;
            }
        }
        Collections.sort(res);
        List<String> ret = new ArrayList<>();
        res.forEach(e->ret.add(words[e]));
        
        return ret;
        
    }

}