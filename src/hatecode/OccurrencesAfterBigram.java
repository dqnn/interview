package hatecode;

import java.util.*;
class OccurrencesAfterBigram {
/*
 * tags:easy
1078. Occurrences After Bigram
Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.

For each such occurrence, add "third" to the answer, and return the answer.

 

Example 1:

Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]
*/
    //split and count
    public String[] findOcurrences(String s, String first, String second) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() < 1) return new String[]{};
        String[] strs = s.split(" ");
        for(int i = 1; i< strs.length - 1;i++) {
            if (strs[i].equals(second) && strs[i - 1].equals(first)) {
                res.add(strs[i+1]);
            }
        }
        
        return res.toArray(new String[res.size()]);
        
    }
}