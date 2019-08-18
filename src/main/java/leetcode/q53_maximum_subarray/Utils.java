package leetcode.q53_maximum_subarray;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ruby_
 * @create 2018-12-01-8:44 AM
 */

public class Utils {
//    public static int rightWeightOfIndex(List<Integer> pArray, int i) {
//        if(pArray.size() % 2 == 0 || i % 2 != 0)
//            throw new IllegalArgumentException("Invalid input array");
//
//        if(i == pArray.size() - 1)
//            return pArray.get(i).intValue();
//        else {
//            return Math.max(pArray.get(i), pArray.get(i) + pArray.get(i+1) +
//                    rightWeightOfIndex(pArray, i+2));
//        }
//    }
//
//    public static int leftWeightOfIndex(List<Integer> pArray, int i) {
//        if(pArray.size() % 2 == 0 || i % 2 != 0)
//            throw new IllegalArgumentException("Invalid input array");
//
//        if(i == 0)
//            return pArray.get(i).intValue();
//        else {
//            return Math.max(pArray.get(i), pArray.get(i) + pArray.get(i-1) +
//                    leftWeightOfIndex(pArray, i-2));
//        }
//    }
//
//    public static ArrayList<Integer> toArrayList(int[] pArray) {
//        ArrayList<Integer> resultArray = new ArrayList<Integer>();
//        for(int i = 0; i < pArray.length; i++)
//            resultArray.add(pArray[i]);
//
//        return resultArray;
//    }
}
