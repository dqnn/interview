package hatecode._1000_1999;
public class _1344AngleBetweenHandsofAClock {
/*
1344. Angle Between Hands of a Clock
Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.

 

Example 1:


Input: hour = 12, minutes = 30
Output: 165
*/
    
    //thinking process: O(1)/O(1)
    
    //the problem is to say: given hour and minutes integer, return 
    //the angle(smaller one) between minute hand and hour hand
    
    //
    public double angleClock(int hour, int minutes) {
        
         // Degree covered by hour hand (hour area + minutes area)
        double h = (hour%12 * 30) + ((double)minutes/60 * 30);
        
         // Degree covered by minute hand (Each minute = 6 degree)
        double m = minutes * 6;
        
         // Absolute angle between them
        double angle = Math.abs(m - h);
        
         // If the angle is obtuse (>180), convert it to acute (0<=x<=180)
        if (angle > 180) angle = 360.0 - angle;
        
        return angle;
    }
}