package hatecode._1000_1999;
public class _1099TwoSumLessThanK {
/*
1099. Two Sum Less Than K
Given an array A of integers and integer K, return the maximum S such that there 
exists i < j with A[i] + A[j] = S and S < K. If no i, j exist satisfying this equation, return -1.

Example 1:

Input: A = [34,23,1,24,75,33,54,8], K = 60
Output: 58
*/
    
    class Node {
        int v;
        int f;
        public Node(int v, int f) {
            this.v =v;
            this.f = f;
        }
        
        public String toString(){return v +"-" + f;}
    }
    //thinking process: O(n)/O(max) interview friendly 
    
    //we use bucket sort to arrange all integers and to find k - a - 1 in the buckets, 
    //in the buckets, we store buckets[a] =a if a is from A, if not, we store previous smaller integer

    /*
     * we use bucket to sort the array, but still need to fill some slots which are empty 
     * 
     */
    public int twoSumLessThanK(int[] A, int k) {
        if(A == null || A.length < 1) return -1;
        
       
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for(int a : A) {
            min = Math.min(min, a);
            max = Math.max(max, a);
        }
        
        Node[] buckets = new Node[max+1];
        for(int a : A) {
            if(buckets[a] == null) buckets[a] = new Node(a, 0);
            buckets[a].f ++;
        }
        
        Node cur = null;
        for(int i = 0; i<= max; i++) {
            if( buckets[i] != null && buckets[i].v == i) {
                cur = buckets[i];
            }
            
            buckets[i] = cur;
        }
       //System.out.println(Arrays.toString(buckets));
        int sum = -1;
        
        for(int a : A) {
            int target = k - a - 1;
            if(target < min) continue;
            if(target > max) target = max;
            //System.out.println(a);
            if(a == buckets[target].v && buckets[target].f == 1) continue;
            sum = Math.max(sum, buckets[target].v + a);
        }
        //System.out.println( "last " + sum);
        return sum;
        
    }
}