package utils;

import leetcode.common.TreeNode;

import java.util.*;

/**
 * @author ruby_
 * @create 2019-05-29-17:01
 */

public class Util {

    public static <T> String listToString(List<T> list) {
        String result = "[ ";
        for(T item : list)
            result += item.toString() + " ";
        result += "]";
        return result;
    }

    //todo: use KMP to match string
    public static boolean sStartsWithPattern(String s, String pattern) {
        if(s.length() > 0
                && s.length() < pattern.length()) return false;

        for (int i = 0; i < pattern.length(); i++) {
            if(s.charAt(i) != pattern.charAt(i))
                return false;
        }

        return true;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    /**
     * Check whether the given string is polindrome
     * @param s
     * @return
     */
    public static boolean isPolindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if(s.charAt(left++) != s.charAt(right--)) return false;
        }

        return true;
    }

    /**
     * Randomly generate palindrome at give size
     * @param size
     * @return
     */
    public static String generatePalindrome (int size) {
        Random rand = new Random();
        StringBuilder random = new StringBuilder(size);
        for (int i = 0; i < (int)Math.ceil((double)size/2); i++) {
            char ch = (char)(rand.nextInt(26) + 97);
            random.append(ch);
        }
        for(int i = size/2-1; i >= 0; i--)
            random.append(random.charAt(i));

        return random.toString();
    }

    public static String removeCharAt(int pos, String s) {
        return s.substring(0, pos) + s.substring(pos + 1, s.length() - 1);
    }

    public static int rightWeightOfIndex(List<Integer> pArray, int i) {
        if(pArray.size() % 2 == 0 || i % 2 != 0)
            throw new IllegalArgumentException("Invalid input array");

        if(i == pArray.size() - 1)
            return pArray.get(i).intValue();
        else {
            return Math.max(pArray.get(i), pArray.get(i) + pArray.get(i+1) +
                    rightWeightOfIndex(pArray, i+2));
        }
    }

    public static int leftWeightOfIndex(List<Integer> pArray, int i) {
        if(pArray.size() % 2 == 0 || i % 2 != 0)
            throw new IllegalArgumentException("Invalid input array");

        if(i == 0)
            return pArray.get(i).intValue();
        else {
            return Math.max(pArray.get(i), pArray.get(i) + pArray.get(i-1) +
                    leftWeightOfIndex(pArray, i-2));
        }
    }

    public static ArrayList<Integer> toArrayList(int[] pArray) {
        ArrayList<Integer> resultArray = new ArrayList<Integer>();
        for(int i = 0; i < pArray.length; i++)
            resultArray.add(pArray[i]);

        return resultArray;
    }
}
