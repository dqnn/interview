package hatecode._0001_0999;

import java.util.*;
public class InterviewArbitrageOnCurrencyExchange {

    /*
     * problem statements:
     * the problem is to say, given a list of exchange rate, like 
     * ["USD", "CNY", "6.8564"].....
     * find the shortest arbitrage path, arbitrage means we have more money when
     * we back to original currency
     */
    
    class QueueNode {
        List<String> currencyList;
        double curAmount = 1.0;
        
        public QueueNode(List<String> currency, double amount) {
            this.curAmount *= amount;
            this.currencyList = new ArrayList<>();
            this.currencyList.addAll(currency);
        }
    }
    
    class MapNode {
        String currency;
        double rate = 0.0;
        
        public MapNode(String currency, double rate) {
            this.rate = rate;
            this.currency = currency;
        }
    }
    
    
    public List<String> findShortestArbitragePath(String[][] rate) {
        if (rate == null || rate.length < 1) return new ArrayList<>();
        
        Map<String, List<MapNode>> map = new HashMap<>();
        
        for(String[] e: rate) {
            if(e[0].equals(e[1])) {
                continue;
            }
            double eRate = Double.valueOf(e[2]);
            map.computeIfAbsent(e[0], v->new ArrayList<>()).add(new MapNode(e[1], eRate));
            map.computeIfAbsent(e[1], v->new ArrayList<>()).add(new MapNode(e[0], 1.0/eRate));
            System.out.println(Arrays.asList(map));
        }
        
        Queue<QueueNode> q = new LinkedList<>();
        List<String> res = new ArrayList<>();
        
        for(String currency: map.keySet()) {
            q.offer(new QueueNode(Arrays.asList(currency), 1.0));
        }
        
        while(!q.isEmpty()) {
            int size = q.size();
            while(size -- > 0) {
                QueueNode node = q.poll();
                
                String origialCurrency = node.currencyList.get(0);
                if(node.curAmount > 1.0 && 
                        node.currencyList.get(0).equals(node.currencyList.get(node.currencyList.size() - 1))) {
                    StringBuilder sb = new StringBuilder();
                    for(String str: node.currencyList) {
                        sb.append(str + "->");
                    }
                    sb.append(node.curAmount);
                    res.add(sb.toString());
                    continue;
                }
                
                for(MapNode e: map.get(origialCurrency)) {
                    node.currencyList.add(e.currency);
                    q.offer(new QueueNode(node.currencyList, node.curAmount * e.rate));
                }
            }
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        
        String[][] rate = {{"USD", "CNY", "6.85446"}, 
                           {"EUR", "USD", "0.78655"},
                           {"EUR", "CNY", "5.7654"}};
        InterviewArbitrageOnCurrencyExchange exchange = new InterviewArbitrageOnCurrencyExchange();
        exchange.findShortestArbitragePath(rate);
    }

}
