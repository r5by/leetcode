package algs;

import utils.Util;

/**
 * Quick sort
 */

public class QuickSort {

    //Quick sort an array small -> large
    public static void qSort(int[] input) {

        qSort(input, 0, input.length - 1);
    }

    private static void qSort(int[] arr, int left, int right) {
        if (left > right - 1) return;

        int pivot = arr[right];
        int l = left, h = right;
        while (l < h) {
            while (l <= right && arr[l] < pivot) l++;
            while (h >= left && arr[h] >= pivot) h--;
            if (l < h) Util.swap(arr, l, h);
        }

        Util.swap(arr, l, right);
        qSort(arr, left, l - 1);
        qSort(arr, l + 1, right);
    }
}