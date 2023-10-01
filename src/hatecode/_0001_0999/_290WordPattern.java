package hatecode._0001_0999;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : WordPattern
 * Creator : professorX
 * Date : Sep, 2018
 * Description : 290. Word Pattern
 */
public class _290WordPattern {
    /**
     * Given a pattern and a string str, find if str follows the same pattern.

     Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

     Examples:
     pattern = "abba", str = "dog cat cat dog" should return true.
     pattern = "abba", str = "dog cat cat fish" should return false.
     pattern = "aaaa", str = "dog cat cat dog" should return false.
     pattern = "abba", str = "dog dog dog dog" should return false.

     time : O(n) space : O(n)

     * @param pattern
     * @param str
     * @return
     */
    
    
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length())
            return false;
        //here is the key, we don't want to use String as key because 
        // abc  b c a, if this way, you will see a->b, b->a, 
        Map<Object, Integer> index = new HashMap<>();
        for (Integer i=0; i<words.length; ++i)
            //the previous value associated with key, or null if there was no mapping for key. (A null return can also
           //indicate that the map previously associated null with key, if the implementation supports 
              //null values.)
            
            //we cannot use String.valueOf(Char) because if we change to string
            // it will be the same as word[i] like b c a
            if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
                return false;
        return true;
    }
    
    //we use 2 maps to pass
    /*
     * 
     */
    public boolean wordPattern_2Map(String p, String s) {
        String[] ss = s.split(" ");
        if(ss.length != p.length()) return false;
        
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();
        
        for(int i = 0; i<ss.length; i++) {
            String pc = p.charAt(i) + "";
            if (map1.containsKey(pc)) {
                if (!map1.get(pc).equals(ss[i])) return false;
            } else {
                if(map2.containsKey(ss[i])) {
                    if (!map2.get(ss[i]).equals(pc)) return false;
                }
            }
            
            map1.put(pc, ss[i]);
            map2.put(ss[i], pc);
        }
        
        return true;
    }
}
