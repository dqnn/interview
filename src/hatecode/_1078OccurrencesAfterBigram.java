package hatecode;

import java.util.*;

public class _1078OccurrencesAfterBigram {
/*
1078. Occurrences After Bigram
Given words first and second, consider occurrences in some text of the form "first second third", where second comes immediately after first, and third comes immediately after second.

For each such occurrence, add "third" to the answer, and return the answer.

Example 1:

Input: text = "alice is a good girl she is a good student", first = "a", second = "good"
Output: ["girl","student"]
*/
  
    //thinking process: O(n)/O(n)
    
    //so the problem is to say to find "A B C" pattern, and return list of C, A, B
    //is the given words
    
    //so just iterate the whole sentences and return
    
    //another way is to remember each string's hashcode and do compare, but it would 
    //require extra space
    public String[] findOcurrences(String text, String first, String second) {
        if(text == null || text.length() < 1) return new String[0];
        
        String[] words = text.split(" ");
        List<String> res = new ArrayList<>();
        for (int i = 2; i < words.length; ++i) {
            if (first.equals(words[i - 2]) && second.equals(words[i - 1]))
                res.add(words[i]);
        }
        return res.toArray(new String[res.size()]);
    }
}