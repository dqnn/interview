package hatecode;
public class _991BrokenCalculator {
/*
991. Broken Calculator
On a broken calculator that has a number showing on its display, we can perform two operations:

Double: Multiply the number on the display by 2, or;
Decrement: Subtract 1 from the number on the display.
Initially, the calculator is displaying the number X.

Return the minimum number of operations needed to display the number Y.
Input: X = 2, Y = 3
Output: 2
*/
    //thinking process: math problem
    //the problem is to say: given two numbers x and y, we want to know 
    //the fewest steps from x->y, we either y-1 or x*2
  
    //the regular thinking is BFS, to find the shortest path
    //greedy solution, so if y > x, the fastest way is y = y /2, 
    //if not we make y+1, then next step is /2
    //y > x, then we want to 
    public int brokenCalc(int x, int y) {
        int res = 0;
        while(y > x) {
            //the tricky part is y+1, why?
            //
            y = y % 2 > 0 ? y+1 : y /2;
            res++;
        }
        //y < x already, so we want how many steps y need to substract 1
        //totally
        return res + x - y;
    }
    
    
    public int brokenCalc_reference(int X, int Y) {
        int cnt = 0;
        while (Y > X) {
            if ((Y & 1) == 1) {
                Y += 1;
                cnt++;
            } 
            Y >>= 1;
            cnt++;
        }
        return cnt + (X - Y);
    }
}