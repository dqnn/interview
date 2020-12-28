package hatecode._1000_1999;
public class _1668MaximumRepeatingSubstring {
/*
1668. Maximum Repeating Substring
For a string sequence, a string word is k-repeating if word concatenated k times is a substring of sequence. The word's maximum k-repeating value is the highest value k where word is k-repeating in sequence. If word is not a substring of sequence, word's maximum k-repeating value is 0.

Given strings sequence and word, return the maximum k-repeating value of word in sequence.

 

Example 1:

Input: sequence = "ababc", word = "ab"
Output: 2
Explanation: "abab" is a substring in "ababc".
*/
    //thinking process: O(n)/O(1)
    
    //notes, baba,b will return 1, it must be repeated together, not seperately
    public int maxRepeating2(String s, String w) {
       int res = 0;
    
       
       for(int i = 0; i< s.length(); i++) {
           int prev = -1, cur= s.indexOf(w, i);
           int count = 0;
           while (cur >=0 && (prev == -1 || prev + w.length() == cur)) {
              prev = cur;
              cur = s.indexOf(w, cur + w.length());
              count++;
            }
           
           res = Math.max(count, res);
           if (i + w.length() >= s.length()) break;
           //else i += w.length();
       }
       return res;
       
    }
    
    public int maxRepeating(String s, String w) {
       int res = 0;
       String temp  = w;
       while(s.indexOf(temp) >=0) {
           res++;
           temp += w;
           //System.out.println(temp);
       }
       
       return res;
    }
    
   
}