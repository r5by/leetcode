package leetcode.q51_52_N_queens;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public int totalNQueens(int n) {
        if( n < 1) return 0;

        int[] res = new int[1];
        dfsNQueen(n, 0, new int[n], res);

        return res[0];
    }

    private void dfsNQueen(final int n, int col, int[] selected, int[] res) {

        if(col == n) {
            res[0] += 1;
        }

        for (int i = 0; i < n; i++) {
            if(!isValid(selected, i, col)) continue;

            selected[col] = i;
            dfsNQueen(n, col + 1, selected, res);
        }
    }

    private boolean isValid(final int[] selected, final int row, final int col) {
        for (int c = 0; c < col; c++) {
            if(row == selected[c] || Math.abs(row - selected[c]) == col - c) return false;
        }
        return true;
    }

    private List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) return new ArrayList<List<String>>() {{
            add(Arrays.asList(""));
        }};

        //for debug usage, set solve[] to be {-1,..-1}
//        int[] solved = new int[n];
//        for (int i = 0; i < solved.length; i++) solved[i] = -1;
//        dfsSolveNQueen(0, solved, new ArrayList<String>(), n);

        dfsSolveNQueen(0, new int[n], new ArrayList<String>(), n);
        return res;
    }

    private void dfsSolveNQueen(int col, int[] solved, ArrayList<String> pRes, final int n) {
        if (col == n) {
            res.add(new ArrayList<>(pRes));
            return;
        }

        for (int i = 0; i < n; i++) {
            //for debug stop (n = 6)
//            if (col == 0 && i == 3)
//                LOG.debug("");

            boolean isValid = true;
            for (int j = 0; j < col; j++) {
                //todo: 注意这个惊天的bug，居然想当然用"两个数比为1"来把程序当成数学线性问题，不可饶恕。。 5/5 == 1 但 5/3 == 1 也成立啊！！
//                if(i == solved[j]
//                        ||((i - solved[j]) / (col - j) == -1)
//                        ||((i - solved[j]) / (col - j) == 1 )) {
//                    isValid = false;
//                    break;
//                }
                if (i == solved[j] || Math.abs(i - solved[j]) == col - j) {
                    isValid = false;
                    break;
                }
            }

            if (!isValid) continue;

            //visit
            StringBuilder str = new StringBuilder();
            for (int k = 0; k < n; k++) {
                if (k == i) str.append("Q");
                else str.append(".");
            }
            pRes.add(str.toString());
            solved[col] = i;

            //dfs
            dfsSolveNQueen(col + 1, solved, pRes, n);

            //backtracking
//            solved[col] = -1; //todo: is this useful? 仅对于debug有用，可以看到回退到哪一步，但实际上不影响程序结果，因为每次到col位置不会查找之后的值
            pRes.remove(pRes.size() - 1);
        }
    }

    //reference:
//    public void helper(int n,int row,int[][] pos){
//        //放置好最后一个皇后时完成一次排列
//        if(row == n){
//            draw(pos);
//            return;
//        }
//        for(int i=0;i<n;i++){
//            if(isValid(row,i,pos)){
//                pos[row][i] = 1;
//                helper(n,row+1,pos);
//                //为回溯到上一步做准备
//                pos[row][i] = 0;
//            }
//        } //放置好最后一个皇后时完成一次排列
//        if(row == n){
//            draw(pos);
//            return;
//        }
//        for(int i=0;i<n;i++){
//            if(isValid(row,i,pos)){
//                pos[row][i] = 1;
//                helper(n,row+1,pos);
//                //为回溯到上一步做准备
//                pos[row][i] = 0;
//            }
//        }
//    }
}
