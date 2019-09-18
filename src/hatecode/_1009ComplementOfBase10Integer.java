package hatecode;
public class _1009ComplementOfBase10Integer {
/*
1009. Complement of Base 10 Integer
Every non-negative integer N has a binary representation.  
For example, 5 can be represented as "101" in binary, 
11 as "1011" in binary, and so on.  
Note that except for N = 0, there are no leading zeroes in 
any binary representation.

The complement of a binary representation is the number in 
binary you get when changing every 1 to a 0 and 0 to a 1.  
For example, the complement of "101" in binary is "010" in binary.

For a given number N in base-10, return the complement of 
it's binary representation as a base-10 integer.
Example 1:

Input: 5
Output: 2
*/
    //thinking process:
    //given one integer, like 5->101, we 1->0, 0->1, so 5->101->010,
    //then we return 2
    
    //we change 5->bianry string first, then we just calculate the results
    public int bitwiseComplement(int n) {
        if (n == 0) return 1;
        StringBuilder sb = new StringBuilder();
        while(n !=0) {
            sb.append(n % 2);
            n = n /2;
        }
        String str = sb.reverse().toString();
        //System.out.println(str);
        int res = 0;
        for(int i =0; i< str.length();i++) {
            res = 2 * res +  (str.charAt(i) == '0' ? 1 : 0);
        }
        return res;
    }
}