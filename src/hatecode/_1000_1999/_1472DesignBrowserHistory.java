import java.util.*

public class _1472DesignBrowserHistory {
    /*
    1472. Design Browser History
    You have a browser of one tab where you start on the homepage and you can visit another url, get back in the history number of steps or move forward in the history number of steps.
    
    Implement the BrowserHistory class:
    
    BrowserHistory(string homepage) Initializes the object with the homepage of the browser.
    void visit(string url) Visits url from the current page. It clears up all the forward history.
    string back(int steps) Move steps back in history. If you can only return x steps in the history and steps > x, you will return only x steps. Return the current url after moving back in history at most steps.
    string forward(int steps) Move steps forward in history. If you can only forward x steps in the history and steps > x, you will forward only x steps. Return the current url after forwarding in history at most steps.
     
    
    Example:
    
    Input:
    ["BrowserHistory","visit","visit","visit","back","back","forward","visit","forward","back","back"]
    [["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],[1],[1],["linkedin.com"],[2],[2],[7]]
    Output:
    [null,null,null,null,"facebook.com","google.com","facebook.com",null,"linkedin.com","google.com","leetcode.com"]
    
    */
        /*
         * thinking process: O(1)/O(n)
         * 
         * the problem is to say: design a browser history functions, 
         * visit(String url): visit the current page
         * backward(int steps):back 
         * forward(int steps): forward steps
         * 
         * 
         * so we use the list to store the urls, 
         * total: to store how many urls are in current scope.
         * cur: which url cur point to
         * 
         * when we visit(string url), we will dectect whether we should append 
         * the url to end of the array or overwrite the one in the array.
         * 
         * 
         */
        List<String> list;
        int total = 0;
        int cur = 0;
        public BrowserHistory(String homepage) {
            this.list = new ArrayList<>();
            list.add(homepage);
            total++;
            cur=1;
        }
        
        public void visit(String url) {
            //here is the key: we use list.size() not total to judge whether we 
            //should overwrite or append
            if (list.size() > cur) {
                list.set(cur, url);
            } else {
                list.add(url);
            }
            cur++;
            total = cur;
        }
        
        public String back(int steps) {
            cur = Math.max(1, cur - steps);
            return list.get(cur - 1);
        }
        
        public String forward(int steps) {
            cur = Math.min(total, cur + steps) ;
            return list.get(cur - 1);
        }
       
    }
    
    /**
     * Your BrowserHistory object will be instantiated and called as such:
     * BrowserHistory obj = new BrowserHistory(homepage);
     * obj.visit(url);
     * String param_2 = obj.back(steps);
     * String param_3 = obj.forward(steps);
     */