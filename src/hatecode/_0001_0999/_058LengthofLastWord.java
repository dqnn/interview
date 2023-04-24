package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : LengthofLastWord
 * Creator : professorX
 * Date : Oct, 2017
 * Description : 58. Length of Last Word
 */
public class _058LengthofLastWord {
    /**
     * For example,
     Given s = "Hello World",
     return 5.

     time : O(1)
     space : O(1)

     * @param s
     * @return
     */
    public int lengthOfLastWord2(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;
    }
    
    // interview friendly one
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        
        char[] chs = s.toCharArray();
        int i = chs.length - 1;
        //scan from right to find first non " " character, this is exclude non spaces in the end
        while (i >= 0 && chs[i] ==' ') {
            i--;
        }
        // we continue to find another space so we can visit whole last word
        int j = i;
        while (j >= 0 && chs[j] !=' ') {
            j--;
        }
        
        return i - j;
    }
    
    public int lengthOfLastWord3(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        
        String[] strs = s.trim().split(" ");
        return strs[strs.length - 1].length();
    }

    public int lengthOfLastWord_Simple(String s) {
        int n  = s.length()-1;
        
        while( n>=0 && s.charAt(n) == ' '){
            n--;
        }
        
        int res = 0;
        
        while(n >= 0 && s.charAt(n) != ' '){
            res++;
            n--;
        }
        
        return res;
    }
}
