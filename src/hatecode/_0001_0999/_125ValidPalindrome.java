package hatecode._0001_0999;

/**
 * Created by duqiang on 28/07/2017.
 */
public class _125ValidPalindrome {
    /**
     * 125. Valid Palindrome
     * Given a string, determine if it is a palindrome, considering only alphanumeric 
     * characters and ignoring cases.

     For example,
     "A man, a plan, a canal: Panama" is a palindrome.
     "race a car" is not a palindrome.

     Note:
     Have you consider that the string might be empty? This is a good question to ask during an interview.

     For the purpose of this problem, we define empty string as valid palindrome.

     case : "A man, a plan, a canal: Panama"

     time : O(n)
     space : O(1);

     * @param s
     * @return
     */
    
    //thinking process:
    // the problem is to validate a string is alphanumeric and ignore cases, 
    //like space, comma
    // first we want to compare from both sides, we can try two pointers, 
    
    //so if we encounter char which is not letter or digits, we just left++,
    //and if right not, we can right--.
    //so we compare each character if they are not equals, we return false, if true, we continue
    //at last, left++ and right--;
    //simplifed version, but they are the same, note if we want to use while we need to add 
    //while(l < r)
    public boolean isPalindrome_simplifed(String s) {
        if (s == null || s.length() < 1) return true;
        int l = 0, r = s.length() -1;
        while(l < r) {
            if (!Character.isLetterOrDigit(s.charAt(l))) {
                l++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(r))) {
                r--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(l++)) != Character.toLowerCase(s.charAt(r--))) return false;
        }
        return true;
    }
    
    
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            //move pointer to right if not character
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            //move pointer to left if not character 
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            //test if they match
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
    
    
    //another solutions
    private static final char[]charMap = new char[256];
    static{
        for(int i=0;i<10;i++){
            charMap[i+'0'] = (char)(1+i);  // numeric
        }
        for(int i=0;i<26;i++){
            charMap[i+'a'] = charMap[i+'A'] = (char)(11+i);  //alphabetic, ignore cases
        }
    }
    public boolean isPalindrome2(String s) {
        char[]pChars = s.toCharArray();
        int start = 0,end=pChars.length-1;
        char cS,cE;
        while(start<end){
            cS = charMap[pChars[start]];
            cE = charMap[pChars[end]];
            if(cS!=0 && cE!=0){
                if(cS!=cE)return false;
                start++;
                end--;
            }else{
                if(cS==0)start++;
                if(cE==0)end--;
            }
        }
        return true;
    }
}
