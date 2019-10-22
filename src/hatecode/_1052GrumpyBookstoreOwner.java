package hatecode;
public class _1052GrumpyBookstoreOwner {
/*
1052. Grumpy Bookstore Owner
Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of customers (customers[i]) enter the store, and all those customers leave after the end of that minute.

On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1, otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied, otherwise they are satisfied.

The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.

Return the maximum number of customers that can be satisfied throughout the day.

 

Example 1:

Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
Output: 16
*/
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        if(customers == null || customers.length< 1) return 0;
        int win = 0, maxSatisfied = 0, satisfied = 0;
        for(int i = 0; i< customers.length; i++) {
            if(grumpy[i] == 0) satisfied += customers[i];
            else win += customers[i];
            
            if(i >= X) {
                win -= grumpy[i-X] * customers[i-X];
            }
            maxSatisfied = Math.max(win, maxSatisfied);
        }
        
        return satisfied + maxSatisfied;
    }
}