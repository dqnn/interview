package hatecode._0001_0999;
import java.util.*;
public class NextClosestTime {
/*
 * 681. Next Closest Time
Given a time represented in the format "HH:MM", form the next closest time by 
reusing the current digits. 
There is no limit on how many times a digit can be reused.

You may assume the given input string is always valid. For example, "01:34", 
"12:09" are all valid. "1:34", "12:9" are all invalid.

Example 1:

Input: "19:34"
Output: "19:39"
Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39, 
which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 
59 minutes later.
 */
    static int diff = Integer.MAX_VALUE;
    static String result = "";
    //give a timestamp, find the next closest time, each digit can be re-usable
    
    //so one thinking is covert to minutes, then each minute + 1, and validate they are in 
    //the original time set numbers and if it is there, then it is the answer, but the 
    //computation is 24* 60 = 1440
    
    //one improve is since digits can be re-used and has to be in original set, so we can 
    //use this to construct the time, we can put the digits in a list as digits below, using 
    //dfs to visit each one, after we construct the digits, we compare each one with target,
    //the min diff will be answer.  the compute will be 4*4*4*4 = 256 times. 
    
    //one more improvement regards this is to prune some number which is not invalid timestamp
    //like below bfs 
    public static String nextClosestTime(String time) {
        Set<Integer> set = new HashSet<>();
        set.add(Integer.parseInt(time.substring(0, 1)));
        set.add(Integer.parseInt(time.substring(1, 2)));
        set.add(Integer.parseInt(time.substring(3, 4)));
        set.add(Integer.parseInt(time.substring(4, 5)));
        //if they are the time, so next closet is 24h, then just return it
        if (set.size() == 1) return time;
        
        List<Integer> digits = new ArrayList<>(set);
        //change t0 minute so we can calc the diff
        int minute = Integer.parseInt(time.substring(0, 2)) * 60 + 
                Integer.parseInt(time.substring(3, 5));

        helper(digits, "", 0, minute);
        return result;
    }
    //cur is like "1223", no ":"
    private static void helper(List<Integer> digits, String cur, int pos, int target) {
        //we do not need pos > 4 because all will be merged here and all returned
        if (pos == 4) {
            int m = Integer.parseInt(cur.substring(0, 2)) * 60 
                    + Integer.parseInt(cur.substring(2, 4));
            if (m == target) return;
            //calc the diff, 
            int d = m - target > 0 ? m - target : 1440 + m - target;
            if (d < diff) {
                diff = d;
                result = cur.substring(0, 2) + ":" + cur.substring(2, 4);
            }
            return;
        }
        //in this templates, all digits are re-usable
        for (int i = 0; i < digits.size(); i++) {
            //prune some timestamp, so this is the t
            if (pos == 0 && digits.get(i) > 2) continue;
            if (pos == 1 && Integer.parseInt(cur) * 10 + digits.get(i) > 23) continue;
            if (pos == 2 && digits.get(i) > 5) continue;
            if (pos == 3 && Integer.parseInt(cur.substring(2)) * 10 + digits.get(i) > 59) continue;
            helper(digits, cur + digits.get(i), pos + 1, target);
        }
    }
    //one digit by digit
    public String nextClosestTime2(String time) {
        char[] result = time.toCharArray();
        char[] digits = new char[] {result[0], result[1], result[3], result[4]};
        Arrays.sort(digits);
        
        // find next digit for HH:M_
        result[4] = findNext(result[4], (char)('9' + 1), digits);  // no upperLimit for this digit, i.e. 0-9
        if(result[4] > time.charAt(4)) return String.valueOf(result);  // e.g. 23:43 -> 23:44
        
        // find next digit for HH:_M
        result[3] = findNext(result[3], '5', digits);
        if(result[3] > time.charAt(3)) return String.valueOf(result);  // e.g. 14:29 -> 14:41
        
        // find next digit for H_:MM
        result[1] = result[0] == '2' ? findNext(result[1], '3', digits) : findNext(result[1], (char)('9' + 1), digits);
        if(result[1] > time.charAt(1)) return String.valueOf(result);  // e.g. 02:37 -> 03:00 
        
        // find next digit for _H:MM
        result[0] = findNext(result[0], '2', digits);
        return String.valueOf(result);  // e.g. 19:59 -> 11:11
    }
    
    /** 
     * find the next bigger digit which is no more than upperLimit. 
     * If no such digit exists in digits[], return the minimum one i.e. digits[0]
     * @param current the current digit
     * @param upperLimit the maximum possible value for current digit
     * @param digits[] the sorted digits array
     * @return 
     */
    private char findNext(char current, char upperLimit, char[] digits) {
        //System.out.println(current);
        if(current == upperLimit) {
            return digits[0];
        }
        int pos = Arrays.binarySearch(digits, current) + 1;
        while(pos < 4 && (digits[pos] > upperLimit || digits[pos] == current)) { // traverse one by one to find next greater digit
            pos++;
        }
        return pos == 4 ? digits[0] : digits[pos];
    }
    
    public static void main(String[] args) {
        String in = "10:12";
        System.out.println(nextClosestTime(in));
        
    }
}