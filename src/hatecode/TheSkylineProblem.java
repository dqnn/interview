package hatecode;

import java.util.*;

/**
 * Project Name : Leetcode
 * Package Name : leetcode
 * File Name : TheSkylineProblem
 * Creator : duqiang
 * Date : Sep, 2018
 * Description : 218. The Skyline Problem
 */
public class TheSkylineProblem {
/*
A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a 
distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape 
photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).

Buildings  Skyline Contour
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and 
Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. 
It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings 
are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], 
[15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] 
that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the 
last key point, where the rightmost building ends, is merely used to mark the termination of the skyline,
 and always has zero height. Also, the ground in between any two adjacent buildings should be considered part 
 of the skyline contour.

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10],
 [20 8], [24, 0] ].

Notes:

The number of buildings in any input list is guaranteed to be in the range [0, 10000].
The input list is already sorted in ascending order by the left x position Li.
The output list must be sorted by the x position.
There must be no consecutive horizontal lines of equal height in the output skyline. For instance, 
[...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged
 into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 */
    /**
     * time : O(n^2)
     * space : O(n)
     * @param buildings
     * @return
     */
    //thinking process:
    
    // so the key points must be on the start and endpoint but maybe with different height. 
    // sweeping line: image one x = 0 from left to right, every point it encountered will be recorded. 
    
    // we categories two type of points, start and endpoint by height, if it is smaller than 0 then starts. 
    //sweeping templates
 /*
this is output when we add output in following code
pre-0-q-[10, 0]-cur-10-height--[2, -10]
pre-10-q-[15, 0, 10]-cur-15-height--[3, -15]
pre-15-q-[15, 12, 10, 0]-cur-15-height--[5, -12]
pre-15-q-[12, 0, 10]-cur-12-height--[7, 15]
pre-12-q-[12, 0]-cur-12-height--[9, 10]
pre-12-q-[0]-cur-0-height--[12, 12]
pre-0-q-[10, 0]-cur-10-height--[15, -10]
pre-10-q-[10, 0, 8]-cur-10-height--[19, -8]
pre-10-q-[8, 0]-cur-8-height--[20, 10]
pre-8-q-[0]-cur-0-height--[24, 8]
  */
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null || buildings.length < 1) {
            return res;
        }
        List<int[]> h = new ArrayList<>();
        //we add start point and endpoint into the array and sort them, 
        // note, height < 0 is the start point, otherwise it will be end point
        //this can help the start is always before the end point in h
        for(int[] temp : buildings) {
            h.add(new int[]{temp[0], -temp[2]});
            h.add(new int[]{temp[1], temp[2]});
        }
        //we sort this start value, if the same, we sort by end value
        //so after this, we can get h array sorted by start value.if same start, then we sort by height, 
        //we made start's height negative, so the taller will be in back
        Collections.sort(h, (a, b)->(a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
        //this stores the start point height and sort desc, why desc sort? if we reached the end point,then we need to 
        //remove this height
        //because as we scan the array, the q keep the height in array, this stands for on the sweeping line, how many height
        //at point t = a. so we always get the max height of point a so we desc sort and get the head until we met this 
        //end point then we remove
        Queue<Integer> q = new PriorityQueue<>((a,b) ->(b - a));
        //arrange 0 in the end of array, some height also will be 0
        q.offer(0);
        int pre = 0;
        // h is sorted from low to high, and small to big
        //start scan the start point and endpoint, if it is starting point, and prev height is the same, then 
        //we skip or we will add to the result since the height is different. 
        //also this is sweeping line templates
        
        // pre : record previous number we put into results. 
        //thinking about x = 0 to sweep this array, we want to record key point and we remember
        // the key point is where the height is different compared to previous one, this is the pattern,
        //we need a structure to simplify the data process and code to process
        //for loop on the points and PriorityQueue to sort to get biggest number in current
        for(int[] height: h) {
            //this is start point new height, so we need to add to queue
            if (height[1] < 0) {
                q.offer(-height[1]);
            } else {
                //end point, we just remove the height, since height already showed before
                q.remove(height[1]);
            }
            // we did not remove, just peek since we still on the previous height
            //this is highest 
            int cur = q.peek();
            System.out.println(String.format("pre-%s-q-%s-cur-%s-height--%s", pre,q, cur, Arrays.toString(height)));
            if (pre != cur) {
                res.add(new int[]{height[0], cur});
                pre = cur;
            }
        }
        return res;
    }

    /**
     * time : O(nlogn)
     * space : O(n)
     * @param buildings
     * @return
     */
/*
与HashSet完全类似，TreeSet里面绝大部分方法都市直接调用TreeMap方法来实现的。

相同点：
TreeMap和TreeSet都是有序的集合，也就是说他们存储的值都是拍好序的。
TreeMap和TreeSet都是非同步集合，因此他们不能在多线程之间共享，不过可以使用方法Collections.synchroinzedMap()来实现同步
运行速度都要比Hash集合慢，他们内部对元素的操作时间复杂度为O(logN)，而HashMap/HashSet则为O(1)。
不同点：
最主要的区别就是TreeSet和TreeMap分别实现Set和Map接口
TreeSet只存储一个对象，而TreeMap存储两个对象Key和Value（仅仅key对象有序）
TreeSet中不能有重复对象，而TreeMap中可以存在

TreeMap 的实现使用了红黑树数据结构，也就是一棵自平衡的排序二叉树，这样就可以保证快速检索指定节点。对于 TreeMap 而言，
它采用一种被称为“红黑树”的排序二叉树来保存 Map 中每个 Entry —— 每个 Entry 都被当成“红黑树”的一个节点对待。

TreeMap<String , Double> map =  new TreeMap<String , Double>();   
        map.put("ccc" , 89.0);   
        map.put("aaa" , 80.0);   
        map.put("zzz" , 80.0);   
        map.put("bbb" , 89.0);   
        System.out.println(map); 
当程序执行 map.put("ccc" , 89.0); 时，系统将直接把 "ccc"-89.0 这个 Entry 放入 Map 中，这个 Entry 就是该“红黑树”的根节点。
接着程序执行 map.put("aaa" , 80.0); 时，程序会将 "aaa"-80.0 作为新节点添加到已有的红黑树中。

以后每向 TreeMap 中放入一个 key-value 对，系统都需要将该 Entry 当成一个新节点，添加成已有红黑树中，通过这种方式就可保证 
TreeMap 中所有 key 总是由小到大地排列。例如我们输出上面程序，将看到如下结果（所有 key 由小到大地排列）：

 {aaa=80.0, bbb=89.0, ccc=89.0, zzz=80.0}

对于 TreeMap 而言，由于它底层采用一棵“红黑树”来保存集合中的 Entry，这意味这 TreeMap 添加元素、取出元素的性能都比 
HashMap 低（红黑树和Hash数据结构上的区别）：当 TreeMap 添加元素时，需要通过循环找到新增 Entry 的插入位置，因此比较耗性能；
当从 TreeMap 中取出元素时，需要通过循环才能找到合适的 Entry，也比较耗性能。但 TreeMap、TreeSet 比 HashMap、HashSet 的优势在于：
TreeMap 中的所有 Entry 总是按 key 根据指定排序规则保持有序状态，TreeSet 中所有元素总是根据指定排序规则保持有序状态。

TreeMap has best method

Returns a view of the portion of this map whose keys range from fromKey to toKey.
subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive)

Returns a view of the portion of this map whose keys are greater than (or equal to, if inclusive is true) fromKey.
tailMap(K fromKey, boolean inclusive)

Returns a view of the portion of this map whose keys are less than (or equal to, if inclusive is true) toKey.
headMap(K fromKey, boolean inclusive)

Returns the first (lowest) key currently in this map. first one in TreeMap
firstKey()
*/
    public List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        for (int[] b: buildings) {
            heights.add(new int[]{b[0], - b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        //we use treemap to store the heights
        //height to occurrence
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put(0,1);
        int prev = 0;

        for (int[] h: heights) {
            if (h[1] < 0) {
                map.put(-h[1], map.getOrDefault(-h[1], 0) + 1);
            } else {
                int cnt = map.get(h[1]);
                if (cnt == 1) {
                    map.remove(h[1]);
                } else {
                    map.put(h[1], cnt - 1);
                }
            }
            int cur = map.firstKey();//lowest key, which means the biggest height here, first one
            if (prev != cur) {
                res.add(new int[]{h[0], cur});
                prev = cur;
            }
        }
        return res;
    }
    
    
    
    //binary一Segment tree
    
    private static class Node{
        int start, end;
        Node left, right;
        int height;
        
        public Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    //so skyline problem is to get the key points in 2D array, 
    // we found the pattern that key points are the diff point compared
    //to previous one. 
    
    //so we set up a segment tree, the leaf node is 2,3(x axis) only 1 diff
    //so we can get all the changes in 2D array if height has changes
    //so when we 2nd to visit the 2D array, we can updates each leaf
    //node with a diff height, if it is higher than previous one, 
    
    //because 
    public List<int[]> getSkyline3(int[][] buildings) {
        Set<Integer> endpointSet = new HashSet<Integer>();
        //add start and end point into Set,so we can merge some same point
        for(int[] building : buildings){
            //start point
            endpointSet.add(building[0]);
            //end point
            endpointSet.add(building[1]);
        }
        //sort these start and end points asc
        List<Integer> endpointList = new ArrayList<Integer>(endpointSet);
        Collections.sort(endpointList);
        
        // Map to store the start and end point with itx idx in endpointList,so we can have segements
        HashMap<Integer, Integer> endpointMap = new HashMap<Integer, Integer>();
        for(int i = 0; i < endpointList.size(); i++){
            endpointMap.put(endpointList.get(i), i);   
        }
        //according to example, this would be (0,24)
        Node root = buildNode(0, endpointList.size() - 1);
        //the segment tree we setup actually is endpointsList idx
        for(int[] building : buildings){
            //
            add(root, /* start point idx */endpointMap.get(building[0]), 
                      /*end point idx */endpointMap.get(building[1]), 
                      /*height in original array*/building[2]);
        }

        List<int[]> result = new ArrayList<int[]>();
        explore(result, endpointList, root);
        //these are height = 0 key points
        if(endpointList.size() > 0){
            result.add(new int[]{endpointList.get(endpointList.size() - 1), 0});
        }
        return result;
    }
    // build the segment tree. from start point to last point 
    
    // for example, 0-5
/*
            (0, 5)
           /      \
        (0,2)     (2, 5)
       /    \       /   \
   (0,1) (1,2)  (2,3) (3, 5)
                       /   \
                     (3,4) (4,5)
 */
    public Node buildNode(int start, int end) {
        if (start > end) {
            return null;
        } else {
            Node result = new Node(start, end);
            // this is really important
            //so we build the tree which restrict leaf node has 1 diff
            // see above example
            if (start + 1 < end) {
                int center = start + (end - start) / 2;
                result.left = buildNode(start, center);
                result.right = buildNode(center, end);
            }

            return result;
        }
    }
    
    public static void main(String[] args) {
        TheSkylineProblem tt = new TheSkylineProblem();
        Node test = tt.buildNode(0, 12);
        System.out.println(test);
    }
    //we want to add this building(start, end, height) into the tree
    // actually we mainly want to find which nodes whould be assigned 
    //with corret height
    
    //
    private void add(Node node, int start, int end, int height) {
        //this means not in this range
        if (node == null || start >= node.end || end <= node.start || height < node.height) {
            return;
        } else {
            //we add height to each leaf node which its (start, end)
            //which has overlap with each building in buildings
            //for each non-leaf node,
            if (node.left == null && node.right == null) {
                node.height = Math.max(node.height, height);
            } else {
                // recursively add height to its node
                add(node.left, start, end, height);
                add(node.right, start, end, height);
                // we don;t need this line actually
                node.height = Math.min(node.left.height, node.right.height);
            }
        }
    }
    //we always will seek to smallest range first then bigger
    // each leaf node in segment tree is 1 diff, like 2->3, 
    private void explore(List<int[]> result, List<Integer> endpointList, Node node) {
        if (node == null) {
            return;
        } else {
            // we visit nodes until leaf node
            if (node.left == null && node.right == null
                    //avoid duplicate node values
                    && (result.size() == 0 
                    || result.get(result.size() - 1)[1] != node.height)) {
                result.add(new int[] {
                        endpointList.get(node.start), node.height });
            } else {
                explore(result, endpointList, node.left);
                explore(result, endpointList, node.right);
            }
        }
    }
}
