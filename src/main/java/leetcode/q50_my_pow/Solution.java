package leetcode.q50_my_pow;

class Solution {

    /**
     * Time complexity : O(log(n))
     * Space complexity: O(log(n))
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(n == 0)
            return 1;

        double half = myPow(x, n / 2);

        if(n % 2 == 0) return half * half;
        if(n > 0) return half * half * x;
        return half * half / x;
    }
}
