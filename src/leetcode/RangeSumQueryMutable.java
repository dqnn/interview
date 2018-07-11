package leetcode;

import java.util.Arrays;

/**
 * Project Name : Leetcode Package Name : leetcode File Name :
 * RangeSumQueryMutable Creator : duqiang Date : Jan, 2018 Description : 307.
 * Range Sum Query - Mutable
 * 
 */
public class RangeSumQueryMutable {

    int[] nums;
    int[] tree;
    int n;

    // time : O(n * logn)
    public RangeSumQueryMutable(int[] nums) {
        n = nums.length;
        tree = new int[n + 1];
        this.nums = new int[n];
        for (int i = 0; i < n; i++) {
            update(i, nums[i]);
        }
    }

    // time : O(logn)
    public void update(int i, int val) {
        if (n == 0) return;
        int diff = val - nums[i];
        nums[i] = val;
        for (int j = i + 1; j <= n; j += j & (-j)) {
            tree[j] += diff;
        }
    }

    // time : O(logn)
    public int sumRange(int i, int j) {
        return sum(j + 1) - sum(i);
    }

    private int sum(int k) {
        int sum = 0;
        for (int i = k; i > 0; i -= i & (-i)) {
            sum += tree[i];
        }
        return sum;
    }

    /**
     * 解法2
     */
    //private int[] tree;
    //private int[] nums;


    public void NumArray(int[] nums) {
        this.nums = nums;
        int sum;
        int lowbit;
        tree = new int[nums.length + 1];
        for (int i = 1; i < tree.length; i++) {
            sum = 0;
            lowbit = i & (-i);
            for (int j = i; j > i - lowbit; j--) {
                sum = sum + nums[j - 1];
            }
            tree[i] = sum;
        }
    }

    public void update2(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        i++;
        for (; i < tree.length; i += (i & (-i))) {
            tree[i] += diff;
        }
    }

    public int sumRange2(int i, int j) {
        return sum2(j + 1) - sum2(i);
    }

    private int sum2(int k) {
        int sum = 0;
        for (int i = k; i > 0; i -= i & (-i)) {
            sum += tree[i];
        }
        return sum;
    }
    /*
     * 问题：求一个数组中连续n项的和。 首先想到的肯定是做一个循环，把这个连续的n项加起来，时间复杂度为O（n）。复杂度为n，
     * 看起来还不错，再说了求n个数的和，怎么也要加n次吧， 所以说这应该就是最优解了，但是一提交结果是Time Limit Exceeded，顿时傻眼了，
     * 难道还有复杂度更低的方法？
     * 
     * 会不会有O（logn）的解法？
     * O（n）的那个算法，如果只操作一次还是可以接受的，但是如果需要大量的求和操作，比如第一次求下标（1，1234）的和第二次求下标（2，1024）的和，
     * 很容易发现在第一次计算的过程中（2，1024）的和是计算过的，只是没有保存下来，导致第二次求和的时候还要再算一遍。你有没有想过，
     * 如果事先把一部分的和先计算并保存起来，这样会不会更快一些呢？
     * 
     * Binary Indexed Tree(BIT) 其实树状数组(Binary Indexed Tree(BIT), Fenwick
     * Tree)就是这样做的，他是一个查询和修改复杂度都为log(n)的数据结构。主要用于查询任意两位之间的所有元素之和，但是每次只能修改一个元素的值。
     * 
     * 核心思想: 树状数组中的每个元素是原数组中一个或者多个连续元素的和。
     * 在进行连续求和操作a[1]+…+a[n]时，只需要将树状数组中某几个元素的和即可。时间复杂度为O(lgn) 下面是一个示意图
     * 
     * 示意图
     * 
     * a[]: 保存原始数据的数组 e[]: 树状数组，其中的任意一个元素e[i]可能是一个或者多个a数组中元素的和。如e[2]=a[1]+a[2];
     * e[3]=a[3]，e[4]=a[1]+a[2]+a[3]+a[4]。 e[i]中的元素：如果数字 i
     * 的二进制表示中末尾有k个连续的0，则e[i]是a数组中2^k个元素的和，则e[i]=a[i-2^k+1]+a[i-2^k+2]+…+a[i-1]+a[i]
     * 。也就是说，e[i]中每一个元素管理着a[]中若干个元素的和，并且各个元素管理的区间没有重叠。
     * 
     * 
     * 
     * 如：4=100(2) e[4]=a[1]+a[2]+a[3]+a[4]; 6=110(2) e[6]=a[5]+a[6] 7=111(2)
     * e[7]=a[7]
     * 
     * 
     * 
     * 
     * 计算2^k的两个方法
     * 
     * 2^k = (i & (-i)); (利用机器补码特性) 2^k = (i & (i^(i-1)); 父节点
     * 是离它最近的，且编号末位连续0比它多的就是它的父亲,如e[2]是e[1]的儿子；e[4]是e[2]的儿子。 e[4] = e[2]+e[3]+a[4] =
     * a[1]+a[2]+a[3]+a[4] ，e[2]、e[3]的后继就是e[4]。
     * 
     * 计算方法 lowbit(i) = ( (i-1) ^ i) & i ; //或者(i & (-i)) 节点e[i]的父节点为 e[ i -
     * lowbit(i) ]
     * 
     * 子节点 最近的，编号即为比自己小的，最末连续0比自己多的节点。 如e[7]的子节点是e[6],e[6]的子节点是e[4]
     * 
     * 计算方法 lowbit(i) = ( (i-1) ^ i) & i ; //或者(i & (-i)) 节点e[i]的子节点为 e[ i +
     * lowbit(i) ]
     */

    int[] tree2 = null;
    int[] numss;
    int   len   = 0;

    public void NumArray3(int[] nums) {
        len = nums.length;
        this.numss = nums;
        tree = new int[len + 1];
        
        Arrays.fill(tree, 0); //actually we don't need this
        for(int i = 1; i< tree.length; i++) {
            int sum = 0;
            int lowbit = i &(-i); // or i & ((i - 1) ^ i);
            for(int j = i; j > i - lowbit; j--) {
                sum += nums[j - 1]; // tree index starting from 1 not 0; 
            }
            tree[i] = sum;  
        }
    }

    // belong to last solutions
    public void update3(int i, int val) {
        int tmp = val - numss[i];
        numss[i] = val;
        i++;
        for (; i < tree.length;) {
            tree[i] += tmp;
            i += i & (-i); // means child node index
        }
    }

    // last solutions
    public int sumRange3(int i, int j) {
        return getSum3(j) - getSum3(i - 1); // why previously all use j+1
    }
    /*
     * lowbit(i) = ( (i-1) ^ i) & i ; //或者(i & (-i)) 节点e[i]的子节点为 e[ i + lowbit(i) ]
     */

    public int getSum3(int index) {
        int sum = 0;
        index++;
        while (index > 0) {
            sum = sum + tree[index];
            index -= index & (-index); // go to parent node
        }
        return sum;
    }

}
