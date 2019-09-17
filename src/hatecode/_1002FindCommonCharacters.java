package hatecode;

import java.util.*;
public class _1002FindCommonCharacters {
/*
1002. Find Common Characters
Given an array A of strings made only from lowercase letters, 
return a list of all characters that show up in all strings 
within the list (including duplicates).  For example, 
if a character occurs 3 times in all strings but not 4 times, 
you need to include that character three times in the final answer.

You may return the answer in any order.

 

Example 1:

Input: ["bella","label","roller"]
Output: ["e","l","l"]
*/
    //
    class Count {
        int idx;
        int count;
        public Count(int idx, int count) {
            this.idx = idx;
            this.count = count;
        }
        public String toString(){
            return "{idx=" + idx + ", count=" + count + "}";
        }
    }
    
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        if (A == null ||A.length < 1) return res;
        
        Map<String, Count> map = new HashMap<>();
        for(char ch : A[0].toCharArray()) {
            String key = ch + "";
            map.put(key, new Count(0, map.containsKey(key) ? map.get(key).count + 1 : 1));
        }
        
        for(int i =1; i < A.length; i++) {
            Map<String, Count> map2 = new HashMap<>();
            for(char ch : A[i].toCharArray()) {
                String key = ch + "";
                map2.put(key, new Count(0, map2.containsKey(key) ? map2.get(key).count + 1 : 1));
            }
            
            Iterator<Map.Entry<String, Count>> it = map2.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<String, Count> entry = it.next();
                String key = entry.getKey();
                if (map.containsKey(key)) {
                    Count cnt = map.get(key);
                    if (map.get(key).count != entry.getValue().count) {
                        int temp = Math.min(map.get(key).count, entry.getValue().count);
                        cnt.count = temp;
                    }
                    cnt.idx = i;
                }
            }
            
            it = map.entrySet().iterator();
            while(it.hasNext()) {
                Map.Entry<String, Count> entry = it.next();
                if (entry.getValue().idx != i) {
                    it.remove();
                }
            }
            //System.out.println("i=" + i + map);
            
        }
        int n = A.length;
        for(Map.Entry<String, Count> entry : map.entrySet()) {
            int k = entry.getValue().count;
            while(k >0) {
                res.add(entry.getKey());
                k--;
            }
        }
        return res;
    }
}