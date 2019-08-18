package leetcode.q155_minStack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        int c = minStack.getMin();  // --> Returns -3.
        minStack.pop();
        c = minStack.top();    //  --> Returns 0.
        c = minStack.getMin();   //--> Returns -2.
        logger.debug("");
    }
}
