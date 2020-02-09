package hatecode._1000_1999;
public class _1118NumberOfDaysInAMonth {
/*
1118. Number of Days in a Month
Given a year Y and a month M, return how many days there are in that month.

 

Example 1:

Input: Y = 1992, M = 7
Output: 31
*/
   private static final int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
   public int numberOfDays(int Y, int M) {
        return M == 2 && (Y % 4 == 0 && Y % 100 != 0 || Y % 400 == 0) ? 29 : days[M];
    }  
}