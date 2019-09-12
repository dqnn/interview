package hatecode;
public class _990SatisfiabilityOfEqualityEquations {
/*
990. Satisfiability of Equality Equations
Given an array equations of strings that represent relationships between variables, each string equations[i] has length 4 and takes one of two different forms: "a==b" or "a!=b".  Here, a and b are lowercase letters (not necessarily different) that represent one-letter variable names.

Return true if and only if it is possible to assign integers to variable names so as to satisfy all the given equations.

 

Example 1:

Input: ["a==b","b!=a"]
Output: false
*/
    //thinking process:
    //the problem is to given an string array, which only contains 
    //a == b or a != b two forms but it contains multiple chars, like a , b c..
    //so return true if we can find such values to satisfy all constains
    
    //so we use union find to find all equals chars, with two loops on the array, 
    //we can apply all constains
    public boolean equationsPossible(String[] es) {
        if(es == null || es.length < 1) return false;
        DSU dsu = new DSU(26);
        for(String s : es) {
            if(s.contains("==")) {
                dsu.union(s.charAt(0) - 'a', s.charAt(3) - 'a');
            }
        }
        
        for(String s : es) {
            if(s.contains("!=")) {
                int x = dsu.find(s.charAt(0) - 'a');
                int y = dsu.find(s.charAt(3) - 'a');
                if(x == y) return false;
            }
        }
        
        return true;
        
    }
    
    class DSU {
        int[] parent;
        public DSU(int n){
            parent = new int[n];
            for(int i = 0; i< n; i++) parent[i] = i;
        }
        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if(x == y) return;
            parent[x] = y;
        }
        public int find(int x) {
            while(x != parent[x]) x = parent[x];
            
            return x;
        }
    }
}