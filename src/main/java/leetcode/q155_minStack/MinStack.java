package leetcode.q155_minStack;

import java.util.Stack;

class MinStack {
    Stack<Integer> stack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
        minStack = new Stack<Integer>();
    }

    public void push(int x) {
        stack.push(x);

        if(minStack.isEmpty()) minStack.push(x);
        else {
            int c = minStack.peek();
            if(x < c) minStack.push(x);
        }
    }

    public void pop() {
        int c = stack.pop();

        if(c == minStack.peek()) //TODO: NOTE here is to check whether the popped element "==" (not "<") the minStack top!!
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
