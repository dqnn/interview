package hatecode._0001_0999;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *     
    //this is from goog interview question:
    /*
     * {a, b}c{d,e}f 需要return 所有可能的组合，acdf，acef，bcdf，bcef。
     * follow-up 怎么处理 nested case:    a{b{c, d}e{f}}
 */

class CartesianProduct {
    public static void main(String[] args) {
//        String s = "{c {a,b}}f";
//        String s = "{x, y}{c{a,b, {A, B}}}{d,e}";

//        String[] strs = new String[]{
//                "{a}", "{a, b}", "{a, b}c", "a{b, c}", "a{b, c}d", "{a, b}{c, d}", "{a, b{c, d}{e, f}}"
//        };


//        String s = "{a,{b, {c, d{e}}}}";
//        String s = "ab{c, {de, f}gh}";
//        String s = "{a}b{de, f}";
//        String s = "test{a}{b}{de, f}";
//        String s = "{a, , d}";
        String s = "{a, {b,c}{d,e}}";

        GoolgeCartesianProduct cartesianProduct = new GoolgeCartesianProduct();
        List<String> rst = GoolgeCartesianProduct.cartesion(s);
        System.out.println("rst = " + rst);
    }
}

public class GoolgeCartesianProduct {
    public static List<String> cartesion(String s) {
        if (s == null) {
            return new ArrayList<>();
        }

        Queue<Character> queue = getQueue(s);
        System.out.println("begin = " + queue);

        List<String> lst = compute(queue);


        /**
         *  递归必须携带括号，原因是，必须携带block信息
         *  最终返回结果时，去掉括号即可
         */

        List<String> rst = build(lst);
        return rst;
    }

    private static List<String> build(List<String> lst) {
        List<String> rst = new ArrayList<>();
        for (String str : lst) {
            if (str.equals("{") || str.equals("}") || str.equals(",")) {
                continue;
            }

            rst.add(str);
        }

        return rst;
    }

    /**
     * 结果只携带首尾括号
     *
     * @param queue
     * @return
     */
    private static List<String> compute(Queue<Character> queue) {
        List<String> lst = new ArrayList<>();
        lst.add("{");
        String alpha = "";

        while (!queue.isEmpty()) {
            char c = queue.poll();

            if (Character.isLetter(c)) {
                alpha += c;
            } else {
                if (c == ',') {     //  alpha结束，需要进入当前层，
                    /**
                     *  进入之前，需要讨论是直接放入，还是与前面的block进行累乘
                     *      1. 若lst的最后一个是"{"，或者最后一个是","，则说明是累加关系
                     *      2. 若lst的最后一个是"}"，说明是累乘关系，则提取最后的block，累乘
                     */
                    int last = lst.size() - 1;
                    if (lst.get(last).equals("{") || lst.get(last).equals(",")) {    //  累加型alpha, { 或者 {ab,c,
                        lst.add(alpha);
                        lst.add(",");
                        alpha = "";
                    } else {    //  累乘型, {a, {bc, d} alpha,
                        if (lst.get(last).equals("}")) {        //  累乘alpha，找出最后一个block，进行累乘
                            int idx = lst.lastIndexOf("{");
                            List<String> prev = getLastBlock(lst, idx);

                            List<String> newList = getNewList(alpha, prev);
                            lst.addAll(newList);
                            lst.add(",");
                            alpha = "";
                        }
                    }
                } else if (c == '{') {  //  alpha结束，同时，新一层的开始，递归调用得到下一层的结果，next；然后把next放入当前
                    /**
                     *  1. 先判断alpha == ""
                     *      1. 相等
                     *          说明没有alpha需要进入当前层，直接处理next，此时需要讨论当前层lst的尾巴是什么，即next与lst尾巴的关系
                     *              1.1 若lst的最后一个是","，或者是"{"，说明next与当前层时累加关系
                     *              1.2 若lst的最后一个是"}"，则说明是累乘关系
                     *              1.3 若lst的最后一个是alpha，则说明是累乘关系
                     *      2. 不相等
                     *          说明存在非空的alpha，需要进入当前层，则先处理alpha，再处理next
                     *              2.1 处理alpha
                     *                      2.1.1   若lst的最后一个是"{"，或者最后一个是","，则说明是累加关系
                     *                      2.1.2   若lst的最后一个是"}"，说明是累乘关系，提出前面的block，累乘，
                     *              2.2 处理next
                     *                      2.2.1   若lst的最后一个是"{"，或者最后一个是","，则说明是累加关系
                     *                      2.2.2   若lst的最后一个是"}"，说明是累乘关系，提出前面的block，累乘，
                     *                      2.2.3   若lst的最后一个是字符串，说明是累乘关系，提出前面的block，累乘，
                     *
                     *  2. 其次，再处理next
                     */

                    List<String> next = compute(queue);

                    if (alpha.equals("")) {
                        int last = lst.size() - 1;
                        if (lst.get(last).equals("{") || lst.get(last).equals(",")) {
                            lst.addAll(next);
                        } else {    //  处理前一个为block或者字符串的情况，即{}{}, a{}
                            if (lst.get(last).equals("}")) {        //  block
                                int idx = lst.lastIndexOf("{");
                                List<String> prev = getLastBlock(lst, idx);

                                List<String> newList = getNewList(prev, next);
                                lst.addAll(newList);
                            } else {
                                String prev = lst.get(lst.size() - 1);
                                lst.remove(lst.size() - 1);

                                List<String> newList = getNewList(prev, next);
                                lst.addAll(newList);


                            }
                        }
                    } else {        //  alpha不为空，则先处理alpha，再处理next
                        int last = lst.size() - 1;
                        if (lst.get(last).equals("{") || lst.get(last).equals(",")) {     //  alpha与前面是加法关系
                            lst.add(alpha);
                            alpha = "";

                            /**
                             *  加入alpha后，再处理next
                             */
                            String prev = lst.get(lst.size() - 1);
                            lst.remove(lst.size() - 1);

                            List<String> newList = getNewList(prev, next);
                            lst.addAll(newList);

                        } else {
                            /**
                             *  alpha与前面是乘法关系，则找到前一个block，与alpha做乘法，得到的结果再与next做乘法
                             */

                            //  1. 找到前一个block
                            int idx = lst.lastIndexOf("{");                         //  乘法关系，把alpha与前一个block相乘
                            List<String> prev = getLastBlock(lst, idx);

                            //  2. 前一个block与alpha做乘法，得到newList
                            List<String> newList = getNewList(alpha, prev);
                            alpha = "";

                            //  3. newList再与next做乘法，加入到当前的lst中
                            List<String> newNext = getNewList(newList, next);
                            lst.addAll(newNext);
                        }
                    }


                } else if (c == '}') {  //  alpha结束，同时，当前层结束，退出while循环，整理结果，向上返回
                    /**
                     *  表示innermost 递归结束
                     *  1. 先判断alpha==""
                     *      1. 相等
                     *          说明没有alpha需要进入当前层，直接考虑结束当前层的递归
                     *              1.1 当前层lst的尾巴是"{"，说明该block是空，
                     *              1.2 当前层lst的尾巴是"}"， 提取出block，解析
                     *                      ！！！【该解析需要只保留该层最外侧的括号】
                     *      2. 不等
                     *          说明存在非空的alpha，需要进入当前层，则先处理alpha，再处理next
                     *              2.1 处理alpha
                     *                      2.1.1 若lst的最后一个是"{"，或者最后一个是","，则说明是累加关系
                     *                      2.1.2 若lst的最后一个是"}"，说明是累乘关系，提出前面的block，累乘
                     *              2.2 处理递归结束
                     *
                     */

                    if (alpha.equals("")) {     //  alpha为空，则加入尾括号，跳出循环，结束递归
                        lst.add("}");
                        break;
                    } else {        //  alpha不为空，则处理alpha
                        /**
                         *  innermost的最后的alpha是加法关系
                         */
                        int last = lst.size() - 1;
                        if (lst.get(last).equals("{") || lst.get(last).equals(",")) {
                            lst.add(alpha);
                            lst.add("}");
                            alpha = "";
                            break;
                        } else if (lst.get(last).equals("}")) {     //  嵌套关系，则先把alpha与前一个block做乘法，然后，做退出循环的处理
                            int idx = lst.lastIndexOf("{");
                            List<String> prev = getLastBlock(lst, idx);

                            List<String> newList = getNewList(alpha, prev);
                            lst.addAll(newList);
                            lst.add("}");
                            break;
                        }
                    }
                }
            }
        }

        /**
         *  保留最外侧括号的方法
         *      1. 重建一个结果集rst，rst加入左括号
         *      2. 加入lst中的元素，当lst中元素为括号是，忽略
         *      3. rst最后加入右括号
         */

        List<String> rst = new ArrayList<>();
        rst.add("{");

        for (String s : lst) {
            if (s.equals("{") || s.equals("}") || s.equals(",")) {
                continue;
            }

            rst.add(s);
            rst.add(",");
        }

        rst.remove(rst.size() - 1);
        rst.add("}");

        return rst;
    }

    private static List<String> getNewList(String str, List<String> next) {
        List<String> rst = new ArrayList<>();
        rst.add("{");

        for (String s : next) {
            if (s.equals("{") || s.equals("}") || s.equals(",")) {
                continue;
            }

            String ss = str + s;
            rst.add(ss);
            rst.add(",");
        }

        rst.remove(rst.size() - 1);
        rst.add("}");

        return rst;
    }

    private static List<String> getNewList(List<String> prev, List<String> next) {
        if (prev.size() == 2) {
            return next;
        }

        if (next.size() == 2) {
            return prev;
        }

        List<String> rst = new ArrayList<>();
        rst.add("{");

        for (String sp : prev) {
            if (sp.equals("{") || sp.equals("}") || sp.equals(",")) {
                continue;
            }

            for (String sn : next) {
                if (sn.equals("{") || sn.equals("}") || sn.equals(",")) {
                    continue;
                }

                String s = sp + sn;
                rst.add(s);
                rst.add(",");
            }
        }

        rst.remove(rst.size() - 1);
        rst.add("}");
        return rst;
    }

    /**
     * 获取rst的同时，删除lst的尾巴
     * 即lst最后的{}已被删除
     * rst携带括号
     *
     * @param lst
     * @param idx
     * @return
     */
    private static List<String> getLastBlock(List<String> lst, int idx) {
        List<String> rst = new ArrayList<>();
        for (int i = idx; i < lst.size(); i++) {
            rst.add(lst.get(i));
        }

        for (int i = 0; i < rst.size(); i++) {
            lst.remove(lst.size() - 1);
        }

        return rst;
    }

    private static Queue<Character> getQueue(String s) {
        s = s.replaceAll(" ", "");
        Queue<Character> queue = new LinkedList<>();

        queue.offer('{');
        for (char c : s.toCharArray()) {
            queue.offer(c);
        }

        queue.offer('}');
        return queue;
    }

}
