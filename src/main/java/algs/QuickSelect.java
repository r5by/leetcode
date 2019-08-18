package algs;

/**
 * @author ruby_
 * @create 2019-07-11-11:16
 */

public class QuickSelect {

    /**
     * @param n:    An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        if (nums == null || nums.length == 1) return 0;

        return kthMax(nums, 0, nums.length - 1, n);
    }

    //find k-th largest
    private int kthMax(int[] arr, int start, int end, int k) {
        if(start > end - 1) {
            return k==1 ? arr[arr.length - 1] : arr[start]; //TODO: Note this is important, if we need to find the 1st max value, it is at the end of the array
        }

        int lo = start, hi = end;
        int pivot = arr[end];
        while (lo < hi) {
            while (lo <= end && arr[lo] < pivot) lo++;
            while (hi >= start && arr[hi] >= pivot) hi--;
            if (lo < hi) swap(arr, lo, hi);
        }

        if (lo != end)
            swap(arr, lo, end); //restore pivot

        int kk = arr.length - lo; // The i-th element is the (len - i)-th max element in the array!
        if (kk > k) //only need to search half the part
            return kthMax(arr, lo + 1, end, k);
        else if (kk < k)
            return kthMax(arr, start, lo - 1, k);
        else
            return arr[lo];

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
