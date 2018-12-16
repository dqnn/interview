package hatecode;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ExcelSheetColumnTitle
 * Creator : duqiang
 * Date : Oct, 2017
 * Description : 168. Excel Sheet Column Title
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
Example 1:

Input: 1
Output: "A"
Example 2:

Input: 28
Output: "AB"
Example 3:

Input: 701
Output: "ZY"

 */
public class ExcelSheetColumnTitle {
    /**
     * 1 -> A
     2 -> B
     3 -> C
     ...
     26 -> Z
     27 -> AA
     28 -> AB

     time : O(n)
     space : O(n)

     * @param n
     * @return
     */
    /*
     * As per the requirement, 1 -> A, 2 -> B... But here in Ascii reminder is added
     * to build the output, 0+65 = A, 1+65 = B, etc. Results will be saved using
     * reminder. So to reduce that 1 char gap, (n-1) was used.
     * 
     * Let's say for 52. It gives reminder, (n-1)%26 => (51%26) = 25 and in next
     * interation it gives 0, which makes AZ. But just keeping n to n, (n)%26 =>
     * (52%26) reminder would be 0 and 2 in next iteration which gives undesirable
     * results in the end (CA)
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            // here is the tricky
            n--;
            sb.append((char)('A' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }
}
