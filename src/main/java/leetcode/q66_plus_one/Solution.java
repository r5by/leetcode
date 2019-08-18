package leetcode.q66_plus_one;

class Solution {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits[0] <= 0)
            throw new IllegalArgumentException("e");

        int l = digits.length;
        int carry = 0;
        for(int i = l - 1; i >= 0; i--) {
            if(digits[i] == 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] += 1;
                if(i >= 0) //TODO (NOTE): dont' forget to reset carry if it's not the highest digit
                    carry = 0;
                break;
            }
        }

        if(carry == 1) {
            int[] res = new int[l+1];
            res[0] = 1;
            return res;
        } else
            return digits;
    }
}
