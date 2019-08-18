package leetcode.q31_next_permutation;

class Solution {
    /**
     * example:
     * [1,2,3,4]
     * [1,2,4,3]
     * [1,3,2,4]
     * [1,3,4,2]
     * [1,4,2,3]
     * ...
     */

    public void nextPermutation(int[] nums) {

        if(nums == null || nums.length == 0)
            throw new IllegalArgumentException("error");


        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int j = nums.length - 1;
                while (j >= i) {
                    if (nums[j] > nums[i - 1]) {
                        swap(nums, i - 1, j);
                        break;
                    }
                    j--;
                }

                reverse(nums, i, nums.length -1);
                return;
            }
        }
    }


    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void reverse(int[] nums, int i, int j) {
        while(i < j)
            swap(nums, i++, j--);
    }
}
