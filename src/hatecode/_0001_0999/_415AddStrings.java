package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : AddStrings
 * Creator : professorX
 * Date : Nov, 2017
 * Description : 415. Add Strings
 */
public class _415AddStrings {
    /**
     * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

     Note:

     The length of both num1 and num2 is < 5100.
     Both num1 and num2 contains only digits 0-9.
     Both num1 and num2 does not contain any leading zero.
     You must not use any built-in BigInteger library or convert the inputs to integer directly.

     time : O(n + m)
     space : O(n)

     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0 || carry == 1) {
            int a = i >= 0 ? num1.charAt(i--) - '0' : 0;
            int b = j >= 0 ? num2.charAt(j--) - '0' : 0;
            int sum = a + b + carry;
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }
    
    //put carry our of while
    public String addStrings_easier_to_understand(String A, String B) {
        int a = A.length() -1, b = B.length()-1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while(a >= 0 || b>=0) {
            carry += a>=0 ? A.charAt(a) - '0' : 0;
            carry += b>=0 ? B.charAt(b) - '0' : 0;
            
            sb.insert(0, carry%10);
            carry = carry /10;
            a--;
            b--;
        }
        
        if (carry > 0) sb.insert(0, carry);
        
        return sb.toString();
    }
}
