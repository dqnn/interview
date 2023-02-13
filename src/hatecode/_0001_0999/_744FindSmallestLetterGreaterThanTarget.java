package hatecode._0001_0999;

public class _744FindSmallestLetterGreaterThanTarget {
    /*
    744. Find Smallest Letter Greater Than Target
    You are given an array of characters letters that is sorted in non-decreasing order, and a character target. There are at least two different characters in letters.
    
    Return the smallest character in letters that is lexicographically greater than target. If such a character does not exist, return the first character in letters.
    
     
    
    Example 1:
    
    Input: letters = ["c","f","j"], target = "a"
    Output: "c"
    Input: letters = ["x","x","y","y"], target = "z"
    Output: "x"
    */
      
    /*
     * thingking process: O(lgn)/O(1)
     * the problem is to say: given one character array and one target character, 
     * return the least character which is greater than target, it not exist, then return first 
     */
        public char nextGreatestLetter(char[] chs, char t) {
            int n = chs.length;
            
            if (chs[n-1] <= t || chs[0] > t) return chs[0];
    
            int l = 0, r = n-1;
            
            while (l < r) {
                int m = l + (r - l)/2;
                if (chs[m] > t) r = m;
                else l = m + 1;
            }
            
            return chs[l];
        }
    }