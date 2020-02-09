package hatecode._0001_0999;
public class SoupServings {
/*
808. Soup Servings
There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:

Serve 100 ml of soup A and 0 ml of soup B
Serve 75 ml of soup A and 25 ml of soup B
Serve 50 ml of soup A and 50 ml of soup B
Serve 25 ml of soup A and 75 ml of soup B
When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.

Note that we do not have the operation where all 100 ml's of soup B are used first.  

Return the probability that soup A will be empty first, 
plus half the probability that A and B become empty at the same time.

 

Example:
Input: N = 50
Output: 0.625
*/
    //thinking process: given N volumn soup for A and B type, we have 4 operation to distribute soup, 
    //return the probability for A is empty first + B and A are empty at same time
    
    //quad tree is the background, 
    public double soupServings(int n) {
        //this is not easy find in interview, just for pass the OJ
        if (n >= 5000) return 1;
        //real work is here
        return helper(n, n, new Double[n +1][n +1]);
    }
    
    private double helper(int A, int B, Double[][] memo) {
        // case 1: empty same time, note:
        //half the probability that A and B become empty at the same time.
        if (A <= 0 && B <= 0) return 0.5; 
        // case 2: A empty first, one instance
        if (A <= 0) return 1.0;
        // case 3 A empty first
        if (B <= 0) return 0.0;
        if (memo[A][B] != null) return memo[A][B];

        int[] proA = {100, 75, 50, 25};
        int[] proB = {0, 25, 50, 75 };
        memo[A][B] = 0.0;
        for (int i = 0; i < 4; i++) {
            memo[A][B] += helper(A - proA[i], B - proB[i], memo);
        }
        //we multiple 0.25 because each operation is 0.25 probability
        return memo[A][B] *= 0.25;

    }
}