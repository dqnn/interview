package hatecode._0001_0999;

import java.util.*;

public class _403FrogJump {
/*
403. Frog Jump
A frog is crossing a river. The river is divided into x units and at each unit 
there may or may not exist a stone. The frog can jump on a stone, but it must not 
jump into the water.

Given a list of stones' positions (in units) in sorted ascending order, determine
 if the frog is able to cross the river by landing on the last stone. Initially, 
 the frog is on the first stone and assume the first jump must be 1 unit.

If the frog's last jump was k units, then its next jump must be either k - 1, k, 
or k + 1 units. Note that the frog can only jump in the forward direction.

Note:

The number of stones is ≥ 2 and is < 1,100.
Each stone's position will be a non-negative integer < 231.
The first stone's position is always 0.
Example 1:

[0,1,3,5,6,8,12,17]

There are a total of 8 stones.
The first stone at the 0th unit, second stone at the 1st unit,
third stone at the 3rd unit, and so on...
The last stone at the 17th unit.

Return true. 
 */
    public class Node{
        int index;
        int preJump;
        public Node(int index, int preJump) {
            this.index = index;
            this.preJump = preJump;
        }
        //for debug purpose
        public String toString(){
            return index +"-" + preJump;
        }
    }
    public boolean canCross(int[] s) {
        if (s == null || s.length < 2 || s[1] > 1) {
            return false;
        }
        Queue<Node> q = new PriorityQueue<>((a,b)->(b.index - a.index));
        Set<String> visited = new HashSet<>();
        Node first = new Node(1,1);
        visited.add(first.index +"#" + first.preJump);
        q.offer(first);
        while(!q.isEmpty()){
            Node node = q.poll();
            int i = node.index;
            int jump = node.preJump;
            int[] dis = {s[i] + jump-1, s[i] + jump, s[i] + jump + 1};
            for(int d : dis) {
                if (d == s[s.length -1]) return true;
            }
            int j =i + 1;
            while(j < s.length && s[j] <= dis[2]) j++;
            for(int k = i+1; k < j;k++) {
                if (dis[0] == s[k]) {
                    Node node1 = new Node(k, jump -1);
                    if (!visited.contains(k +"#" + (jump -1))) {
                        visited.add(k +"#" + (jump -1));
                        q.offer(node1);
                    }
                } else if (dis[1] == s[k]) {
                    Node node1 = new Node(k, jump);
                    if (!visited.contains(k +"#" + jump)) {
                        visited.add(k +"#" + jump);
                        q.offer(node1);
                    }
                } else if (dis[2] == s[k]) {
                    Node node1 = new Node(k, jump +1);
                    if (!visited.contains(k +"#" + (jump +1))) {
                        visited.add(k +"#" + (jump+1));
                        q.offer(node1);
                    }
                }
            }
        }
        return false;
    }
    //another good solution, so we setup a map s[1]=[3,4,5], this means on s[1]
    //we can jump 3, 4 or 5 moves, so as we visit the array, we have several options
    //and keep updating the map, as we scan stone, we just need to check current
    //for current stone, whether i reached the last one or how many options have, and update
    //each of them, if we cannot find in the map which means fall into water
    //O(n)/O(n)
    public boolean canCross2(int[] stones) {
        if (stones.length == 0) {
            return true;
        }
        //[0,1,3,5,6,8,12,17] for examples, stores each value, its next possible moves
        //{17=[], 0=[1], 1=[1, 2], 3=[1, 2, 3], 5=[1, 2, 3], 
        //6=[1, 2, 3, 4], 8=[1, 2, 3, 4], 12=[3, 4, 5]}
        HashMap<Integer, Set<Integer>> map = new HashMap<>(stones.length);
        //here is to boostrap the process, make first jump
        map.computeIfAbsent(0, v->new HashSet<>()).add(1);
        Arrays.stream(stones).forEach(e->map.computeIfAbsent(e, v->new HashSet<>()));
        
        for (int stone : stones) {
            for (int step : map.get(stone)) {
                int reach = step + stone;
                if (reach == stones[stones.length - 1]) {
                    return true;
                }
                if (map.containsKey(reach)) {
                    map.get(reach).add(step);
                    map.get(reach).add(step + 1);
                    if (step > 1) map.get(reach).add(step - 1);
                }
            }
        }
        return false;
    } 
    
    //DP solution, O(n^2)/O(n^2), best solution
    //so the key point is that first jump must be 1, so 
    //we at each step, we have conclusion that the max jump is 
    //n = stones.length, so we use n + 1 length,
    //so we for range (i, j) in stones, we find the diff which means the jump we need,
    //so we mark in step i, we can jump diff, diff - 1 and diff + 1
    public boolean canCross_DP(int[] stones) {
        int N = stones.length;
        //dp[i] means on stone[i], how many possible jumps we can make,
        boolean[][] dp = new boolean[N][N + 1];
        dp[0][1] = true;
        
        for(int i = 1; i < N; ++i){
            for(int j = 0; j < i; ++j){
                int diff = stones[i] - stones[j];
                if(diff < 0 || diff > N || !dp[j][diff]) continue;
                dp[i][diff] = true;
                if(diff - 1 >= 0) dp[i][diff - 1] = true;
                if(diff + 1 <= N) dp[i][diff + 1] = true;
                if(i == N - 1) return true;
            }
        }

        return false;
    }
 /*
 expalin for above solution: 
                +----+    +----+        +----+     +----+       
stone:          | S1 |    | S2 |        | S3 |     | S4 | 
            ____|____|____|____|________|____|_____|____|____________
           ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
position:"         0         1             3          5             "        

jump size:         1     [0, 1, 2]     [1, 2, 3]

// Suppose we want to know if the frog can reach stone 2 (S2),
// and we know the frog must come from S1, 
// dist(S1->S2) = 1 - 0 = 1, and we already know the frog is able to make a jump of size 1 at S1.
// Hence, the frog is able to reach S2, and the next jump would be 0, 1 or 2 units.


// Then, we want to know if the frog can reach stone 3 (S3),
// we know the frog must be at either S1 or S2 before reaching S3,

// If the frog comes from S1, then 
// we know dist(S1->S3) = 3 - 0 = 3, and we know frog couldn't make a jump of size 3 at S1.
// So it is not possible the frog can jump from S1 to S3.

// If the frog comes from S2, then
// we know dist(S2->S3) = 3 - 1 = 2, and we know frog could make a jump of size 2 at S2.
// Hence, the frog is able to reach S3, and the next jump would be 1, 2 or 3 units.

// If we repeat doing this for the rest stones, we'll end with something like below:
Exapme 1:
            
index:        0   1   2   3   4   5   6   7 
            +---+---+---+---+---+---+---+---+
stone pos:  | 0 | 1 | 3 | 5 | 6 | 8 | 12| 17|
            +---+---+---+---+---+---+---+---+
k:          | 1 | 0 | 1 | 1 | 0 | 1 | 3 | 5 |
            |   | 1 | 2 | 2 | 1 | 2 | 4 | 6 |
            |   | 2 | 3 | 3 | 2 | 3 | 5 | 7 |
            |   |   |   |   | 3 | 4 |   |   |
            |   |   |   |   | 4 |   |   |   |
            |   |   |   |   |   |   |   |   |

// Sub-problem and state:
let dp(i) denote a set containing all next jump size at stone i

// Recurrence relation:
for any j < i,
dist = stones[i] - stones[j];
if dist is in dp(j):
    put dist - 1, dist, dist + 1 into dp(i). 

// Now lets make this approach more efficient.
// BECAUSE 
// 1. The number of stones is ≥ 2 and is < 1,100. 
// 2. The frog is on the first stone and assume the first jump must be 1 unit.
// 3. If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units,

// The maximum jump size the frog can make at each stone if possible is shown as followings: 
// stone:      0, 1, 2, 3, 4, 5
// jump size:  1, 2, 3, 4, 5, 6 (suppose frog made jump with size k + 1 at each stone)

// So instead of creating a HashSet for lookup for each stone, 
// we can create a boolean array with size of N + 1 (N is the number of stones),
// Like in the given example, at stone 2 the next jump could be 1, 2, 3, 
// we can use a bool array to represent this like
// index:    0  1  2  3  4  5  6  7  ...
//          [0, 1, 1, 1, 0, 0, 0, 0, ...]
// index is jump size, boolean value represents if the frog can make this jump.

// Then, the 2D array will be something like below.

index:        0   1   2   3   4   5   6   7 
            +---+---+---+---+---+---+---+---+
stone pos:  | 0 | 1 | 3 | 5 | 6 | 8 | 12| 17|
            +---+---+---+---+---+---+---+---+
k:        0 | 0 | 1 | 0 | 0 | 1 | 0 | 0 | 0 |
          1 | 1 | 1 | 1 | 1 | 1 | 1 | 0 | 0 |
          2 | 0 | 1 | 1 | 1 | 1 | 1 | 0 | 0 |
          3 | 0 | 0 | 1 | 1 | 1 | 1 | 1 | 0 |
          4 | 0 | 0 | 0 | 0 | 1 | 1 | 1 | 0 |
          5 | 0 | 0 | 0 | 0 | 0 | 0 | 1 | 1 |
          6 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 |
          7 | 0 | 0 | 0 | 0 | 0 | 0 | 0 | 1 |

// Sub-problem and state:
let dp[i][j] denote at stone i, the frog can or cannot make jump of size j

// Recurrence relation:
for any j < i,
dist = stones[i] - stones[j];
if dp[j][dist]:
    dp[i][dist - 1] = ture
    dp[i][dist] = ture
    dp[i][dist + 1] = ture
  */
}