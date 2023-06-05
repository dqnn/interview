public class _2716MinimizeStringLength {
/*
2716. Minimize String Length

Given a 0-indexed string s, repeatedly perform the following operation any number of times:

Choose an index i in the string, and let c be the character in position i. Delete the closest occurrence of c to the left of i (if any) and the closest occurrence of c to the right of i (if any).
Your task is to minimize the length of s by performing the above operation any number of times.

Return an integer denoting the length of the minimized string.

 

Example 1:

Input: s = "aaabc"
Output: 3
*/
/*
just remove the same character no matter they are 
*/
    public int minimizedStringLength(String s) {
        if(s == null || s.length() <1) return 0 ;
        
        Set<Character> set = new HashSet<>();
        for(char c: s.toCharArray()) set.add(c);
        return set.size();
    }
}