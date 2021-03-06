package hatecode._1000_1999;
public class _1067DigitCountInRange {
/*
1067. Digit Count in Range
233.
Given an integer d between 0 and 9, and two positive integers low and high as lower and upper bounds, respectively. Return the number of times that d occurs as a digit in all integers between low and high, including the bounds low and high.
 

Example 1:

Input: d = 1, low = 1, high = 13
Output: 6
*/
    //thinking process:
    //given digit d, integer low and high, from [low, hight], how many
    //times d appear
    
    //so 233 has solutin for 1-n, so here we 
    //first to know for d, how many times occurred for 1-low, then 
    //1-high, we substract former, 
    //
    public int digitsCount(int d, int low, int high) {
        return countDigit(high, d) - countDigit(low-1, d);
    }
    
    //1-n， how many times d occurred
    private int countDigit(int n, int d) {
        if(n < 0 || n < d) {
            return 0;
        }
        
        int count = 0;
        for(long i = 1; i <= n; i*= 10) {
            long divider = i * 10;
            //d= 2, n = 31452, divider = 10, then 
            //then we will have 3145 times of 2 showed at last digit
            count += (n / divider) * i;
            //d = 0 or d > 0
            if (d > 0) {
                //n % divider means the rest, like 
                //31452 % 10 = 2, last digit, we want to see 
                //in this position whether it exceed d * i or not.
                //for example if 31420, we cannot see 3 at 2 position, +1 is
                //because it showed once, only substracting will be 0.
                // comment1: tailing number need to be large than d *  i 
                //to qualify.
                count += Math.min(Math.max(n % divider - d * i + 1, 0), 
                                  i); 
            } else {
                //means n has digit on divider(biggest pisition)
                if(n / divider > 0) {
                    // comment2: when d == 0, we need avoid to take 
                    //numbers like 0xxxx into account.
                    if(i > 1) {  
                        count -= i;
                        count += Math.min(n % divider + 1, i);  
                    }
                }
            }
        }
        
        return count;
    }
}