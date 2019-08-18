package leetcode.q15_k_sum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author ruby_
 * @create 2019-07-26-19:45
 * <p>
 * 解题思路： dfs 考虑从A[]前i个数里面选j个结果为t的情况, 其状态转移方程为：
 *      dp[i][j][t] = dp[i-1][j-1][t - A[i-1]]  + dp[i-1][j][t]
 *                      选第i个                        不选第i个
 * 知识点：
 * 1) 初始化的情况要注意：前i个选1个为t的情况，其实是考虑从 0 ~ (i - 1) 中选出所有为t的数所在位置
 * 2） java 复合集合类型的深拷贝问题： 深拷贝外层是没用的，必须拷贝内层以放置其他状态对其修改
 */

public class Solution3 {
    private static Logger LOG = LoggerFactory.getLogger(Solution3.class);

    //dps + memo

    // Memoize: <key_i, _j, _target>  ==> Set<Set<Integer>>
    //   first i integers select j sum equals target  ==>  positions of selected integers
    // e.g. A[0,1,1] : <3,2,1> ==> { {0, 1}, {0, 2}}
    private HashMap<Key, Set<Set<Integer>>> mem = new HashMap<>();

    /**
     * @param A:      An integer array
     * @param k:      A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        return ksum(A, k, target).size();
    }

    public Set<Set<Integer>> ksum(int[] A, int k, int target) {
        int len = A.length;

        //initialize: i (1 ~ len) 之前取1个数等于某个值的情况就是它自己和一切在它之前跟它相等的数！
        for (int i = 1; i <= len; i++) {
            for (int p = 0; p < i; p++) {
                Key key = new Key(i, 1, A[p]);

                if (mem.isEmpty() || !mem.containsKey(key)) {
                    Set<Set<Integer>> r = new HashSet<>();
                    r.add(new HashSet<>(Arrays.asList(p)));
                    mem.put(key, r);
                } else {
                    mem.get(key).add(new HashSet<>(Arrays.asList(p)));
                }
            }
        }

        Key key = new Key(len, k, target);
        dfs(A, key);

        return mem.get(key);
    }

    /**
     * 以 dfs 不断进行前n个数选k个和为t的 操作，并记录中间结果
     *
     * @param A : 数组
     * @Key key:
     */
    private void dfs(int[] A, Key key) {

        if (key.inValid() || (!mem.isEmpty() && mem.containsKey(key))) return;

        int n = key._i; //当前考虑前n个数字

        //取第n个数字的可能性
        Key key1 = key.preWithCurrent(A[n - 1]);
        dfs(A, key1);
        if (mem.containsKey(key1)) memoize(key1, key, true);

        //不取第n个数字的可能性
        Key key2 = key.preWithoutCurrent();
        dfs(A, key2);
        if (mem.containsKey(key2)) memoize(key2, key, false);
    }

    /**
     * 根据 是否要包含进当前key所对应的最后一个数而决定如何生成中间结果
     * @param pre: 前 i-1 选 j-1 和为 t - A[i-1] 所生成的key
     * @param cur： 前 i-1 选 j 和为 t 所生成的key
     * @param hasCur
     */
    private void memoize(Key pre, Key cur, boolean hasCur) {
        Set<Set<Integer>> res = new HashSet<>();
        for (Set<Integer> s : mem.get(pre)) {
            Set<Integer> r = new HashSet<>(s); //todo: 注意此处要深拷贝！

            if (hasCur) r.add(cur._i - 1); //增加记录当前考虑的子数组最后一个数的位置

            if (mem.containsKey(cur)) mem.get(cur).add(r);
            else res.add(r);
        }

        if (!mem.containsKey(cur)) mem.put(cur, res);
    }

    //前i个选j个和为target
    private class Key {
        int _i;
        int _j;
        int _target;

        Key(int i, int j, int target) {
            _i = i;
            _j = j;
            _target = target;
        }

        boolean inValid() {
            return (_i < 1 || _j < 1 || _i < _j);
        }

        Key preWithCurrent(int d) {
            return new Key(_i - 1, _j - 1, _target - d);
        }

        Key preWithoutCurrent() {
            return new Key(_i - 1, _j, _target);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = result * 31 + _i;
            result = result * 31 + _j;
            result = result * 31 + _target;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || obj.getClass() != getClass()) return false;
            Key that = (Key) obj;
            return (this._i == that._i && this._j == that._j && this._target == that._target);
        }
    }
}
