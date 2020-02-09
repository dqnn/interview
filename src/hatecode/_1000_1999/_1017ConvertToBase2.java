package hatecode._1000_1999;
public class _1017ConvertToBase2 {
/*
1017. Convert to Base -2
Given a number N, return a string consisting of "0"s and "1"s 
that represents its value in base -2 (negative two).

The returned string must have no leading zeroes, unless the 
string is "0".

 

Example 1:

Input: 2
Output: "110"
Explantion: (-2) ^ 2 + (-2) ^ 1 = 2
*/
    //thinking process: 
    public String baseNeg2(int N) {
        String res = "";
        while (N != 0) {
            int rem = N % -2;
            N /= -2;
            if (rem < 0) {
                rem += 2;
                N += 1;
            }
            res = rem + res;
        }
        return res.equals("") ? "0" : res;
    }
}