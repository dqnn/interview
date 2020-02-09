package hatecode._1000_1999;
public class _1103DistributeCandiesToPeople {
/*
1103. Distribute Candies to People
We distribute some number of candies, to a row of n = num_people people in the following way:

We then give 1 candy to the first person, 2 candies to the second person, and so on until we give n candies to the last person.

Then, we go back to the start of the row, giving n + 1 candies to the first person, n + 2 candies to the second person, and so on until we give 2 * n candies to the last person.

This process repeats (with us giving one more candy each time, and moving to the start of the row after we reach the end) until we run out of candies.  The last person will receive all of our remaining candies (not necessarily one more than the previous gift).

Return an array (of length num_people and sum candies) that represents the final distribution of candies.

 

Example 1:

Input: candies = 7, num_people = 4
Output: [1,2,3,1]
*/
    //thinking process: O(sqrt(n))/O(n)
    
    //people in a curcle, start from 1, we give 1 candy, then +1 every time, last
    //if we do not have enough, we give all of them, so return an array represents how many
    //candies each one have
    
    //josephus similiar problem, 
    //
    public int[] distributeCandies(int candies, int n) {
        if(n <= 0) return new int[0];
        
        int[] res = new int[n];
        for (int give = 0; candies > 0; candies -= give) {
            res[give % n] +=  Math.min(candies, ++give);
        }
        return res;
    }
}