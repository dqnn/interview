package hatecode._0001_0999;
public class _806NumberOfLinesToWriteString {
/*
806. Number of Lines To Write String
We are to write the letters of a given string S, from left to right into lines. Each line has maximum width 100 units, and if writing a letter would cause the width of the line to exceed 100 units, it is written on the next line. We are given an array widths, an array where widths[0] is the width of 'a', widths[1] is the width of 'b', ..., and widths[25] is the width of 'z'.

Now answer two questions: how many lines have at least one character from S, and what is the width used by the last such line? Return your answer as an integer list of length 2.

 

Example :
Input: 
widths = [10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10]
S = "abcdefghijklmnopqrstuvwxyz"
Output: [3, 60]
*/
    //very preciously on each row to process line and cur
    public int[] numberOfLines(int[] widths, String s) {
        if (s == null || s.length() < 1) return new int[]{0,0};
        
        int line = 1, cur = 0; 
        for(char ch: s.toCharArray()) {
            int w = widths[ch - 'a'];
            line = cur + w > 100 ? line + 1 : line;
            cur = cur + w > 100 ? w : cur + w;
        }
        return new int[] {line, cur};
    }
}