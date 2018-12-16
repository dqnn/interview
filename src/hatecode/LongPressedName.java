package hatecode;
/*
Your friend is typing his name into a keyboard.  Sometimes, 
when typing a character c, the key might get long pressed, and the character 
will be typed 1 or more times.

You examine the typed characters of the keyboard.  Return True if it is possible that 
it was your friends name, with some characters (possibly none) being long pressed.

 

Example 1:

Input: name = "alex", typed = "aaleex"
Output: true
Explanation: 'a' and 'e' in 'alex' were long pres
Example 2:

Input: name = "saeed", typed = "ssaaedd"
Output: false
Explanation: 'e' must have been pressed twice, but it wasn't in the typed output.
 */
public class LongPressedName {
    
    //thinking process:
    // so the problem is to we type name to typed, we may type 1+ char for each in name, 
    //for example, alex-->aalleexx, 
    
    // two pointers based: 
    //we loop on typed,  nIdx pointer to name, j point to typed
    // if each char equals then we increase Idx, 
    // if not it would mean name pointer to a different char but j still the same
    // then next we compare name nIdx to typed j,  
    // and detect if typed i and i-1 not equals then we return false because 
  //j already moved to a place, like alex vs aalleex, nIdx points to 
    //l while j point to a,so j must equals to j - 1
    public boolean isLongPressedName(String name, String typed) {
        if (name == null && typed == null)
            return true;
        if (name == null || typed == null || name.length() > typed.length())
            return false;
        int nIdx = 0, m = name.length(), n = typed.length();
        //so we loop in typed string, longer ones
        for (int j = 0; j < n; j++)
            if (nIdx < m && name.charAt(nIdx) == typed.charAt(j))
                // nIdx move to next char
                nIdx++;
            else if (j == 0 || typed.charAt(j) != typed.charAt(j - 1))
                return false;
        return nIdx == m;
    }
    
    public boolean isLongPressedName2(String name, String typed) {
        if (name == null && typed == null)
            return true;
        if (name == null || typed == null || name.length() > typed.length())
            return false;
        // first detect first char
        if (name.charAt(0) != typed.charAt(0))
            return false;
        // name index
        int nid = 0;
        for (int i = 0; i < typed.length(); i++) {
            // if reached name last char
            if (nid == name.length()) {
                if (typed.charAt(i) != typed.charAt(i - 1))
                    return false;
                continue;
            }
            // name idx move to next
            if (name.charAt(nid) == typed.charAt(i)) {
                nid++;
                // typed i == i - 1, so we continue next char in typed
            } else if (typed.charAt(i) == typed.charAt(i - 1)) {
                continue;
            } else
                return false;
        }
        if (nid < name.length())
            return false;
        return true;
    }
}