package hatecode._0001_0999;
public class _880DecodedStringAtIndex {
/*
880. Decoded String at Index
An encoded string S is given.  To find and write the decoded string to a tape, the encoded string is read one character at a time and the following steps are taken:

If the character read is a letter, that letter is written onto the tape.
If the character read is a digit (say d), the entire current tape is repeatedly written d-1 more times in total.
Now for some encoded string S, and an index K, find and return the K-th letter (1 indexed) in the decoded string.

 

Example 1:

Input: S = "leet2code3", K = 10
Output: "o"
*/
    //thinking process: O(n)/O(1)
    
    //the problem is to say: given one string and one integer K, the string contains 
    //string and a digit, digit means the string will be dup how many times, then
    //return the K-th character.
    
    //brute force is we calculated the whole string and we count from first to Kth position.
    //but this will TLE.
    
    //then we start from the origin string, how can we be faster and less space?
    //we easily know the whole length, we do not need to know the whole string, then we visit 
    //back from end to head for this string, so they key is how we can identify the Kth char.
    //so when we visit back, suppose we have the result string, but we visit the original string
    //if we meet digit,then we divide the length, then we reduced the previos length, then
    //we should meet char, if K = totalLen after total -- and K /= total.
    
    //so we will meet each letter or digit in the original string, it will map to the result
    //string, but the map how we map is to use digit, and %
    public String decodeAtIndex(String str, int k) {
        //result string Length
        long totalLen =0 ;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                //repeat ch - ‘0’ - 1 times
                totalLen *= ch - '0';
            } else {
                totalLen++;
            }
        }
       //we start back visit the origin string, t
       for (int i = str.length()-1; i >= 0; --i) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                totalLen /= c - '0';
                //
                k %= totalLen;
            } else {
                //k maybe the last character; below also works
                //if (k == 0 || k == totalLen) {
                if(k % totalLen == 0) {
                    return Character.toString(c);
                }
                totalLen--;
            }
        }
        return "";
    }
    //this slights improved the time complexity
    public String decodeAtIndex_Improved(String str, int k) {
        long totalLen = 0;
        int i = 0;
        for (; totalLen < k; i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                totalLen *= ch - '0';
            } else {
                totalLen++;
            }
        }
        i--;
        for (int j = i; j >= 0; --j) {
            char c = str.charAt(j);
            if (Character.isDigit(c)) {
                totalLen /= c - '0';
                k %= totalLen;
            } else {
                if (k % totalLen == 0) {
                    return Character.toString(c);
                }
                totalLen--;
            }
        }
        return "";
    }
    
    //this is optimized
    public String decodeAtIndex2(String S, int K) {
        long N = 0L;
        int i;
        char[] chs = S.toCharArray();
        for (i = 0; N < K; i++) 
            N = chs[i] >= '0' && chs[i] <= '9' ? 
                    N*(chs[i] - '0') : N + 1;
        //we do not need to go so far
        i--;
        while (true){
            if (chs[i] >= '0' && chs[i] <= '9') {
                N /= chs[i] - '0';
                K %= N;
            } else if (K%N == 0) return "" + chs[i];
              else N--;
            
            i--;
        }
    }
}