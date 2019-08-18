package leetcode.q54_spiral_matrix;

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();

        if(matrix == null || matrix.length == 0)
            throw new IllegalArgumentException("erro");

        final int rows = matrix.length;
        final int cols = matrix[0].length;
        int left = 0;
        int right = cols - 1;
        int top = 0;
        int bottom = rows - 1;

        while (res.size() < rows * cols) {
            for(int col = left; col <= right; col++)
                res.add(matrix[top][col]);
            top++;

            if(res.size() < rows * cols) {
                for(int row = top; row <= bottom; row++)
                    res.add(matrix[row][right]);
                right--;
            }

            if(res.size() < rows * cols) {
                for(int col = right; col >= left; col--)
                    res.add(matrix[bottom][col]);
                bottom--;
            }

            if(res.size() < rows * cols) {
                for(int row = bottom; row >= top; row--)
                    res.add(matrix[row][left]);
                left++;
            }
        }


        return res;
    }

    /**
     * Ping-pong from bottom to top, starting from #flat points at bottom and #mutate points to top;
     *
     * //TODO: The function is able to generate row number, by passing n to flat, (m-1) to mutate, 0 to bottom and (m-1) to top
     *          Similar function can be written to generate column number.
     * @param flat
     * @param mutate
     * @param bottom
     * @param top
     * @return
     */
    public List<Integer> pingpong(int flat, int mutate, int bottom, int top) {
        if( (top - bottom < mutate)
                || flat < 0 || mutate < 0)
            throw new IllegalArgumentException("error");


        List<Integer> res = new ArrayList<Integer>();
        if(flat == 0 && mutate == 0)
            return res;


        while (true) {
            //ping
            if(flat == 0 || mutate == 0) break;
            for(int i = 0; i < flat; i++) res.add(bottom);
            bottom++;
            flat--;
            for (int j = 0; j < mutate; j++) res.add(bottom + j);
            mutate--;

            //pong
            if(flat == 0 || mutate == 0) break;
            for (int p = 0; p < flat; p++) res.add(top);
            top--;
            flat--;
            for (int q = 0; q < mutate; q++) res.add(top - q);
            mutate--;
        }

        return res;
    }

}
