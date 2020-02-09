package hatecode._0001_0999;

import java.util.Random;

/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
public class ImplementRand10UsingRand7 {
/*
470. Implement Rand10() Using Rand7()
Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.

Do NOT use system's Math.random().

 

Example 1:

Input: 1
Output: [7]
*/
    //the key is k * randk can help expand the result, while randk + k have some overlap in result space
    //so we cannot use +, k * rank can help to expand the space while keep same probability. 
    //
    public int rand10() {
        while(true) {
            int res = 7 * (rand7() - 1) + rand7();
            if (res <= 40) return res % 10 + 1;
        }
    }
    
    private int rand7() {
        return new Random().nextInt(7) + 1;
    }
}