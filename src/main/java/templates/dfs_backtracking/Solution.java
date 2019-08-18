package templates.dfs_backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Backtracking:
 *
 * backtracking（回溯算法）也叫试探法，它是一种系统地搜索问题的解的方法。回溯算法的基本思想是：从一条路往前走，能进则进，不能进则退回来，换一条路再试。
 * 回溯算法说白了就是穷举法。不过回溯算法使用剪枝函数，剪去一些不可能到达最终状态（即答案状态）的节点，从而减少状态空间树节点的生成。
 * 回溯法是一个既带有系统性又带有跳跃性的的搜索算法。它在包含问题的所有解的解空间树中，按照深度优先的策略，从根结点出发搜索解空间树。算法搜索至解空间树的任一结点时，
 * 总是先判断该结点是否肯定不包含问题的解。如果肯定不包含，则跳过对以该结点为根的子树的系统搜索，逐层向其祖先结点回溯。否则，进入该子树，继续按深度优先的策略进行搜索。
 *
 * 回溯法在用来求问题的所有解时，要回溯到根，且根结点的所有子树都已被搜索遍才结束。
 * 而回溯法在用来求问题的任一解时，只要搜索到问题的一个解就可以结束。
 * 这种以深度优先的方式系统地搜索问题的解的算法称为回溯法，它适用于解一些组合数较大的问题。
 *
 * 参考：
 *
 * Q. 46
 * Q. 47
 */

public class Solution {
    private List<List<Integer>> res = new ArrayList<List<Integer>>();

    public void helper(int[] nums) {
        helper(new ArrayList<Integer>(), nums, 0);
    }

    //list s是已取出的数，nums是原始数组，pos是当前取第几个位置的数
    public void helper(List<Integer> s,final int[] nums,final int pos){
        //跳出条件
        if(pos == nums.length -1){
            //do cutting
            return;
        }
        //遍历池子中的数
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            //取过的数不再取
            if(s.contains(num)){
                continue;
            }
            //取出一个数
            s.add(num);
            //进行下一个位置的取数，pos+1
            //TODO: note here pos is the parameter, can't use "++pos" to edit this!! o.w. the logic is wrong
            helper(s,nums,pos+1);
            //重要！！遍历过此节点后，要回溯到上一步，因此要把加入到结果中的此点去除掉！
            s.remove(s.size()-1);
        }
    }
}
