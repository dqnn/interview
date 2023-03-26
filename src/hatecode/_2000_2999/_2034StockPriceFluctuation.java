package hatecode._2000_2999;

import java.util.*;

public class _2034StockPriceFluctuation {
    
    /*
    2034. Stock Price Fluctuation
    You are given a stream of records about a particular stock. Each record contains a timestamp and the corresponding price of the stock at that timestamp.
    
    Unfortunately due to the volatile nature of the stock market, the records do not come in order. Even worse, some records may be incorrect. Another record with the same timestamp may appear later in the stream correcting the price of the previous wrong record.
    
    Design an algorithm that:
    
    Updates the price of the stock at a particular timestamp, correcting the price from any previous records at the timestamp.
    Finds the latest price of the stock based on the current records. The latest price is the price at the latest timestamp recorded.
    Finds the maximum price the stock has been based on the current records.
    Finds the minimum price the stock has been based on the current records.
    Implement the StockPrice class:
    
    StockPrice() Initializes the object with no price records.
    void update(int timestamp, int price) Updates the price of the stock at the given timestamp.
    int current() Returns the latest price of the stock.
    int maximum() Returns the maximum price of the stock.
    int minimum() Returns the minimum price of the stock.
     
    
    Example 1:
    
    Input
    ["StockPrice", "update", "update", "current", "maximum", "update", "maximum", "update", "minimum"]
    [[], [1, 10], [2, 5], [], [], [1, 3], [], [4, 2], []]
    Output
    [null, null, null, 5, 10, null, 5, null, 2]
    */
    
    /*

    thinking process: O(lgn)/O(n)
     the problem is to say: 
    */
        TreeMap<Integer, Integer> t2price;
        TreeMap<Integer, Set<Integer>> price2t;
        public _2034StockPriceFluctuation() {
            price2t = new TreeMap<>();
            t2price = new TreeMap<>();
        }
        
        public void update(int timestamp, int price) {
            if (t2price.containsKey(timestamp)) {
                int prevPrice = t2price.get(timestamp);
                price2t.get(prevPrice).remove(timestamp);
                if (price2t.get(prevPrice).size() == 0) price2t.remove(prevPrice);
                t2price.remove(timestamp);
            }
            
            t2price.put(timestamp, price);
            price2t.computeIfAbsent(price, v->new HashSet<>()).add(timestamp);       
        }
        
        public int current() {
            return t2price.lastEntry().getValue();
        }
        
        public int maximum() {
            return price2t.lastKey();
        }
        
        public int minimum() {
            return price2t.firstKey();
        }
    }
    
    /**
     * Your StockPrice object will be instantiated and called as such:
     * StockPrice obj = new StockPrice();
     * obj.update(timestamp,price);
     * int param_2 = obj.current();
     * int param_3 = obj.maximum();
     * int param_4 = obj.minimum();
     */