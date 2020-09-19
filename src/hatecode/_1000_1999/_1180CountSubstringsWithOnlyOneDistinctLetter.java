package hatecode._1000_1999;
public class _1180CountSubstringsWithOnlyOneDistinctLetter {
/*
1180. Count Substrings with Only One Distinct Letter
Given a string S, return the number of substrings that have only one distinct letter.

 

Example 1:

Input: S = "aaaba"
Output: 8
Explanation: The substrings with one distinct letter are "aaa", "aa", "a", "b".
"aaa" occurs 1 time.
"aa" occurs 2 times.
"a" occurs 4 times.
"b" occurs 1 time.
So the answer is 1 + 2 + 4 + 1 = 8.
*/
    
    // thinking process: 
    //the problem is to say: give a string s, we want to know how many substring which 
    //only has 1 character, for example aaaba has a, aa(2 times), aaa etc
    
    //it has formula, aaa--> n(n+1)/2, n means how many times this letter repeats
    ///aaa repeated 3 times, so it will be 3*4/2 = 6, a on different position means 
    //different character
    //it is like C(n, 2),  another example:
    //"aaaa": 1 ("aa") + 2 ("aaa") + 3 ("aaaa") + "aaaa".size() = 6 + 4 = 10.
    //if there a 'b' then we need to stop, then count how many times previous char
    //repeated
    public int countLetters(String s) {
        if (s == null || s.length() < 1) return 0;
        
        int l =0, r =1;
        int res = 0;
        while(r <= s.length()) {
            if(r == s.length() || s.charAt(r-1) != s.charAt(r)) {
                res += (r-l + 1) * (r-l) / 2;
                l = r;
            }
            r++;
        }
        return res;
    }
    
    public int countLetters_simpler(String S) {
        int sum = 1;
        int currentCount = 1;
        //culumlative sum together, smart solution
        for(int i = 1; i < S.length(); i++){
            if(S.charAt(i) == S.charAt(i-1)){
                currentCount++;
                sum += currentCount;
            }else {
                currentCount = 1;
                sum += currentCount;
            }
        }
        
        return sum;
        
    }
}