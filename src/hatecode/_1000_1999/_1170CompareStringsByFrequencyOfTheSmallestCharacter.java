package hatecode._1000_1999;

import java.util.*;
import java.util.stream.Collectors;
public class _1170CompareStringsByFrequencyOfTheSmallestCharacter {
/*
1170. Compare Strings by Frequency of the Smallest Character
Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.

Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.

 

Example 1:
Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
*/
    
    //thinking process: O(aq + bq + b + algb)/O(max(ap,bq) + b)
    
    //the problem 
    public int[] numSmallerByFrequency_BF(String[] queries, String[] words) {
        if(queries == null || queries.length < 1 || words == null || words.length < 1) return new int[0];
        
        int n = queries.length;
        List<Integer> q = calcF(queries), w = calcF(words);
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int c: w) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        
        //System.out.println(Arrays.toString(w));
        //System.out.println(Arrays.toString(q));
        int[] res = new int[n];
        for(int i = 0; i< n; i++) {
            res[i] = map.tailMap(q.get(i), false).values().stream().mapToInt(e -> e).sum();
        }
        
        return res;
    }
    
    
    
    private List<Integer> calcF(String[] in) {
        return Arrays.stream(in).map(s->smallestCharF(s)).collect(Collectors.toList());
    }
    
    private int smallestCharF(String s) {
        int[] cnt = new int[26];
        
        for(char c: s.toCharArray()) {
            cnt[c-'a']++;
        }
        for(int i = 0; i< 26; i++) {
            if (cnt[i] > 0) return cnt[i];
        }
        return -1;
    }
    
    //This is the best solution. 
    //we use an array to record for words character, 
    //we get each smallest char fre, then we want to know for current position,
    //how many strings are behind this string, so we the array to accumulate all
    //possible answer so we do not need to visit whole array
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
       int[] freq = new int[11]; // 11 so we can keep index and freq same
       for(String word: words){
           int c = smallestCharF(word);
           freq[c]++; // 1 <= c <= 10 based on constraint 1 <= words[i].length <= 10
       }
       for(int i=freq.length-2; i>=0; i--) freq[i] += freq[i+1]; // to know how many words are 'equal' or greater. 
                                                      // we need to keep the equal to avoid losing data about self.
       int[] answer = new int[queries.length];
       int i=0;
       for(String query : queries){
           int c = smallestCharF(query);
           answer[i++] = c == freq.length-1 ? 0 : freq[c+1]; // f(query) < f(W). only greater.
       }
       return answer;
   }
}