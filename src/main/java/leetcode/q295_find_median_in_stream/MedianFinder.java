package leetcode.q295_find_median_in_stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

class MedianFinder {
//    private static Logger LOG = LoggerFactory.getLogger(MedianFinder.class);
    public Node root;
    private int rootOrder; //the order of root element in the stream (small -> large)
    private int total; //total added elements

    /** initialize your data structure here. */
    public MedianFinder() {
        root = new Node(50); //an imagenary root of input average value stay on top to balance the tree
        rootOrder = 1;
    }

    public void addNum(int num) {
        //update total
        total++;
        //update the min-order of root
        if(num < root._val) rootOrder++;

        Node c = new Node(num);
        Node p = root;
        while (p != null) {
            if(p._val > num) {
                if(p.lChild == null) {
                    p.lChild = c;
                    break;
                }
                p = p.lChild;
            }
            else {
                if(p.rChild == null) {
                    p.rChild = c;
                    break;
                }

                p = p.rChild;
            }
        }


    }

    public double findMedian() {
        int order;
        if(total % 2 == 1) {//odd
            order = total/2 + 1; // find the (n/2 + 1) as the median
            if(order < rootOrder)
                return trav(root.lChild, order, false);
            else
                return trav(root.rChild, order - rootOrder + 1, false);
        } else {
            double a, b;
            order = total/2; //find the (n/2) and (n/2 + 1) elements to calc median
            if (order + 1 == rootOrder) {
                a = root.lChild._val;
                b = trav(root.rChild, 1, false);
            } else if (order + 1 < rootOrder) {
                return trav(root.lChild, order, true);
            } else {
                return trav(root.rChild, order - rootOrder + 1, true);
            }

            return (a + b)/2;
        }
    }

    //return the n-th min value, two -> true, find both then return avg; false: find only 1 element
    public double trav(Node node, int n, boolean two) {
        Stack<Node> mem = new Stack<>();
        int cnt = 0;
        double a = 0, b = 0;
        Node cur = node;
        while (cur != null || !mem.isEmpty()) {
            while(cur != null) {
                mem.push(cur);
                cur = cur.lChild;
            }

            cur = mem.pop();
//            LOG.debug(cur._val + " ");
            cnt++;
            if(cnt == n) {
                if(two) a = cur._val;
                else return cur._val;
            }

            if(cnt == n + 1) {
                b = cur._val;
                return (a + b) /2 ;
            }

            cur = cur.rChild;
        }

        return -1;
    }

    private class Node {
        int _val;
        Node lChild;
        Node rChild;
        Node(int val) {
            _val = val;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
