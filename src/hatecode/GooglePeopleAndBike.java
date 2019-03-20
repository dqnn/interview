package hatecode;

public class GooglePeopleAndBike {
/*
Question:
2D平面上，有m个人（P），n辆自行车(B)，还有空白（O）满足以下条件
1.m < n
2.不存在两个人，到同一辆自行车距离相等, 距离用abs(x1-x2) + abs(y1-y2)定义
3.每个人尽量找离自己最近的自行车，一旦某辆自行车被占，其他人只能找别的自行车。
例：
OPOBOOP
OOOOOOO
OOOOOOO
OOOOOOO
BOOBOOB

(0,1)->(0,3)
(0,6)->(5,6)
 */
    
    //first question, if there was no tie, return List of Pair, bike and People match
    //we need Bike < Person count, 
    
    
    
    //Second question, if we want overall best match, we need to use Hungarian algorithm
    
    
    
    /*
     follow up版本1: 一组坐标表示人，另一组表示车，车比人多，其中人和车的距离有tie（距离两个人最近的车可能是同一辆），给每个人匹配一辆车，要求所有匹配的人车曼哈顿距离加起来最小（全局最优）。
这一问原题的两种方法基本全部gg，因为要求全局最优并且有tie，于是每个人不一定是匹配到距离自己最近的车子。pq方法完全失效，bfs方法无法保证全局最优（距离一个人最近的车可能有多辆，然而单凭bfs无法确定给此人匹配哪辆可以全剧最优）。暴力搜索全部匹配方式，找最小总距离的匹配方式可以确保正确性，但是车和人很多的时候，时间复杂度会很高。目前个人认为这一题面试官的期待做法，应该就是二分图最小带权匹配，KM算法，但是鉴于面试的时候可能很难写出，所以在此希望大家讨论一下有没有其他稍微简单点的办法，因为和正常的二分图匹配不一样，这个已经告诉你那些节点属于哪一边了。

follow up版本2: 一组坐标表示人，另一组表示车，车比人多，其中人和车的距离有tie（距离两个人最近的车可能是同一辆），给每个人匹配一辆车，要求匹配后最大的人车距离最小。
这一问和前面的关系似乎不是很大，不过万能的暴力dfs还是能做，全部匹配方法写出来，找最长距离最小的那个，就是答案，不过和前面一样，没有非常有效的剪枝方法，复杂度很高，所以面试官也不会满意（我同学面试答了这种方法挂掉了）。感觉可以用dp来做，但是没有想出很好的状态表示和转移方程，希望大家讨论！！！！

     */
    
    
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
