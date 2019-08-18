package leetcode.q771_jewels_and_stones;

class Solution {
    public int numJewelsInStones(String J, String S) {
        if(J == null || S == null)
            throw new IllegalArgumentException("erro");

        if(S.length() < 1 || J.length() < 1)
            return 0;

        int cnt = 0;
        for (char s : S.toCharArray()) {
            for (char j : J.toCharArray()) {
                if(s==j) cnt++;
            }
        }
        return cnt;
    }
}
