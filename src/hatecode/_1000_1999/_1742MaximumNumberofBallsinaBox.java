package hatecode._1000_1999;

import java.util.*;
public class _1742MaximumNumberofBallsinaBox {
/*
1742. Maximum Number of Balls in a Box
You are working in a ball factory where you have n balls numbered from lowLimit up to highLimit inclusive (i.e., n == highLimit - lowLimit + 1), and an infinite number of boxes numbered from 1 to infinity.

Your job at this factory is to put each ball in the box with a number equal to the sum of digits of the ball's number. For example, the ball number 321 will be put in the box number 3 + 2 + 1 = 6 and the ball number 10 will be put in the box number 1 + 0 = 1.

Given two integers lowLimit and highLimit, return the number of balls in the box with the most balls.

 

Example 1:

Input: lowLimit = 1, highLimit = 10
Output: 2
*/
    /*
     *  thinking process: O(n)/O(n)
     *  
     *  the problem is to say: given l and h number, it means the ball number,
     *  then sum on each digit, sum will be the box number, return the box number which has max balls
     *  
     */
    public int countBalls(int l, int h) {
        if (l > h) return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int i = l; i<=h;i++) {
            
            int boxId = 0,  c = i;
            while(c > 0) {
                boxId += c %10;
                c = c/10;
            }
            map.put(boxId, map.getOrDefault(boxId, 0) + 1);
            res = Math.max(res, map.get(boxId));
        }
        
        return res;
    }
    
    public int countBalls_faster(int lowLimit, int highLimit) {
        int[] arr = new int[100];
        int offset = 50;
        arr[offset]++;

        for (int i = lowLimit + 1; i <= highLimit; i++) {
            offset++;
            int j = 10;
            while (i % j == 0) {
                offset -= 9;
                j *= 10;
            }
            arr[offset]++;
        }

        int ret = 0;
        for (int num : arr) {
            ret = Math.max(ret, num);
        }
        return ret;
    }
    
    public int countBalls_fastest_reference(int lowLimit, int highLimit) {
        int[] boxes = new int[46];              // Max of 9+9+9+9+9=45 possible digit sums.
        
        if (highLimit == 100_000) {             // Only 100,000 can have a digit in the 10^5 position.
            boxes[1] = 1;
            highLimit = 99_999;
        }
        
        int low0 = lowLimit % 10;               // Initial digit in the 10^0 (one's) position.
        int low1 = (lowLimit / 10) % 10;        // Initial digit in the 10^1 (ten's) position.
        int low2 = (lowLimit / 100) % 10;       // Initial digit in the 10^2 (hundred's) position.
        int low3 = (lowLimit / 1000) % 10;      // Initial digit in the 10^3 (thousand's) position.
        int low4 = (lowLimit / 10000) % 10;     // Initial digit in the 10^4 (ten thousand's) position.
        int count = highLimit - lowLimit + 1;   // Number of digit-sums to accumulate.
        for (int dig4 = low4; dig4 <= 9; dig4++) {                  // Loop for 10^4 digit position.
            int sum4 = dig4;
            for (int dig3 = low3; dig3 <= 9; dig3++) {              // Loop for 10^3 digit position.
                int sum3 = sum4 + dig3;
                for (int dig2 = low2; dig2 <= 9; dig2++) {          // Loop for 10^2 digit position.
                    int sum2 = sum3 + dig2;
                    for (int dig1 = low1; dig1 <= 9; dig1++) {      // Loop for 10^1 digit position.
                        int sum1 = sum2 + dig1;
                        for (int dig0 = low0; dig0 <= 9; dig0++) {  // Loop for 10^0 (one's) digit position.
                            boxes[sum1 + dig0]++;                   // Count another ball's digit sum in a box.
                            if (--count == 0)  return maxVal(boxes);// If done accumulating sums, then return max value in boxes[].
                        }
                        low0 = 0;               // Next time, digit in 10^0 position starts at zero.
                    }
                    low1 = 0;                   // Next time, digit in 10^1 position starts at zero.
                }
                low2 = 0;                       // Next time, digit in 10^2 position starts at zero.
            }
            low3 = 0;                           // Next time, digit in 10^3 position starts at zero.
        }
        return -1;                              // Should never get here.
    }
    
    
    private int maxVal(int[] arr) {
        int max = 0;
        for (int i = arr.length - 1; i >= 0; i--)
            max = Math.max(max, arr[i]);
        return max;
    }
}