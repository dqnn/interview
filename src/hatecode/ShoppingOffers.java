package hatecode;
import java.util.*;
import java.util.stream.*;
class ShoppingOffers {
/*
638. Shopping Offers
In LeetCode Store, there are some kinds of items to sell. Each item has a price.

However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.

Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special offer, other numbers represents how many specific items you could get if you buy this offer.

You could use any of special offers as many times as you want.

Example 1:
Input: [2,5], [[3,0,5],[1,2,10]], [3,2]
Output: 14
*/
    //interview friendly, so thinking this problem as combination sum or backpack, 
    //consider the needs is bag, the specials are stones, how can we combine these stones to get max value,
    //here we are generating a new list for next iteration, we examine each offer withe needs, if it 
    //matches, then we put in the bag, and we put the rest needs in a new list, and next time we still 
    //iterate on special offers i position which means we repeat add to the list
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        //this is direct DFS
        //return helper(price, special, needs, 0);
        //this is memo
        return helper(price, special, needs, 0, new HashMap<>());
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos) {
        int min = IntStream.range(0, price.size()).map(i->price.get(i) * needs.get(i)).sum();
        for(int i = 0; i< special.size();i++) {
            List<Integer> offer = special.get(i);
            List<Integer> temp = new ArrayList<>();
            
            for(int j = 0; j< needs.size();j++) {
                //each item will be less than special offer could provide, so we skip, else
                //we add to temp
                if (needs.get(j) < offer.get(j)) {
                    temp = null;
                    break;
                }
                temp.add(needs.get(j) - offer.get(j));
            }
            // use the current offer and try next, note i still i, not i+1, which means we can re-use this item
            if (temp != null) {
                min = Math.min(min, offer.get(offer.size() -1) + helper(price, special, temp, i));
            }
        }
        return min;
    }
    
    public int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos, Map<List<Integer>, Integer> map) {
        if(map.containsKey(needs)) return map.get(needs);
        int min = IntStream.range(0, price.size()).map(i->price.get(i) * needs.get(i)).sum();
        for(int i = 0; i< special.size();i++) {
            List<Integer> offer = special.get(i);
            List<Integer> temp = new ArrayList<>();
            
            for(int j = 0; j< needs.size();j++) {
                //each item will be less than special offer could provide, so we skip, else
                //we add to temp
                if (needs.get(j) < offer.get(j)) {
                    //temp is null means we cannot use this offer
                    temp = null;
                    break;
                }
                temp.add(needs.get(j) - offer.get(j));
            }
            // use the current offer and try next, note i still i, not i+1, which means we can re-use this item
            if (temp != null) {
                min = Math.min(min, offer.get(offer.size() -1) + helper(price, special, temp, i, map));
            }
        }
        map.put(needs, min);
        return min;
    }
}