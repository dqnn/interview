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
    //interview friendly dp[i][j] = string from index i to index j in encoded form.
    //dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j]) or if we can find some pattern in string from i to j which will result in more less length.
    //O(N^3)
    
    //thinking process: 
    public static String encode2(String s) {
        if (s == null || s.length() <= 4) return s;
        int N = s.length();
        String[][] dp = new String[N][N];
        //here window means window length, because in dp, we using down-top to assign the value to each
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
        Arrays.stream(dp).forEach(e->System.out.println(Arrays.toString(e)));
        return dp[0][s.length()-1];
    }
/*
 s= "aabcaabcd"
[a, aa, aab, aabc, aabca, aabcaa, aabcaab, 2[aabc], 2[aabc]d]
[null, a, ab, abc, abca, abcaa, abcaab, abcaabc, abcaabcd]
[null, null, b, bc, bca, bcaa, bcaab, bcaabc, bcaabcd]
[null, null, null, c, ca, caa, caab, caabc, caabcd]
[null, null, null, null, a, aa, aab, aabc, aabcd]
[null, null, null, null, null, a, ab, abc, abcd]
[null, null, null, null, null, null, b, bc, bcd]
[null, null, null, null, null, null, null, c, cd]
[null, null, null, null, null, null, null, null, d]
    
 */
    
  //we have Hashmap solution, TODO: this needs time to prove it is correct，the tricky part is to use two array to store
    // the substr longest string
 /*
 two edge cases:
 1. "abab" should not replace "ab" as the longest repeated substring
 2. If there is an overlap for two different repeated substring, 
 you should choose the longest one, for example "abcdefabcdefffff", 
 you should let the middle 'f' belong to 'abcdef' instead of 'fffff'.

  */
    public static String encode(String s) {
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
               /* Rule 2: If there is an overlap for two different repeated substring, you should choose the longest one, 
               for example "abcdefabcdefffff", you should let the middle 'f' belong to 'abcdef' instead of 'fffff'.
               tmp is new repeated substring. words[bound] is previous repeated substring.*/
               if (j > 0 && substrFreMapArray[j - 1].containsKey(tmp)) {//current str appeared in previous substring
                   if (j - 1 > bound || tmp.length() >= lgestSubstrArray[bound].length()) {
                       substrFreMapArray[i].put(tmp, (int)substrFreMapArray[j - 1].get(tmp) + 1);
                   } else {
                       substrFreMapArray[i].put(tmp, 1);
                   }
                // Rule 1: "abab" should not replace "ab" as the longest repeated substring.  "abab".split("ab").length == 0 
                   // tmp is new repeated substring. lgestSubstrArray[i] is previously found 
                   //repeated substring that ends at the same position.
                   if (tmp.split(lgestSubstrArray[i]).length > 0) {
                       lgestSubstrArray[i] = tmp;
                       bound = i;
                   }
               } else {
                   substrFreMapArray[i].put(tmp, 1);
               }               
           }
       }
       System.out.println("lgestSubstrArray: " + Arrays.toString(lgestSubstrArray));
       System.out.println("substrFreMapArray: " + Arrays.toString(substrFreMapArray));
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
/*
s = "abbbabbbcabbbabbbc"
lgestSubstrArray: [, , b, b, , , b, abbb, , , , b, b, , , b, abbb, abbbabbbc]
substrFreMapArray: [{a=1}, {ab=1, b=1}, {bb=1, abb=1, b=2}, {bb=1, b=3, bbb=1, abbb=1}, 
{bba=1, a=1, bbba=1, abbba=1, ba=1}, 
{bbbab=1, ab=1, b=1, bab=1, abbbab=1, bbab=1}, {bb=1, abb=1, babb=1, b=2, bbabb=1, bbbabb=1, abbbabb=1}, 
{bb=1, abbbabbb=1, babbb=1, b=3, bbb=1, bbbabbb=1, abbb=2, bbabbb=1}, 
{bbabbbc=1, bc=1, bbc=1, abbbabbbc=1, c=1, bbbabbbc=1, abbbc=1, bbbc=1, babbbc=1}, 
{bbca=1, bbbabbbca=1, a=1, bca=1, bbabbbca=1, bbbca=1, abbbabbbca=1, abbbca=1, babbbca=1, ca=1}, 
{ab=1, b=1, bbabbbcab=1, cab=1, bbcab=1, abbbabbbcab=1, abbbcab=1, bcab=1, babbbcab=1, bbbabbbcab=1, bbbcab=1}, 
{bb=1, abb=1, cabb=1, bbcabb=1, bbbcabb=1, b=2, bcabb=1, babbbcabb=1, abbbcabb=1, bbabbbcabb=1, bbbabbbcabb=1, abbbabbbcabb=1}, 
{bb=1, b=3, bbb=1, cabbb=1, bbabbbcabbb=1, bbbabbbcabbb=1, abbb=1, abbbabbbcabbb=1, bbbcabbb=1, bcabbb=1, bbcabbb=1, abbbcabbb=1, babbbcabbb=1}, 
{bba=1, bbabbbcabbba=1, a=1, bbbcabbba=1, abbba=1, bbbabbbcabbba=1, bcabbba=1, cabbba=1, abbbcabbba=1, abbbabbbcabbba=1, bbba=1, bbcabbba=1, babbbcabbba=1, ba=1}, 
{ab=1, b=1, bab=1, bbcabbbab=1, abbbabbbcabbbab=1, cabbbab=1, bbbcabbbab=1, abbbcabbbab=1, babbbcabbbab=1, bbab=1, bbbab=1, bbabbbcabbbab=1, bcabbbab=1, abbbab=1, bbbabbbcabbbab=1}, 
{bb=1, abb=1, abbbabbbcabbbabb=1, b=2, cabbbabb=1, bbabbbcabbbabb=1, bbbcabbbabb=1, abbbabb=1, bcabbbabb=1, babb=1, bbabb=1, bbbabb=1, bbcabbbabb=1, babbbcabbbabb=1, abbbcabbbabb=1, bbbabbbcabbbabb=1},
{bb=1, abbbabbb=1, abbbabbbcabbbabbb=1, b=3, bbb=1, bbbabbbcabbbabbb=1, bbcabbbabbb=1, bbbcabbbabbb=1, bbabbbcabbbabbb=1, abbb=2, bbabbb=1, abbbcabbbabbb=1, babbb=1, babbbcabbbabbb=1, bbbabbb=1, bcabbbabbb=1, cabbbabbb=1}, 
{bbabbbc=1, bbbcabbbabbbc=1, bbabbbcabbbabbbc=1, bc=1, bbc=1, abbbabbbc=2, c=1, abbbc=1, bbcabbbabbbc=1, bbbabbbcabbbabbbc=1, babbbcabbbabbbc=1, abbbabbbcabbbabbbc=1, bbbc=1, cabbbabbbc=1, bbbabbbc=1, abbbcabbbabbbc=1, bcabbbabbbc=1, babbbc=1}]
lgestSubstrArray: [, , b, b, , , b, abbb, ]
substrFreMapArray: [{a=1}, {ab=1, b=1}, 
{bb=1, abb=1, b=2}, 
{bb=1, b=3, bbb=1, abbb=1}, 
{bba=1, a=1, bbba=1, abbba=1, ba=1}, 
{bbbab=1, ab=1, b=1, bab=1, abbbab=1, bbab=1}, 
{bb=1, abb=1, babb=1, b=2, bbabb=1, bbbabb=1, abbbabb=1}, 
{bb=1, abbbabbb=1, babbb=1, b=3, bbb=1, bbbabbb=1, abbb=2, bbabbb=1}, 
{bbabbbc=1, bc=1, bbc=1, abbbabbbc=1, c=1, bbbabbbc=1, abbbc=1, bbbc=1, babbbc=1}]
lgestSubstrArray: [, , b, b]
substrFreMapArray: [{a=1}, {ab=1, b=1}, {bb=1, abb=1, b=2}, {bb=1, b=3, bbb=1, abbb=1}]

2[2[abbb]c]
 */
    
    public static void main(String[] args) {
        System.out.println(encode("abbbabbbcabbbabbbc"));
        System.out.println(encode2("aabcaabcd"));
    }
}