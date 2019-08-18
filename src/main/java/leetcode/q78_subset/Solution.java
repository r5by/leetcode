package leetcode.q78_subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    List<List<Integer>> res = new ArrayList<List<Integer>>();

    /*1. without deplicated numbers*/
    public List<List<Integer>> subset(int[] nums, boolean noDup) {
        if(nums == null)
            throw new IllegalArgumentException("erro");

        if(noDup)
            subset(new ArrayList<Integer>(), nums, 0);
        else {
            //选代表： 选第2个重复数之前必须要选第1个 所以先sort
            Arrays.sort(nums);
            subsetWithDups(new ArrayList<Integer>(), nums, 0);
        }


        return res;
    }

    private void subset(List<Integer> list, final int[] nums, final int pos) {
        res.add(new ArrayList<Integer>(list)); //deep copy

        if(pos == nums.length)
            return;

        for (int i = pos; i < nums.length; i++) {
            if (list.contains(nums[i])) {
                continue;
            }

            list.add(nums[i]);
            //Key idea here: i not pos (different to permutation example). because we only take numbers after it to avoid taking [3, 1] after [1, 3]!!
            subset(list, nums, i + 1);

            list.remove(list.size() - 1);
        }
    }

    private void subsetWithDups(List<Integer> list, final int[] nums, final int pos) {
        res.add(new ArrayList<Integer>(list));

        if(pos == nums.length) return;

        for (int i = pos; i < nums.length; i++) {

            if(i > pos && nums[i-1] == nums[i]) //从pos往后看取数， 如果之前的相同数没有被取则跳过这一分支 (相同的数只取一次 所以不需要判断是否visite )
                continue;

            list.add(nums[i]);

            subsetWithDups(list, nums, i + 1);

            list.remove(list.size() - 1);
        }
    }
}
