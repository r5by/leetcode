package leetcode.q240_search_sorted_matrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    /* O(m+n) solution... 从右上角的元素开始判断，不断往左下移动 */
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;

        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0, col = cols -1;
        while(row < rows && col >= 0) {
            if(matrix[row][col] > target) col--;
            else if(matrix[row][col] < target) row++;
            else return true;
        }

        return false;
    }

//    public boolean searchMatrix(int[][] matrix, int target) {
//        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
//
//        int m = matrix.length;
//        int n = matrix[0].length;
//
//        if (target < matrix[0][0] || target > matrix[m - 1][n - 1]) return false;
//
//        List<Integer> diagV = new ArrayList<>();
//        int i = 0;
//        while (i < m && i < n) {
//            diagV.add(matrix[i][i]);
//            i++;
//        }
//
//        int t = bSearch(diagV, 0, diagV.size() - 1, target);
//        if (diagV.get(t) == target) return true;
//
//        return bSearch(matrix, t, target);
//    }
//
//    private int bSearch(List<Integer> arr, int start, int end, int target) {
//        if (start >= end) return end;  //not found
//
//        int mid = (start + end) / 2;
//        if (arr.get(mid) < target) return bSearch(arr, mid + 1, end, target);
//        else if (arr.get(mid) > target) return bSearch(arr, start, mid - 1, target);
//        else return mid;
//    }
//
//    //todo: too complicated....
//    //Binary search the row and column from (t,t) in the matrix for target:
//    //      * ************************  but also <----(t+1, t+1) |^  (up)
//    //      * * (t,t) ----> (t, n - 1)
//    //      *      |
//    //      *      |
//    //      *     (m-1, t)
//    //      * ************************
//    private boolean bSearch(int[][] nums, int t, int target) {
//        int rowH = nums.length - 1;
//        int colH = nums[0].length - 1;
//
//        int pr = t, pc = t;
//        while (pr < rowH) {
//            int mid = (pr + rowH) / 2;
//            if (nums[mid][t] < target) pr = mid + 1;
//            else if (nums[mid][t] > target) rowH = mid - 1;
//            else return true;
//        }
//
//        while (pc < colH) {
//            int mid = (pc + colH) / 2;
//            if (nums[t][mid] < target) pc = mid + 1;
//            else if (nums[t][mid] > target) colH = mid - 1;
//            else return true;
//        }
//        return false;
//    }
}
