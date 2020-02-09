package hatecode._0001_0999;
import java.util.*;
public class SimilarStringGroups {
/*
839. Similar String Groups
Two strings X and Y are similar if we can swap two letters (in different positions) of X, so that it equals Y.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is similar to at least one other word in the group.

We are given a list A of strings.  Every string in A is an anagram of every other string in A.  How many groups are there?

Example 1:

Input: ["tars","rats","arts","star"]
Output: 2
*/
    //just leverage the union find to find all possible groups
    public int numSimilarGroups(String[] A) {
        DUS dus = new DUS(A.length);
        for(int i=0; i<A.length; i++){
            for(int j=i+1; j<A.length; j++){
                if(isSimilar(A[i],A[j])){
                    dus.union(i,j);
                }          
            }
        }
        return dus.count;
    }
    
    private boolean isSimilar(String a, String b){
        int count = 0;
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i)!=b.charAt(i) && ++count>2){
                return false;
            }
        }
        return true;
    }
    
    class DUS {
        int[] parent;
        int[] size;
        int count;
        public DUS(int n) {
            parent = new int[n];
            for(int i =0; i< n;i++) parent[i] = i;
            size = new int[n];
            Arrays.fill(size, 1);
            count = n;
        }
        
        public int find(int x) {
            while (x != parent[x]) {
                x = parent[x];
            }
            return x;
        }
        
        public void union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) return;
            if (size[x] >= size[y]) {
                parent[y] = x;
                size[x] += size[y];
            } else {
                parent[x] = y;
                size[y] += size[x];
            }
            count--;
        }
    }
}