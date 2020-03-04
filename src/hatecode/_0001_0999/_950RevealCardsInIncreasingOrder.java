package hatecode._0001_0999;

import java.util.*;
public class _950RevealCardsInIncreasingOrder {
/*
950. Reveal Cards In Increasing Order
In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.

Initially, all the cards start face down (unrevealed) in one deck.

Now, you do the following steps repeatedly, until all cards are revealed:

Take the top card of the deck, reveal it, and take it out of the deck.
If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
If there are still unrevealed cards, go back to step 1.  Otherwise, stop.
Return an ordering of the deck that would reveal the cards in increasing order.

The first entry in the answer is considered to be the top of the deck.

 

Example 1:

Input: [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]
*/
    //thinking process:
    
    //pretty elegant solution, 
    //
     public int[] deckRevealedIncreasing(int[] deck) {
        //we sort original array
        int n= deck.length;
        Arrays.sort(deck);
        Queue<Integer> q= new LinkedList<>();
        for (int i=0; i<n; i++) q.add(i);
        int[] res= new int[n];
        for (int i=0; i<n; i++){
            //We first pick the index at the top: res[q.poll()]=deck[i]
            res[q.poll()]=deck[i];
            //we put the next index to the bottom: q.add(q.poll());
            q.add(q.poll());
        }
        return res;
    }
}