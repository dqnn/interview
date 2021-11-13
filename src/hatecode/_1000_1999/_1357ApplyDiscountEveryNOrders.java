package hatecode._1000_1999;

import java.util.*;
public class _1357ApplyDiscountEveryNOrders {
/*
1357. Apply Discount Every n Orders
There is a supermarket that is frequented by many customers. The products sold at the supermarket are represented as two parallel integer arrays products and prices, where the ith product has an ID of products[i] and a price of prices[i].

When a customer is paying, their bill is represented as two parallel integer arrays product and amount, where the jth product they purchased has an ID of product[j], and amount[j] is how much of the product they bought. Their subtotal is calculated as the sum of each amount[j] * (price of the jth product).

The supermarket decided to have a sale. Every nth customer paying for their groceries will be given a percentage discount. The discount amount is given by discount, where they will be given discount percent off their subtotal. More formally, if their subtotal is bill, then they would actually pay bill * ((100 - discount) / 100).

Implement the Cashier class:

Cashier(int n, int discount, int[] products, int[] prices) Initializes the object with n, the discount, and the products and their prices.
double getBill(int[] product, int[] amount) Returns the final total of the bill with the discount applied (if any). Answers within 10-5 of the actual value will be accepted.
 

Example 1:

Input
["Cashier","getBill","getBill","getBill","getBill","getBill","getBill","getBill"]
[[3,50,[1,2,3,4,5,6,7],[100,200,300,400,300,200,100]],[[1,2],[1,2]],[[3,7],[10,10]],[[1,2,3,4,5,6,7],[1,1,1,1,1,1,1]],[[4],[10]],[[7,3],[10,10]],[[7,5,3,1,6,4,2],[10,10,10,9,9,9,7]],[[2,3,5],[5,3,2]]]
Output
[null,500.0,4000.0,800.0,4000.0,4000.0,7350.0,2500.0]
*/
    private int customerCnt = 0, discountCount, discount;
    private Map<Integer, Integer> price = new HashMap<>();
    public _1357ApplyDiscountEveryNOrders(int n, int discount, int[] products, int[] prices) {
        this.discountCount = n;
        this.discount = discount;
        for (int i = 0; i < products.length; ++i)
            price.put(products[i], prices[i]);
    }
    
    public double getBill(int[] product, int[] amount) {
        double total = 0.0d;
        for (int i = 0; i < product.length; ++i)
            total += price.get(product[i]) * amount[i];
        return total * (++customerCnt % discountCount == 0 ? 1 - discount / 100d : 1);
    }
}

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */