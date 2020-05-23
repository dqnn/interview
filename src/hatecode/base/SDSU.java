package hatecode.base;

import java.util.stream.IntStream;

public class SDSU {
        int[] parent;
        public SDSU(int n) {
            this.parent = new int[n];
            IntStream.range(0,n).forEach(e->parent[e] = e);
        }
        public void union(int c1, int c2) {
            int p1 = find(c1);
            int p2 = find(c2);
            //here we want to use the smallest char as root
            if(p1 < p2) parent[p2] = p1;
            else parent[p1] = p2;
        }
        
        public int find(int c) {
            if(c != parent[c]) {
                parent[c] = find(parent[c]);
            }
            return parent[c];
        }
    }