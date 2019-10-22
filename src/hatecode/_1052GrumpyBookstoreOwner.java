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
    //thinking process: O(n)/O(1)
    
    //the problem is to say: a book store has customers[i] at i-th minute, customer will 
    //leave at minute, grumpy[i] = 0 or 1 1 means grumpy then customer not satisfied, so 
    //but they can have X not grumpy so return max customer satisified number
    
    //so BF is we can try window at each start to test which is biggest gain. 
    
    //but that will be O(Xn),so we can improve a little bit by using a window
    //win starts 1, if it bigger than X, then we remove first item in the window
    
    //satisfied will record not grumpy customers amount
    //win will record if grumpy customer amount, 
    //so if win size >=X, then we will remove first one, so this is to compare 
    //if window starts from each element, the biggest value, 
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        if(customers == null || customers.length< 1) return 0;
        int win = 0, maxSatisfiedInWin = 0, satisfied = 0;
        for(int i = 0; i< customers.length; i++) {
            if(grumpy[i] == 0) satisfied += customers[i];
            else win += customers[i];
            
            if(i >= X) {
                win -= grumpy[i-X] * customers[i-X];
            }
            maxSatisfiedInWin = Math.max(win, maxSatisfiedInWin);
        }
        
        return satisfied + maxSatisfiedInWin;
    }
}