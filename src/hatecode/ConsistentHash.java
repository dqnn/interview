package hatecode;

import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/*
 * this one is not from LeetCode, but just for understanding he consistence hash implementations
 */
public class ConsistentHash<T> {

    /**
     * 哈希函数
     */
    private final HashFunction hashFunction;
    /**
     * 每台物理服务器节点虚拟出虚拟节点个数
     */
    private final int numberOfReplicas;
    /**
     * Hash环
     * <p>
     * 该Map中仅存放虚拟服务器节点，并不存放实际的缓存数据！！！
     * 该Map中key为虚拟服务器节点对应在环中的位置
     * <p>
     * 使用TreeMap的原因：
     * TreeMap的内部使用红黑树（平衡查找树），
     * 因而在确定了一个缓存数据key的hash值在该环中的位置后，
     * 可以很快查找到该缓存数据应该放置的物理服务器
     */
    private final SortedMap<Integer, T> circle = new TreeMap<Integer, T>();

    /**
     * 构造器
     *
     * @param hashFunction     哈希函数
     * @param numberOfReplicas 每台物理服务器节点虚拟出虚拟节点个数
     * @param nodes            物理服务器节点
     */
    public ConsistentHash(HashFunction hashFunction, int numberOfReplicas, Collection<T> nodes) {

        this.hashFunction = hashFunction;
        this.numberOfReplicas = numberOfReplicas;
        //将物理服务器放在环上
        for (T node : nodes) {
            add(node);
        }
    }

    /**
     * 添加物理服务器节点
     * <p>
     * 实现：将该物理服务器节点放在环中的numberOfReplicas个位置处，
     * （添加一个物理服务器节点，就要添加numberOfReplicas个虚拟节点）
     *
     * @param node 要添加的物理服务器节点
     */
    public void add(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(hashFunction.hash(node.toString() + i),
                    node);
        }
    }

    /**
     * 移除物理服务器节点
     * <p>
     * 实现：因为该物理服务器节点放在了环中的多个位置，所以在删除时都要删除
     * （移除一个物理服务器节点，就要将该服务器节点对应的numberOfReplicas个虚拟节点移除）
     *
     * @param node 要移除的物理服务器节点
     */
    public void remove(T node) {
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(hashFunction.hash(node.toString() + i));
        }
    }

    /**
     * 根据缓存数据的key获取物理服务器节点
     *
     * @param key 缓存数据的key
     * @return 该缓存数据所在的物理服务器节点
     */
    public T get(Object key) {
        if (circle.isEmpty()) {
            return null;
        }
        int hash = hashFunction.hash(key);
        //如果缓存数据的key的哈希值没有落在虚拟服务器节点上，
        //则
        if (!circle.containsKey(hash)) {
            //获取map中key值大于该缓存数据的key的哈希值的子map，>= hash
            SortedMap<Integer, T> tailMap = circle.tailMap(hash);
            //如果该子map不为空，则返回该子map的第一个元素（因为该map是排序好的，第一个即是最小的元素）
            //如果该子map为空，说明该缓存数据的key的哈希值超出了哈希环中最后的那个虚拟服务器节点对应的位置（顺时针），
            //                则将该缓存数据放在哈希环中第一个虚拟服务器节点中（顺时针）
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        return circle.get(hash);
    }

}


class HashFunction {
    //we should use MD5 to improve
    int hash(Object key) {
        return key.hashCode();
    }
}