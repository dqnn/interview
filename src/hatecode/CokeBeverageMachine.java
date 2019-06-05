package hatecode;

import java.util.*;
public class CokeBeverageMachine {
    /*
     * from Goole interview doc: https://docs.google.com/document/d/
     * 1qxA2wps0IhVRWULulQ55W4SGPMu2AE5MkBB37h8Dr58/edit#
     * 
     * problem statement:
     * 有一系列按钮，每个按钮按下去会得到一定体积范围的可乐。先给定一个目标体积范围，问不限制按按钮次数，
     * 能否确定一定能得到目标范围内的可乐？
     * 
     * 举例：有三个按钮，按下去得到的范围是[100, 120], [200, 240], [400, 410], 
     * 假设目标是[100, 110],那答案是不能。因为按下一，可能得到120体积的可乐，不在目标范围里。 
     * 假设目标是[90, 120]，那答案是可以。因为按下一，一定可以得到此范围内的可乐。
     * 假设目标是[300, 360], 那答案是可以，因为按下一再按二，一定可以得到此范围内 
     * 假设目标是[310, 360],那答案是不能，因为按下一再按二，有可能得到300，永远没可能确定得到这个范围内的可乐。 
     * 假设目标是[1,9999999999]，那答案是可以。随便按一个都确定满足此范围
     * 
     */
    class Soda{
        int lo;
        int hi;
    }
    //so the problem is to say given 
    public boolean isPossible(List<Soda> list, int lower, int upper) {
        
    }
    
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
            
    }

}
