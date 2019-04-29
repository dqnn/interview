package hatecode;
import java.util.*;
public class ShutTheBox {
/*
这题是有概率的游戏问题。
可以网上搜一下shut the box怎么玩
这里假设是一个人玩，每次掷两个骰子， 要把数字1-9消掉。消掉算你赢，消不掉算你输。
假设掷了一个 7， 7可以用1,6 ， 也可以用3,4，也可以直接用7。具体用哪种要算一下取了哪种之后胜率最高
这里dp[state][i], 表示当我还剩state里包含的数字时我掷出了 “i” 这个数时的胜率。
枚举各种情况， 取使下一步胜率最大的那一支。
具体实现的时候由于很多状态变不是合法的根本没必要算。
所以没使用DP来做，使用了从大到小的recursion with memorization。 这样不必要的状态就不用算一遍了
初始化的时候先算好所以情况下应该怎么办存下来，具体游戏玩到哪一步的时候就直接查表就可以了。
一个文献如下  http://www.durangobill.com/ShutTheBox.html
经测试下面这个程序的1千万次的胜率7.147%，和文献中的期望值完美match上
这里面容易出现的bug。
我们的数字是从1到9的，可是计算机里的数字是从 0开始的。这面有一个 1 的offset。这样，如果state是 0 或者state是 1，都是成功的状态 ， 因为第0位的状态没有意义。
这题真的很容易写错。 我第二次写的时候也debug了好久。
 */
    int STATE; // using bit to represent which bits are available
    Double[][] probability; // memorization
    double[] numberProb; // from 2 to 12, the probability of each number
    List[][] combinations;
    // recursion with memorization
    public ShutTheBox(){
        STATE = 0;
        for (int i = 1; i <= 9; i++) {
            STATE += (1 << i);
        }

        probability = new Double[STATE + 1][13]; // only 2 to 12 is meaningful
        combinations = new List[STATE + 1][13];
        numberProb = calNumberProb();
        for (int c = 2; c <= 12; c++ ) dfs(STATE, c, probability);
    }

    private double dfs(int curState, int c, Double[][] probablity) {
        // recursion with memorization
        if (probablity[curState][c] != null) return probablity[curState][c];
        //base case;
        if (curState == 0) {
            probablity[curState][c] = 1.0;
            return 1.0;
        }

        // find combinations that can construct c from state
        // c is the number from rolling the dice
        List<List<Integer>> combs = findComb(curState, c);
        if (combs.size() == 0) {
            probablity[curState][c] = 0.0;
            return 0.0;
        }
        double bestProb = 0.0;
        for (List<Integer> comb : combs) {
            int nextState = getNextState(curState, comb);
            double localProb = 0.0;
            for (int nc = 2; nc <= 12; nc++) {
                localProb += numberProb[nc] * dfs(nextState, nc, probablity);
            }
            if (localProb > bestProb) {
                bestProb = localProb;
                combinations[curState][c] = comb;
            }
        }
        probablity[curState][c] = bestProb;
        return bestProb;
    }

    private List<List<Integer>> findComb(int curState, int c) {
        List<Integer> choices = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if ((curState & (1 << i)) > 0) {
                choices.add(i);
            }
        }
        List<List<Integer>> combinations = new ArrayList<>();
        findCombDfs(choices, c, 0, combinations, 0, new ArrayList<>());
        return combinations;
    }

    private void findCombDfs(List<Integer> choices, int c, 
                    int index, List<List<Integer>> combinations, 
                    int sum, List<Integer> list) {
        if (sum > c || c < 2) return;
        if (sum == c) {
            combinations.add(new ArrayList<>(list));
            return;
        }
        for (int i = index; i < choices.size(); i++) {
            if (sum + choices.get(i) <= c) {
                list.add(choices.get(i));
                findCombDfs(choices, c, i + 1, 
                              combinations, sum + choices.get(i), list);
                list.remove(list.size() - 1);
            }
        }
    }

    private int getNextState(int curState, List<Integer> comb) {
        for (int n : comb)  curState -= (1 << n);
        return curState;
    }

    public double[] calNumberProb() {
        double[] ans = new double[13];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                ans[i + j] += 1.0 / 6.0 /6.0;
            }
        }
        return ans;
    }

    public List<Integer> pick(int curState, int c) {
        return combinations[curState][c];
    }

    /////// Test code

    public static Random random;
    public static int rollDice() {
        return random.nextInt(6) + random.nextInt(6) + 2;
    }
    public static void main(String[] args) {
        ShutTheBox stb = new ShutTheBox();
        random = new Random();
        int trialNumber = 10000;
        int cnt = 0;
        for (int i = 0; i < trialNumber; i++) {
            if (runOnce(stb)) cnt++;
        }
        System.out.println(cnt * 1.0 / trialNumber);
    }

    public static boolean runOnce(ShutTheBox stb) {
        int curState = stb.STATE;
        while (true) {
            int c = rollDice();
            List<Integer> comb = stb.pick(curState, c);
            if (comb == null || comb.size() == 0) {
                return false;
            } else {
                curState = stb.getNextState(curState, comb);
                if (curState == 0) {
                    return true;
                }
            }
        }
    }
}
//second time code
class ShutTheBox2 {
    int[][] nextStates;
    Double[][] chanceToWin;
    double[] sumChance;
    public ShutTheBox2() {
        // constructor
        //precompute and calculate the next step
        int nOfState = (1 << 10 ); // 1 -> 2, 2 -> 4
        nextStates = new int[nOfState][13]; // 1 to 13
        chanceToWin = new Double[nOfState][13];
        for (int r = 0; r < nOfState; r++) {
            for (int c = 0; c < 13; c++) {
                nextStates[r][c] = -1; // -1 means never computed before
                // -2 means computed and no good state
            }
        }
        sumChance = new double[13];
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) sumChance[i + j] += 1.0/36;
        }

        for (int diceSum = 2; diceSum <= 12; diceSum ++) {
            getBestChoice(nOfState - 1, diceSum);
        }
    }
    public List<Integer> pickCombination(int state, int diceSum) {
        if (nextStates[state][diceSum] == -2) return null;
        // if null: lost;
        return getDiff(state, nextStates[state][diceSum]);
    }
    private double getBestChoice(int state, int diceSum) {
        if (nextStates[state][diceSum] != -1) return chanceToWin[state][diceSum];
        // base case;
        if (state == 0 || state == 1) {
            chanceToWin[state][diceSum] = 1.0;
            nextStates[state][diceSum] = 0;
            return 1.0;
        }
        List<List<Integer>> choicesOfCombination = getChoicesOfCombination(state, diceSum);

        //among the choices, pick the best one
        // the one which have best expectation;
        double bestExpectation = 0.0;
        int  bestNextState = -2;
        for (List<Integer> comb : choicesOfCombination) {
            int nextState = getNextState(state, comb);
            double expectation = 0.0;
            for (int c = 2; c <= 12; c++) {
                 // recursive call
                expectation += sumChance[c] * getBestChoice(nextState, c);
            }
            if (expectation >= bestExpectation) {
                bestExpectation = expectation;
                bestNextState = nextState;
            }
        }
        chanceToWin[state][diceSum] = bestExpectation;
        nextStates[state][diceSum] = bestNextState;
        return bestExpectation;
    }

    private List<List<Integer>> getChoicesOfCombination(int state, int diceSum) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            if ((state & (1 << i)) != 0) nums.add(i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        dfs(nums, ans, 0, diceSum, 0, new ArrayList<>());
        return ans;
    }
    private void dfs(List<Integer> nums, List<List<Integer>> ans, int index, int target, int curSum, List<Integer> path) {
        if (curSum > target) return;
        if (curSum == target) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = index; j < nums.size(); j++) {
            path.add(nums.get(j));
            dfs(nums, ans, j + 1, target, curSum + nums.get(j), path);
            path.remove(path.size() - 1);
        }
    }

    private int getNextState(int state, List<Integer> comb) {
        for (int b : comb)  state -= (1 << b);
        return state;
    }
    private List<Integer> getDiff(int state, int nextState) {
        List<Integer> ans = new ArrayList<>();
        if (nextState == -2) return null;
        for (int i = 1; i <= 9; i++) {
            int mask = 1 << i;
            if ((mask & state) > 0 &&( (mask & nextState) == 0)) {
                ans.add(i);
            }
        }
        return ans;
    }

    //Test

    public static Random random;
    public static int rollDice() {
        return random.nextInt(6) + random.nextInt(6) + 2;
    }
    public static void main(String[] args) {
        ShutTheBox2 stb = new ShutTheBox2();
        random = new Random();
        int trialNumber = 10000000;
        int cnt = 0;
        for (int i = 0; i < trialNumber; i++) {
            if (runOnce(stb)) cnt++;
        }
        System.out.println(cnt * 1.0 / trialNumber);
    }
    public static void printState(int state) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) if ((state & (1 << i)) > 0) ans.add(i);
        System.out.println(ans);
    }
    public static boolean runOnce(ShutTheBox2 stb) {
        int curState = (1 << 10) - 1;
        //printState((1<< 10) - 1);
        while (true) {
            //System.out.println(curState);
            //printState(curState);
            int c = rollDice();
            List<Integer> comb = stb.pickCombination(curState, c);
            //System.out.println(c);
            //System.out.println(comb);
            if (comb == null || comb.size() == 0) {
                return false;
            } else {
                curState = stb.getNextState(curState, comb);
                if (curState <= 1) {
                    return true;
                }
            }
        }
    }
}
