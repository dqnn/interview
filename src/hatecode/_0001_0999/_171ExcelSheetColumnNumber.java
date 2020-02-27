package hatecode._0001_0999;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * ExcelSheetColumnNumber Creator : duqiang 
 * Date : Oct, 2017 Description : 
 * 171.
 * Excel Sheet Column Number Given a column title as appear in an Excel sheet,
 * return its corresponding column number.
 * 
 * 
 * 
 * 
 * 
 * 
 * For example:
 * 
 * A -> 1 B -> 2 C -> 3 ... Z -> 26 AA -> 27 AB -> 28 ... Example 1:
 * 
 * Input: "A" Output: 1 Example 2:
 * 
 * Input: "AB" Output: 28 Example 3:
 * 
 * Input: "ZY" Output: 701
 */
public class _171ExcelSheetColumnNumber {
    /**
     * For example:

     A -> 1
     B -> 2
     C -> 3
     ...
     Z -> 26
     AA -> 27
     AB -> 28

     AB -> 28
     res = 1 * 26 + 2 = 28

     time : O(n)
     space : O(1)

     * @param s
     * @return
     */
    // 26 进制
    public int titleToNumber(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int res = 0;
        for(int i = 0; i < s.length(); i++) {
            // so we have to + 1 because A is 1 not 0;
            res = res * 26 + s.charAt(i) - 'A' + 1;
            
        }
        return res;
    }
}
