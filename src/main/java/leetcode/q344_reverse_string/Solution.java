package leetcode.q344_reverse_string;

class Solution {

    /*Requirement: Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory. */
    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            swap(s, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] s, final int i, final int j) {
        char tmp = s[i];
        s[i] = s[j];
        s[j] = tmp;
    }
}
