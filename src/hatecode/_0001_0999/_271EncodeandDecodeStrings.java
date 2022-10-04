package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.List;

/**
 * Description : 271. Encode and Decode Strings
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 */
public class _271EncodeandDecodeStrings {


    /**
     * time : O(n);
     * space : O(n)
     * @param strs
     * @return
     */

    // Encodes a list of strings to a single string.
    // we use 3/a->aaa this way to encode
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append('/').append(str);
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int slash = s.indexOf('/', i);// i is the starting position to search
            int size = Integer.valueOf(s.substring(i, slash));
            res.add(s.substring(slash + 1, slash + size + 1));
            i = slash + size + 1;
        }
        return res;
    }
    
    
    // Encodes a list of strings to a single string.
    public String encode2(List<String> strs) {
        if (strs == null || strs.size() < 1) return "";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strs.size(); i++) {
            String s = strs.get(i).replaceAll("#", "##");
            sb.append(s).append(" # ");
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode2(String s) {
        List<String> res = new ArrayList<>();
        if (s == null || s.length() < 1) return res;
       
        String[] strs = s.split(" # ",-1);
        for(int i = 0; i< strs.length -1; i++) {
            res.add(strs[i].replace("##", "#"));
        }
        return res;
    }
}
