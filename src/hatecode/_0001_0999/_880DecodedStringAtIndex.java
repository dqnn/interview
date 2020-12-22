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
    //thinking process: 
    public String decodeAtIndex(String str, int k) {
        long size =0 ;
        for(int i=0;i<str.length();i++) {
            char ch=str.charAt(i);
            if(Character.isDigit(ch)) {
                size*=ch-'0';
            } else {
                size++;
            }
        }
        
       for (int i = str.length()-1; i >= 0; --i) {
            char c = str.charAt(i);
            if (Character.isDigit(c)) {
                size /= c - '0';
                k %= size;
            } else {
                if(k==0 || k==size) {
                    return Character.toString(c);
                }
                size--;
            }
                
        }
        return "";
    }
}