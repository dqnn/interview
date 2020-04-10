package hatecode._1000_1999;
public class _1154DayOfTheYear {
/*
1154. Day of the Year
Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.

 

Example 1:

Input: date = "2019-01-09"
Output: 9
*/
    public int dayOfYear(String date) {
        int y=Integer.parseInt(date.substring(0, 4));
        int m=Integer.parseInt(date.substring(5, 7));
        int res=Integer.parseInt(date.substring(8));
        if(y%4==0&&y%100!=0&&m>2&&res!=29) res++;
        int[] days={31,28,31,30,31,30,31,31,30,31,30,31};
        for(int i=0;i<m-1;i++) res+=days[i];
        return res;
    }
}