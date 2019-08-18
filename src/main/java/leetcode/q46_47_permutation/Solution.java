package leetcode.q46_47_permutation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Util;

import java.util.*;

/**
 * Note: About using boolean[] vs. BitSet:
 * boolean[] is more time efficient than BitSet, while BitSet is more space efficient, but at small value, boolean[] has more advantage
 * see this post:
 * https://stackoverflow.com/questions/605226/boolean-vs-bitset-which-is-more-efficient
 */

class Solution {
    private static Logger logger = LoggerFactory.getLogger(Solution.class);
    private List<List<Integer>> res = new ArrayList<List<Integer>>();

    /* 1. permutate the whole array */
    public void permutate(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("erro");
        }
        permutate(new ArrayList<Integer>(), nums, 0);
    }

    private void permutate(List<Integer> list, final int[] nums, final int pos) {
        permutate(list, nums, nums.length, pos);
    }

    /* 2. permutate the array with given size */
    public void permutate(int[] nums, int length) {
        if (nums == null || nums.length < length) {
            throw new IllegalArgumentException("erro");
        }

        permutate(new ArrayList<Integer>(), nums, length, 0);
    }

    private void permutate(List<Integer> list, final int[] nums, final int length, final int pos) {
//        logger.debug("choose element for position: " + pos);
//        List<Integer> newlist = new ArrayList<Integer>(list);
        if (pos == length) {
            res.add(new ArrayList<Integer>(list));
//            res.add(newlist);
//            logger.debug("adding resut list: " + Util.listToString(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
//            logger.debug("currently choosing:"+ nums[i]);

            if (list.contains(nums[i])) {
                continue;
            }
//            logger.debug("adding -- (+)" + nums[i]);
            List<Integer> newlist = new ArrayList<Integer>(list);
//            list.add(nums[i]);
//            permutate(list, nums, pos+1);
            newlist.add(nums[i]);
            permutate(newlist, nums, pos + 1);

//            logger.debug("backtrack removing: -- (x)" + list.get(list.size() - 1));
//            list.remove(list.size() - 1);
        }
    }


    public void permutateWithDups(int[] nums, int length) {
        Arrays.sort(nums);
        permutateWithDups(new ArrayList<Integer>(), new boolean[nums.length], nums, length, 0);
    }

    public void permutateWithDups(int[] nums) {
        permutateWithDups(nums, nums.length);
    }

    /* 3. permutate array with duplicated items
     *  1) sort the array
     *  2) take non-duplicated values in order to prevent re-usage
     * */
    private void permutateWithDups(List<Integer> list, final boolean visited[], final int[] nums, final int length, final int pos) {
        if (pos == length) {
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            // Can't take the number if 1) number is already taken 2) the previous same number hasn't been taken
            if (visited[i] == true || (i > 0 && nums[i] == nums[i - 1] && visited[i - 1] == false)) {
                continue;
            }

            list.add(nums[i]);
            visited[i] = true;
            permutateWithDups(list, visited, nums, length, pos + 1);

            /*traceback and reset the visited-flag */
            list.remove(list.size() - 1);
            visited[i] = false;
        }

    }


    public List<List<Integer>> permuteUnique(int[] nums) {
//        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 1) return new ArrayList<List<Integer>>(){{add(new ArrayList<>());}};


        boolean[] selected = new boolean[nums.length];

        //基础方法：熟悉dfs+backtracking
//        dfs(nums, selected, new ArrayList<>(), res);
//        return res;

        //空间换时间优化方案： dfs + backtracking + pruning
        Map<Integer, List<List<Integer>>> cache = new HashMap<>();

        dfsCached(nums, selected, 0, new ArrayList<>(), cache);
        return cache.get(nums.length - 1);
    }

    private void dfsCached(final int[] nums, boolean[] selected, int h, List<Integer> partialResult, Map<Integer, List<List<Integer>>> cache) {
        if(h == nums.length ) return; //end condition

        if(cache.isEmpty() || cache.get(h) == null) {
            cache.put(h, new ArrayList<>());
        }

        for (int i = 0; i < nums.length; i++) {

            if(selected[i]) continue; //pruning 1

            partialResult.add(nums[i]); //pruning 2
            if (!cache.isEmpty() && cache.get(h) != null && cache.get(h).contains(partialResult)) {
                partialResult.remove(partialResult.size() - 1);
                continue;
            }

            selected[i] = true;
            cache.get(h).add(new ArrayList<>(partialResult));

            dfsCached(nums, selected, h + 1, partialResult, cache);

            selected[i] = false;
            partialResult.remove(partialResult.size() - 1);
        }
    }

    //todo： 注意以下方法并未对中间的重复结果剪枝，比如 nums = [0,1,0] 第二层会生成两个{0,0}的分支（重复）
    //优化方案：可以利用上面 permutateWithDups() 方法，先对输入排序，然后按照顺序取重复的元素;
    //或者将每一层结果缓存，每次进入分支前检查该分支是否已被创建, 参考 dfsCached
    private void dfs(final int[] nums, boolean[] selected, List<Integer> choice, List<List<Integer>> res) {

        if (choice.size() == nums.length && (res.isEmpty() || !res.contains(choice))) { //该条件判断是否已达要求长度，同时排除掉重复元素生成的分支
//        if (choice.size() == nums.length) { //条件单纯判断是否已达到要求长度
            res.add(new ArrayList<>(choice));
            return;
        }

        for (int i = 0; i < nums.length; i++) {//每次向下一级可以选择的元素：例如 nums = [0,1,2] 第二层： {0} => {0, ?}, ? 可以选 {0,1,2} 中任何一个
            if(selected[i]) continue; //todo：这一条语句相当于剪掉已经选择的元素分支：例如， {0} => {0,1}, {0,2}, 而 {0,0} 分支将被剪掉，因为 0 已经被标记为visited

            selected[i] = true;
            choice.add(nums[i]);

            dfs(nums, selected, choice, res);


            selected[i] = false;
            choice.remove(choice.size() - 1);
        }
    }


    public List<List<Integer>> getRes() {
        return res;
    }
}
