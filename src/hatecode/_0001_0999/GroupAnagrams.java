package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by duqiang on 28/07/2017.
 */
public class GroupAnagrams {
    /**
     * 49. Group Anagrams
     * Given an array of strings, group anagrams together.

     For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
     Return:
     [
     ["ate", "eat","tea"],
     ["nat","tan"],
     ["bat"]
     ]


     * @param strs
     * @return
     */

    // time : O(m * n * logn) space : O(m * n)
    // m is strs[] 
    // n is longest str length
    //  n lgn is sort 
    // space is the usage we use in map
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            char[] ch = str.toCharArray();
            // we sort and form a string
            Arrays.sort(ch);
            String s = new String(ch);
            if (map.containsKey(s)) {
                List<String> list = res.get(map.get(s));
                list.add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, res.size());
                res.add(list);
            }
        }
        return res;
    }
    
    //also using sort and hashmap to 
    //interview friendly, just sort to make sure they are the same
    public List<List<String>> groupAnagrams3(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length < 1) {
            return res;
        }
        
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs);
            String s = new String(chs);
            map.computeIfAbsent(s, v-> new ArrayList<>());
            map.get(s).add(str);
        }
        res.addAll(map.values());
        return  res;
    }

    // time : O(m * n) space : O(m * n)  counting sort
    // 

    public List<List<String>> groupAnagrams2(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for (Character ch : str.toCharArray()) {
                count[ch - 'a']++;
            }
            String s = "";
            // so we sort according to alphbet this is better than Arrays.sort
            // 3a2b1c, like this way to represent original string
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    s += String.valueOf(count[i]) + String.valueOf((char)(i + 'a'));
                }
            }
            if (map.containsKey(s)) {
                List<String> list = map.get(s);
                list.add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
