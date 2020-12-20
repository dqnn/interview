package hatecode._1000_1999;
public class _1694ReformatPhoneNumbe {
/*
1694. Reformat Phone Number
You are given a phone number as a string number. number consists of digits, spaces ' ', and/or dashes '-'.

You would like to reformat the phone number in a certain manner. Firstly, remove all spaces and dashes. Then, group the digits from left to right into blocks of length 3 until there are 4 or fewer digits. The final digits are then grouped as follows:

2 digits: A single block of length 2.
3 digits: A single block of length 3.
4 digits: Two blocks of length 2 each.
The blocks are then joined by dashes. Notice that the reformatting process should never produce any blocks of length 1 and produce at most two blocks of length 2.

Return the phone number after formatting.

 

Example 1:

Input: number = "1-23-45 6"
Output: "123-456"
*/
    
    // this problem is not worth time, just reading the problem carefully
    
    //it means if string length > 6, then group 3 chars first
    public String reformatNumber(String s) {
        if (s == null || s.length() < 1) return "";
        
        s = s.replaceAll("-", "");
        s = s.replaceAll(" ", "");
        
        StringBuilder sb = new StringBuilder();
        int n = s.length(), cur = 0;
        
        while (n > 4) {
            sb.append(s.substring(cur, cur+3));
            sb.append('-');
            cur += 3;
            n -= 3;
        }

        //System.out.println(n);
        if(n == 3 || n == 2) {
             sb.append(s.substring(s.length() - n));
        } else {
            sb.append(s.substring(s.length()-4, s.length()-2));
            sb.append("-");
            sb.append(s.substring(s.length()-2));
        }
        
        return sb.toString();
    }
}