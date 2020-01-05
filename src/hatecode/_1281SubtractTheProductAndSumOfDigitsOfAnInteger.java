package hatecode;
public class _1281SubtractTheProductAndSumOfDigitsOfAnInteger {
/*
1281. Subtract the Product and Sum of Digits of an Integer
Given an integer number n, return the difference between the product of its digits and the sum of its digits.
 

Example 1:

Input: n = 234
Output: 15 
Explanation: 
Product of digits = 2 * 3 * 4 = 24 
Sum of digits = 2 + 3 + 4 = 9 
Result = 24 - 9 = 15
*/
    
    //thinking process: O(lgn)/O(1)
    
    //given an integer, return its multiple each digit and sum of all digits
    
    //key is how to retrieve each digit in the integer, 
    public int subtractProductAndSum(int n) {
        int sum = 0, product = 1;
        while (n > 0) {
            sum += n % 10;
            product *= n % 10;
            n /= 10;
        }
        return product - sum;
    }
    
    
    public int subtractProductAndSum_BF(int n) {
        String str = String.valueOf(n);
        int sum = 0, mul = 1;
        for(int i =0; i<str.length(); i++) {
            int k = str.charAt(i) - '0';
            sum += k;
            mul *= k;
        }
        
        return mul - sum;
    }
    
    
}