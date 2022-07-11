package hatecode._0001_0999;
public class _680ValidPalindromeII {
 /*680. Valid Palindrome II
  * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example 1:
Input: "aba"
Output: True
  */
    //recursive solution, better for readability
    int count = 1;
    public boolean validPalindrome(String s) {
        if (s == null || s.length() < 1) return true;
        int right = s.length() - 1, left = 0;
        while(left <= right) {
            if (s.charAt(left) != s.charAt(right)){
                if (count-- < 1) return false;
                return validPalindrome(s.substring(0, left) + s.substring(left + 1))
                    || validPalindrome(s.substring(0, right) + s.substring(right + 1));
            } else {
                left ++;
                right--;
            }
        }
        return true;
    }
    
    //interview friendly: O(n) O(1)
    public boolean validPalindrome_Best(String s) {
            int l = 0, r = s.length() - 1;
            
            while (l < r) {
                if (s.charAt(l) == s.charAt(r)) {
                    l++; 
                    r--;
                } else {
                    return helper(s, l+1, r) || helper(s, l, r-1);
                }
            }
            
            return true;
        }
        
        private boolean helper(String s, int l, int r) {
            while(l < r) {
                if (s.charAt(l) == s.charAt(r)) {
                    l++; 
                    r--;
                } else return false;
            }
            
            return true;
        }
   
   //here to say if we want to remove most K characters
   boolean validPalindromeForRemovingKChars(String s, int k) {
       if (s.length() <= 1) {
           return true;
       }

       //always compare first to last character
       while (s.charAt(0) == s.charAt(s.length()-1)) {
           s = s.substring(1, s.length()-1);

           if (s.length() <= 1) {
               return true;
           }
       }

       if (k == 0) {
           return false;
       }

       // Try to remove the first or last character
       return validPalindromeForRemovingKChars(s.substring(0, s.length() - 1), k - 1) || 
              validPalindromeForRemovingKChars(s.substring(1, s.length()), k - 1);
   }
   
   
   //another way of removing K characters
   boolean validPalindromeForRemovingKChars_2(String s, int k) {
       if (s.length() <= 1) {
           return true;
       }

       int l = 0, r =s.length() - 1;
       while (l < r) {
           if (s.charAt(l) == s.charAt(r)) {
               l++; 
               r--;
               continue;
           }
       }

       if (k == 0) {
           return false;
       }

       // Try to remove the first or last character
       return validPalindromeForRemovingKChars(s.substring(l+1, r+1), k - 1) || 
              validPalindromeForRemovingKChars(s.substring(l, r), k - 1);
   }
}