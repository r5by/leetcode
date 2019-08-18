package leetcode.q138_copy_list_with_random_pointer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Deep copy of a node list
class Solution {
    private HashMap<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head) {
        Node newHead = copyLinkedList(head);
        Node p = newHead;
        while (p != null) {
            p.random = map.get(head.random);
            p = p.next;
            head = head.next;
        }

        return newHead;
    }

    private Node copyLinkedList(Node node) {
        if(node == null) return null;

        Node newNode = new Node(node.val);
        Node next = copyLinkedList(node.next);
        newNode.next = next;

        //save for second copy of random pointer
        map.put(node, newNode);
        return newNode;
    }

    public class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };

    //helper
    public Node generateNodeList(int[] vals, int[] random) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < vals.length; i++) {
            nodes.add(new Node(vals[i]));

            if (nodes.size() > 1) {
                nodes.get(i - 1).next = nodes.get(i);
            }
        }

        for (int j = 0; j < random.length; j++) {
            nodes.get(j).random = nodes.get(random[j]);
        }

        return nodes.get(0);
    }
}
