package algs;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuickSelectTest {
    QuickSelect s = new QuickSelect();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void kthLargestElement() {
        int[] input = new int[] {9,3,2,4,8};
        int res = s.kthLargestElement(3, input);
        System.out.println();
    }
}