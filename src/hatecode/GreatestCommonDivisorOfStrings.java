package hatecode;
public class GreatestCommonDivisorOfStrings {
/*
1071. Greatest Common Divisor of Strings
For strings S and T, we say "T divides S" if and only if S = T + ... + T  (T concatenated with itself 1 or more times)

Return the largest string X such that X divides str1 and X divides str2.
Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"
*/
    //thinking process:
    //so given two string str1 and str2, find the gcd of these two strings, if cannot, return ""
    
    //so the problem is extending the gcd concepts from numbers to strings,
    //ABABAB, ABAB, this return AB, so the gcd process is like to find the minimal strings/common
    //numbers, if we follow the gcd implementation, we use recursive until ld is 0, 
    //gcd(a,b){if (a==0) return b; return gcd(b %a, a)}
   public String gcdOfStrings(String str1, String str2) {
       // make sure str1 length is not shorter than str2.
        if (str1.length() < str2.length()) { return gcdOfStrings(str2, str1); } 
        else if (!str1.startsWith(str2)) { return ""; } // shorter string is not common prefix.
        else if (str2.isEmpty()) { return str1; } // gcd string found.
        else { return gcdOfStrings(str1.substring(str2.length()), str2); } // cut off the common prefix part of str1.
    }
}