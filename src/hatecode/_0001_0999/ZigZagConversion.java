package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ZigZagConversion
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 6. ZigZag Conversion
 */
public class ZigZagConversion {
/*
 * 
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
(you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I


     time : O(n);
     space : O(n);

     * @param s
     * @param numRows
     * @return
     */
    //thinking process
    
    //the problem is to covert String into Zig style with fixed row number
    
    // this is better solution
    public String convert(String s, int nRows) {
        if (nRows <= 1) return s;
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();
        
        int i = 0;
        //s = "PAYPALISHIRING", numRows = 4
        /*
         *P     I    N
          A   L S  I G
          Y A   H R
          P     I
         */
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }
    
    
    
    public String convert2(String s, int numRows) {
        if (numRows <= 1) return s;
        //each line to be stringBuilder
        StringBuilder[] sb = new StringBuilder[numRows];
        for (int i = 0; i < sb.length; i++) {
            sb[i] = new StringBuilder();
        }
        
        //
        for (int i = 0; i < s.length(); i++) {
            int index = i % (2 * numRows - 2);
            index = index < numRows ? index : 2 * numRows - 2 - index;
            sb[index].append(s.charAt(i));
        }
        for (int i = 1; i < sb.length; i++) {
            sb[0].append(sb[i]);
        }
        return sb[0].toString();
    }
}
