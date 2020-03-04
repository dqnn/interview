package hatecode._0001_0999;
import java.util.*;
public class _402RemoveKDigits {
/*
402. Remove K Digits
given a non-negative integer num represented as a string, remove k digits 
from the number so that the new number is the smallest possible.

Note:
The length of num is less than 10002 and will be ≥ k.
The given num does not contain any leading zero.
Example 1:

Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new 
number 1219 which is the smallest.
Example 2:

Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. 
Note that the output must not contain leading zeroes.
*/
    
    //O(n)
    public String removeKdigits(String num, int k) {
        int digits = num.length() - k;
        char[] stk = new char[num.length()];
        int top = 0;
        // k keeps track of how many characters we can remove
        // if the previous character in stk is larger than the current one
        // then removing it will get a smaller number
        // but we can only do so when k is larger than 0
        for (int i = 0; i < num.length(); ++i) {
            char c = num.charAt(i);
            while (top > 0 && stk[top-1] > c && k > 0) {
                top -= 1;
                k -= 1;
            }
            stk[top++] = c;
        }
        // find the index of first non-zero digit
        int idx = 0;
        while (idx < digits && stk[idx] == '0') idx++;
        return idx == digits? "0": new String(stk, idx, digits - idx);
    }
    
    //give a string and remove K digits, so the left could be smallest, thinking problem from 
    //two perspetive, one is how to get smallest K digits, for example, if i remove one, i think 
    //it is max one, because the number length is the same no matter which one i changed, but it may
    //be different that second one is a 0. 
    // so the correct way is to remove digits which is bigger than its right.so we can always 
    //to get the smallest, this is the pattern
    public static String removeKdigits2(String num, int k) {
        int len = num.length();
        // corner case
        if (k == len)
            return "0";

        Stack<Character> stack = new Stack<>();
        int i = 0;
        //1432219-->14, when we push 3, we found 3 < 4. so stack pop until 1 then 
        //if we continue doing this, which means we will remove max k digits, 
        //stack help to keep the order of the chars in string, so after this while loop,
        //we already removed max j digits, we cannot remove digits they are the same, so 
        //we need another loop to remove the rest then we can reverse them
        while (i < num.length()) {
            // whenever meet a digit which is less than the previous digit, discard the
            // previous one
            while (k > 0 && !stack.isEmpty() && stack.peek() > num.charAt(i)) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }
        // corner case like "1111"
        while (k > 0) {
            stack.pop();
            k--;
        }
        // construct the number from the stack
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty())
            sb.append(stack.pop());
        sb.reverse();
        // remove all the 0 at the head
        while (sb.length() > 1 && sb.charAt(0) == '0')
            sb.deleteCharAt(0);
        return sb.toString();
    }
    
    public static void main(String[]args) {
        String in = "1432211119";
        System.out.println(removeKdigits2(in, 5));
    }
}