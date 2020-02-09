package hatecode._0001_0999;
public class ValidPalindromeII {
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
    public boolean isPalindromeRange(String s, int j) {
        s = s.substring(0, j) + s.substring(j+1);
        int right = s.length() - 1, left = 0;
        while(left <= right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
       return true;
   }
   public boolean validPalindrome2(String s) {
       int r = s.length() - 1, l = 0;
       while(l <= r) {
           if (s.charAt(l) != s.charAt(r)) {
               return (isPalindromeRange(s, l) ||
                       isPalindromeRange(s, r));
           } else {
               l++;
               r--;
           }
       }
       return true;
   }
}