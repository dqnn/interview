package hatecode;
import java.util.*;
class EncodeStringWithShortestLength {
/*
471. Encode String with Shortest Length
Given a non-empty string, encode the string such that its encoded length is the shortest.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times.

Note:

k will be a positive integer and encoded string will not be empty or have extra space.
You may assume that the input string contains only lowercase English letters. The string's length is at most 160.
If an encoding process does not make the string shorter, then do not encode it. If there are several solutions, return any of them is fine.
 
Input: "abbbabbbcabbbabbbc"
Output: "2[2[abbb]c]"
*/
    //dp[i][j] = string from index i to index j in encoded form.
    //dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]) or if we can find some pattern in string from i to j which will result in more less length.
    //O(N^3)
    public String encode2(String s) {
        if (s == null || s.length() <= 4) return s;
        int N = s.length();
        String[][] dp = new String[N][N];
        //here l means window length, because in dp, we using down-top to assign the value to each
        for(int window = 0; window < N;window++) {
            for(int i = 0; i < N -window;i++) {
                // j move to l length of window
                int j = i + window;
                //here get the window string
                String windowStr = s.substring(i, j+1);
                
                if (j - i < 4) dp[i][j] = windowStr;
                else {
                    dp[i][j] = windowStr;
                    
                    //in windowStr, we try to check whether there is possible to have a 
                    //shorter compression substr
                    for(int k = 0; k < windowStr.length(); k++) {
                        String repeatStr = windowStr.substring(0, k+1);
                        if(repeatStr != null && windowStr.length()%repeatStr.length() == 0 
                            && windowStr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = windowStr.length()/repeatStr.length() + "[" + dp[i][i+k] + "]";
                          
                            if(ss.length() < dp[i][j].length()) dp[i][j] = ss;
                        }
                    }
                    
                  //in this window string, we try to optimize to find the shorter compressed 
                    //string
                    //we placed the dp forum calc here is better because TODO: 
                    for(int k = i; k < j; k++) {
                        if ((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k+1][j];
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }
    
    
  //we have Hashmap solution, it is better than dp
 /*
 two edge cases:
 1. "abab" should not replace "ab" as the longest repeated substring
 2. If there is an overlap for two different repeated substring, 
 you should choose the longest one, for example "abcdefabcdefffff", 
 you should let the middle 'f' belong to 'abcdef' instead of 'fffff'.

  */
    public String encode(String s) {
       int n = s.length();
       //record which substring repeated how many time up until current index,
       HashMap<String, Integer>[] substrFreMapArray = new HashMap[n];
       // record the longest repeated substring up until current index
       String[] lgestSubstrArray = new String[n];
       int bound = -1;
       for (int i = 0; i < n; i++) {
           substrFreMapArray[i] = new HashMap<>();
           lgestSubstrArray[i] = "";
           //look back from i, -1 each time,
           for (int j = i; j >= 0; j--) {
               String tmp = s.substring(j, i + 1);
               if (j > 0 && substrFreMapArray[j - 1].containsKey(tmp)) {
                   if (j - 1 > bound || tmp.length() >= lgestSubstrArray[bound].length()) {
                       substrFreMapArray[i].put(tmp, (int)substrFreMapArray[j - 1].get(tmp) + 1);
                   } else {
                       substrFreMapArray[i].put(tmp, 1);
                   }
                   if (tmp.split(lgestSubstrArray[i]).length > 0) {
                       lgestSubstrArray[i] = tmp;
                       bound = i;
                   }
               } else {
                   substrFreMapArray[i].put(tmp, 1);
               }               
           }
       }
       
       StringBuilder sb = new StringBuilder();
       for (int i = n - 1; i >= 0; i--) {
           if (lgestSubstrArray[i].length() > 0 
                   //test the frequency * length of each substr
                   && lgestSubstrArray[i].length() * (int)substrFreMapArray[i].get(lgestSubstrArray[i]) > 4) {
               sb.insert(0, (int)substrFreMapArray[i].get(lgestSubstrArray[i]) + "[" + encode(lgestSubstrArray[i]) + "]");
               i = i - lgestSubstrArray[i].length() * (int)substrFreMapArray[i].get(lgestSubstrArray[i]) + 1;
           } else {
               sb.insert(0, s.charAt(i));
           }
       }
       
       return sb.toString();
    }
}