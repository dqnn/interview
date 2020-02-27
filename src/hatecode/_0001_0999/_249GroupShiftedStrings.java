package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : GroupShiftedStrings
 * Creator : duqiang
 * Date : Dec, 2017
 * Description : 249. Group Shifted Strings
 */
public class _249GroupShiftedStrings {
    /**
     * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
     * We can keep "shifting" which forms the sequence:

     "abc" -> "bcd" -> ... -> "xyz"
     Given a list of strings which contains only lowercase alphabets, group all strings
     that belong to the same shifting sequence.

     For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
     A solution is:

     [
     ["abc","bcd","xyz"],
     ["az","ba"],
     ["acef"],
     ["a","z"]
     ]

     time : (n * m)
     space : O(n)


     * @param strings
     * @return
     */

    // clarification questions to ask
    //1 . the answer should be sorted?
    //2  input is null, the output is object with null contents or just null
    //
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        // here we store the delta to each starting key as baseline as
        // abc --> 'a' -'a' + 'b' - 'a' + 'c' - 'a' so no matter 
        //how shift and starting character, we should be always to get the correct index
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            String key = "";
            for (int i = 0; i < str.length(); i++) {
                char c = (char)(str.charAt(i) - offset);
                if (c < 'a') c += 26;
                key += c;
            }
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            res.add(list);
        }
        return res;
    }
    //my own solution O(NM)/O(1)
    public List<List<String>> groupStrings2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length < 1) return res;
        
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            map.computeIfAbsent(getKey(str), v->new ArrayList<>()).add(str);
        }
        map.values().forEach(ele->res.add(new ArrayList<>(ele)));
        System.out.println(map);
        return res;
    }
    
    //az, ba
    public String getKey(String str) {
        if(str == null || str.length() < 1) return "0";
        
        if (str.length() == 1) return "1";
        
        StringBuilder sb1 = new StringBuilder();
        sb1.append("#");
        for(int i = 1; i < str.length(); i++) {
            int key1 = str.charAt(i) - str.charAt(i-1);
            key1 = key1 < 0 ? 26 + key1 : key1;
            sb1.append(key1);
            if (i != str.length() - 1) {
                sb1.append("#");
            }
        }
        return sb1.toString();
    }
}
