package hatecode._0001_0999;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : ValidNumber
 * Creator : professorX
 * Date : Sep, 2018
 * Description : TODO
 */
public class _065ValidNumber {

    /**
     * 65. Valid Number
     * Validate if a given string is numeric.

     Some examples:
     "0" => true
     " 0.1 " => true
     "abc" => false
     "1 a" => false
     "2e10" => true

     time : O(n);
     space : O(1);
     * @param s
     * @return
     */
    //thinking process:
    // the problem is validate a string is numberic, like 0, 0.1, 2e10, -10, +10
    //only these types
    // so special case is point, e and number
    // 
    public static boolean isNumber(String s) {
        s = s.trim();
        boolean numberSeen = false;
        boolean dotSeen = false;
        boolean eSeen = false;
        boolean numberAfterE = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // "223"
            if (c >= '0' && c <= '9') {
                numberSeen = true;
                numberAfterE = true;
            //e.3 or ..2
            } else if (c == '.') {
                if (eSeen || dotSeen) {
                    return false;
                }
                dotSeen = true;
            //ee  ae
            } else if (c == 'e') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                eSeen = true;
                numberAfterE = false;
            //+-2, 
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            //all othe charcters
            } else return false;
        }
        return numberSeen && numberAfterE;
    }


    public static void main(String[] args) {

        //valid 

        System.out.println(isNumber("1e3"));
        System.out.println(isNumber("4."));
        System.out.println(isNumber("+3.14"));

         System.out.println(isNumber("a"));
         
    }
}
