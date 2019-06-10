package hatecode;

public class GoogleSnapshot {
/*
 * Googe interview question, not from LC
 * Problem statement:
 * 实现三个functions， get, set, take snapshots。
 * 一开始三个数：  0 0 0
//这时版本数为 0
int set(0, 10)  // 10 0 0
int set(0, 12)  // 12 0 0
int takesnapshots() //这时版本数变成了1
int set(0, 9)  // 9 0 0
int takesnapshots() //这时版本数变成了2
int set(0, 3)  // 3 0 0
 
int get(0, 0)  // 第一参数为版本数，第二参数是index。则返回12。
int get(1, 0)  // 返回9
 */
    interface SnapShot {
        public int set(int sid, int index);
        public int get(int sid, int index);
        public int take();
    }
    
    //naive implementation can be use List<List<Integer>>, outter list is used to indicate sid, 
    //the innter list is used as index depends on a snapshot
    //
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
