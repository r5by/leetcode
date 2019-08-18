package leetcode.q56_merge_intervals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    //TODO: Understand this better solution:
    //if there is no intersection,this fun will return false.if not, this fun will merge intervals[i][] and intervals[j][] and return true.
    private boolean mix(int[][] intervals,int i,int j){
        if(intervals[i][0]<=intervals[j][0]){
            if(intervals[i][1]<intervals[j][0])//there is no intersection
                return false;
            intervals[i][1]=intervals[i][1]>intervals[j][1]?intervals[i][1]:intervals[j][1];
            return true;
        }else{//
            if(intervals[i][0]>intervals[j][1])//there is no intersection
                return false;
            intervals[i][0]=intervals[j][0];
            intervals[i][1]=intervals[i][1]>intervals[j][1]?intervals[i][1]:intervals[j][1];
            return true;
        }
    }
    public int[][] merge(int[][] intervals) {
        boolean[] judge=new boolean[intervals.length];//if judge[i] is true,we won't use intervals[i][] as the final result
        boolean change=false;//if there isn't "change",the result of [[1,2],[3,4],[5,6],[1,18]] will be [1,18],[1,18],[1,18]
        int size=intervals.length;//the size of the array of results
        for(int i=0;i<intervals.length;i++){
            change=false;
            if(judge[i]){
                size--;
                continue;
            }
            for(int j=i+1;j<intervals.length;j++){
                if(judge[j]==true)
                    continue;
                judge[j]=mix(intervals,i,j);
                if(judge[j]==true&&!change)
                    change=true;
            }
            if(change)
                i--;
        }
        int j=0;
        int[][] result=new int[size][2];
        for(int i=0;i<size;i++)
            for(;j<intervals.length;j++)
                if(judge[j]==false){
                    result[i][0]=intervals[j][0];
                    result[i][1]=intervals[j][1];
                    j++;
                    break;
                }
        return result;
    }

//    public int[][] merge(int[][] intervals) {
//        List<Interval> inputList = convert(intervals);
//        Collections.sort(inputList, (a, b) -> a.start - b.start);
//
//        List<Interval> res = new ArrayList<Interval>();
//
//        Interval pre = inputList.get(0);
//        res.add(pre);
//        for (int i = 1; i < inputList.size(); i++) {
//            Interval cur = inputList.get(i);
//
//            if (cur.start <= pre.end) {
//                pre.end = Math.max(pre.end, cur.end);
//            } else {
//                res.add(cur);
//                pre = cur; //don't forget to move the pointer!!
//            }
//        }
//
//        int[][] outArray = new int[res.size()][2];
//        output(outArray,res);
//        return outArray;
//    }
//
//    private void output(int[][] out, List<Interval> res) {
//        for (int i = 0; i < res.size(); i++) {
//            out[i][0] = res.get(i).start;
//            out[i][1] = res.get(i).end;
//        }
//    }
//
//    private List<Interval> convert(int[][] intervals) {
//        if(intervals == null)
//            throw new IllegalArgumentException("e");
//
//        List<Interval> res = new ArrayList<Interval>();
//        if(intervals.length == 0)
//            return res;
//
//        for (int i = 0; i < intervals.length; i++) {
//            res.add(new Interval(intervals[i]));
//        }
//        return res;
//    }
//
//    private class Interval {
//        int start;
//        int end;
//
//        public Interval(){
//            start = 0;
//            end = 0;
//        }
//
//        public Interval(int pStart, int pEnd) {
//            start = pStart;
//            end = pEnd;
//        }
//
//        public Interval(int[] inter) {
//            start = inter[0];
//            end = inter[1];
//        }
//    }

}
