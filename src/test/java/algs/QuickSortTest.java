package algs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSortTest {

    int[] input;

    @Before
    public void setUp() throws Exception {
//        input = new int[] {3, 6, 9, 16, 11, 4, 7, 9};
        input = new int[] {6, 3};
    }

    @Test
    public void qSort() {
        QuickSort.qSort(input);
        System.out.println();
    }
}