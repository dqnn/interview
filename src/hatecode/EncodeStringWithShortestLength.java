package hatecode;
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
    public String encode(String s) {
        if (s == null || s.length() <= 4) return s;
        int N = s.length();
        String[][] dp = new String[N][N];
        for(int l = 0; l < N;l++) {
            for(int i = 0; i < N -l;i++) {
                // j move to l lenth of window
                int j = i + l;
                String substr = s.substring(i, j+1);
                
                if (j - i < 4) dp[i][j] = substr;
                else {
                    dp[i][j] = substr;
                    for(int k = i; k < j; k++) {
                        if ((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()) {
                            dp[i][j] = dp[i][k] + dp[k+1][j];
                        }
                    }
                    //in substr, we try to check whether there is possible to have a longer compression substr
                    for(int k = 0; k < substr.length(); k++) {
                        String repeatStr = substr.substring(0, k+1);
                        if(repeatStr != null && substr.length()%repeatStr.length() == 0 
                            && substr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = substr.length()/repeatStr.length() + "[" + dp[i][i+k] + "]";
                          
                            if(ss.length() < dp[i][j].length()) dp[i][j] = ss;
                        }
                    }
                }
            }
        }
        return dp[0][s.length()-1];
    }
}