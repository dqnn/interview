package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : MultiplyStrings
 * Date : Aug, 2018
 * Description : 43. Multiply Strings
 */
public class _043MultiplyStrings {
    /**
     * tags: facebook
     * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

     Note:

     The length of both num1 and num2 is < 110.
     Both num1 and num2 contains only digits 0-9.
     Both num1 and num2 does not contain any leading zero.
     You must not use any built-in BigInteger library or convert the inputs to integer directly.

     time : O(n * m)
     space : O(n + m)

     * @param num1
     * @param num2
     * @return
     */
    //thinking process:
    //the problem is to say: given two strings integers, multiple them and return.
    
    //each multiple can be assembled by plus, look at examples, we use an array to store
    //all the sum which by each digit multiple, 
    //for example: A[i] * B[j], the sum as a factor must be in the result, and we just need to know
    //how we put the contribution, since it is just one digit * another digit, so it must be 
    //2 digit at most, and the position should be i+j and i+j+1 if it has carrier. 
    //another key is to note: A[i] * B[j] mapping to digits array
    //we cannot use convert * to +, 
    //the key is A[i] * B[j] will be placed into [i+j, i+j+1]
    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return "0";
        int[] digits = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            for (int j = num2.length() - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                //this is the key,
 /*
  *     1   2   3   B[j]
  *         4   5   A[i]
  *  --------------------
  *         1  5
  *      1  0
  *   0  5
  * ---------------- 
  *      1  2
  *   0  8
  * 0 4
  * ------------------
    0 5  5   3  5
  [ 0,1  2, 3, 4]
  *  digit[A.length() + B.length()]
  *  we can see A[1] = 2, B[0] = 5, product = 10, then the smallest result starts from 0 at i + j + 1, 
  *  the righter, the smaller, 
  */            
                //note i+j+1 is the smaller one
                int p1 = i + j, p2 = i + j + 1;
                //add previous value of digits[p2], p2 is smaller significant position, so sum here is real
                int sum = product + digits[p2];
                // incremental, we need to add previous value 
                digits[p1] += sum / 10;
                // real value in the smaller position, note we do not have + because we only visite the smaller position once
                //it already finalized
                digits[p2] = sum % 10;
            }
        }
        
        //two exception case:
        //1 leading 0.
        //2 result is 0
        StringBuilder sb = new StringBuilder();
        for (int digit : digits) {
            //skip leading 0
            //"12" x "34" = "0408", skip first 0 only
            if(digit !=0 ||sb.length() != 0) sb.append(digit);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }
    
    
    public static void main(String[] args) {
        System.out.println(multiply("10", "10"));
    }
}
