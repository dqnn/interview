package hatecode._0001_0999;

import java.util.*;

public class setAllValueO1 {
    
    /*
     * class CustomMap {

	public void set(int key, int value) {
	}

	public Integer get(int key) {
	}

	public void setAll(int value) {
	}
}
All methods should work in O(1) time.

Example:

CustomMap map = new CustomMap();
map.set(0, 1);
map.get(0); // 1
map.set(1, 2);
map.get(1); // 2
map.setAll(5);
map.get(0); // 5
map.get(1); // 5
map.get(2); // null, there's no such key
map.set(2, 7);
map.get(0); // 5
map.get(1); // 5
map.get(2); // 7
     */

    class Node{
        int version;
        int v;
        public Node(int v, int version) {
            this.version = version;
            this.v = v;
        }
    }

/*
 * the problem is to say: design one data structure, so get, put and setAll are O(1) ops
 * 
 * versionmap is to maintain setAll versions 
 * for every set, it will keep current version
 * 
 * 
 * 
 */

class CustomMap {
     
    Map<Integer, Node> map = new HashMap<>();
    Map<Integer, Integer> versionMap = new HashMap<>();
    int version = 0;

	public void set(int k, int v) {
        map.put(k, new Node(v, version));
	}

	public Integer get(int k) {
        if (!map.containsKey(k)) return null;

        Node res = map.get(k);
        if(res.version < version) {
            return versionMap.get(version);
        }

        return res.v;

	}

	public void setAll(int v) {
        version++;
        versionMap.put(version, v);

	}
} 

}
