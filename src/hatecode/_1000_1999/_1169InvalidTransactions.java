package hatecode._1000_1999;

import java.util.*;
public class _1169InvalidTransactions {
/*
1169. Invalid Transactions
A transaction is possibly invalid if:

the amount exceeds $1000, or;
if it occurs within (and including) 60 minutes of another transaction with the same name in a different city.
Each transaction string transactions[i] consists of comma separated values representing the name, time (in minutes), amount, and city of the transaction.

Given a list of transactions, return a list of transactions that are possibly invalid.  You may return the answer in any order.

 

Example 1:

Input: transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
Output: ["alice,20,800,mtv","alice,50,100,beijing"]
*/
    
    //thinking process: O(n)/O(n)
    
    //the problem is to say: given array of strings, "{name},{time},{amount},{city}",
    //we need to return invalid transactions as list of origin strings which match 2 rules
    //1 is transactions > 1000 dollars
    //2 is in different city and time diff <= 60m, both transactions are invalid.
    
    //the biggest problem is the stream data, it does not come as time series, 
    //some time are lag behind, so we need to compare each transaction to all previous 
    //transactions, i believe we can improve here is to use TreeMap to filter out the time diff
    //is 60m
    public class Transaction {
        String name;
        int time;
        String city;
        String trans;
        
        public Transaction(String name, int time, String city, String trans) {
            this.name = name;
            this.time = time;
            this.city = city;
            this.trans = trans;
        }
    }
    
    public List<String> invalidTransactions(String[] transactions) {
        Set<String> out = new HashSet<>();
        
        Map<String, List<Transaction>> map = new HashMap<>();
        
        for(String t : transactions) {
            String[] split = t.split(",");
            
            String name = split[0];
            int time = Integer.valueOf(split[1]);
            int amount = Integer.valueOf(split[2]);
            String city = split[3];
            
            if(amount > 1000) {
                out.add(t);
            }
            
            List<Transaction> otherTransactions = map.get(name);
            
            if(otherTransactions == null) {
                otherTransactions = new ArrayList<>();
                otherTransactions.add(new Transaction(name, time, city, t));
                map.put(name, otherTransactions);
            } else {
                for(Transaction transa : otherTransactions) {
                    if(!transa.city.equals(city) && Math.abs(transa.time - time) <= 60) {
                        out.add(transa.trans);
                        out.add(t);
                    }
                }
                
                otherTransactions.add(new Transaction(name, time, city, t));
            }
        }
        
        return new ArrayList<String>(out);
    }
}