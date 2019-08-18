package leetcode.q39_combination_sum;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0)
            throw new IllegalArgumentException("Empty input");

        process(res, new ArrayList<Integer>(), candidates, target, 0);
        return res;
    }

    private void process(List<List<Integer>> res, List<Integer> list, int[] candidates, int target, int start) {
        if(target < 0) return;
        if (target == 0) {
            res.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            list.add(candidates[i]);
            process(res, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
}
