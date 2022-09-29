package hatecode._0001_0999;

/**
 * Project Name : Leetcode Package Name : 
leetcode File Name : CountandSay
 * Creator : professorX Date : Ang, 2018 Description : 
 * 38. Count and Say
 */

/*
 * * The count-and-say sequence is the sequence of integers with the first five
 * terms as following:
 * 
 * 
 * 
 * 1. 1
 * 
 * 2. 11
 * 
 * 3. 21
 * 
 * 4. 1211
 * 
 * 5. 111221
 * 
 * 
 * 1 is read off as "one 1" or 11. 11 is read off as "two 1s" or 21. 21 is read
 * off as "one 2, then one 1" or 1211. Given an integer n, generate the nth term
 * of the count-and-say sequence.
 * 
 * Note: Each term of the sequence of integers will be represented as a string.
 * 
 * Example 1:
 * 
 * Input: 1 Output: "1" Example 2:
 * 
 * Input: 4 Output: "1211"
 */



public class _038CountandSay {
    /**
     1.     1
     2.     11
     3.     21
     4.     1211
     5.     111221

     time : 不知道
     space : O(n)

     * @param n
     * @return
     */
    // so this problem essentially:
    // you need to understand countAndSay2(n) is the read off to 
    //countAndSay2(n -1).
    // from example we can see
    // 3 is 21,
    // so 4 is the "read off" to 3, 1211. 12--> 2, 11-->1, we can split the problem
    // 1 get the previous number digits and then read off again, and return.
    public String countAndSay(int n) {
        if(n < 1) return "";
        if(n == 1) return "1";
        else if(n == 2) return "11";
        else if(n == 3) return "21";
        else if(n == 4) return "1211";
        else if(n == 5) return "111221";
        
        String prev = countAndSay(n-1);
        //for 111221 case, if we do not append F, then last 11 wil be missed
        prev += "F";
        int count = 1;
        char ch = prev.charAt(0);
        StringBuilder sb = new StringBuilder();
        for(int i =1; i<prev.length(); i++) {
            if (prev.charAt(i-1) != prev.charAt(i)) {
                sb.append(count + "" + ch);
                count = 1;
                ch =  prev.charAt(i);
            } else count++;
        }
        
        return sb.toString();
    }
}
