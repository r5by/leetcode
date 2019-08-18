package leetcode.q218_skyline_problem;

import java.util.*;

class Solution {

    /*TODO: Segment tree solution*/
    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if(buildings == null)
            throw new IllegalArgumentException("erro");
        else if(buildings.length == 0)
            return res;

        //Collect all input data x coordinates
        Set<Integer> endPoints = new HashSet<>();
        for (int[] building : buildings) {
            endPoints.add(building[0]);
            endPoints.add(building[1]);
        }
        //sort all end points
        List<Integer> endPointsList = new ArrayList<>(endPoints);
        Collections.sort(endPointsList);

        //discretize the x coordinates
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < endPointsList.size(); i++) {
            indices.put(endPointsList.get(i), i);
        }

        //Generate the segment tree with discrete indices
        Node root = generateTree(0, endPointsList.size() - 1);

        //Insert buildings
        for (int[] building : buildings) {
            int start = indices.get(building[0]);
            int end = indices.get(building[1]);
            int height = building[2];

            insert(root, start, end, height);
        }

        //Analyze and write results
        explore(root, endPointsList);
        if (!res.isEmpty()) {//don't forget the last point
            List<Integer> last = new ArrayList<Integer>(){{
                add(endPointsList.get(endPointsList.size() - 1));
                add(0);
            }};
            res.add(last);
        }

        return res;
    }

    private void explore(Node node, final List<Integer> endPointsList) {
        if (node.left == null && node.right == null) {
            if(node.start == 0 || res.get(res.size() - 1).get(1) != node.height) { //TODO: Note here the previous saved point is res.size() - 1 !! (not node.start - 1)!!
                List<Integer> tmp = new ArrayList<Integer>() {{
                    add(endPointsList.get(node.start));
                    add(node.height);
                }};
                res.add(tmp);
            }
            return;
        }

        explore(node.left, endPointsList);
        explore(node.right, endPointsList);
    }

    private void insert(Node node, final int start, final int end, final int height) {
        //end condition
        if(height <= node.height) return; //no need to update the segment tree if the inserted building is smallest in the range;

        if(node.start == node.end - 1) {//TODO: Note: node.start not start!!! the leaf node
            node.height = Math.max(height, node.height); //update the newest hightest building in the segment leaf node

            //Collect the leaf info for later output
            //TODO: Note this is wrong, not all leaf nodes can be updated while interting new value!!
//            resPool.put(node.start, node.height);
            return;
        }

        int mid = (node.start + node.end) / 2;
        if(end <= mid)
            insert(node.left, start, end, height);
        else if(start>=mid)
            insert(node.right, start, end, height);
        else {
            insert(node.left, start, mid, height);
            insert(node.right, mid, end, height);
        }
        node.height = Math.min(node.left.height, node.right.height);
    }

    private Node generateTree(final int start, final int end) {
        Node node = new Node(start, end);

        if(start == end - 1) return node;

        int mid = (start + end) / 2;
        node.left = generateTree(start, mid);
        node.right = generateTree(mid, end);

        return node;
    }

    private class Node{
        int start, end;
        Node left, right;
        int height;

        Node(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    //reference: segment tree solution
//    public List<List<Integer>> getSkyline(int[][] buildings) {
//        Set<Integer> endpointSet = new HashSet<Integer>();
//        for(int[] building : buildings){
//            endpointSet.add(building[0]);
//            endpointSet.add(building[1]);
//        }
//
//        List<Integer> endpointList = new ArrayList<Integer>(endpointSet);
//        Collections.sort(endpointList);
//
//        HashMap<Integer, Integer> endpointMap = new HashMap<Integer, Integer>();
//        for(int i = 0; i < endpointList.size(); i++){
//            endpointMap.put(endpointList.get(i), i);
//        }
//
//        Node root = buildNode(0, endpointList.size() - 1);
//        for(int[] building : buildings){
//            add(root, endpointMap.get(building[0]), endpointMap.get(building[1]), building[2]);
//        }
//
//        List<List<Integer>> result = new ArrayList<>();
//        explore(result, endpointList, root);
//        if(endpointList.size() > 0){
//            List<Integer> tmp = new ArrayList<Integer>(){{
//                add(endpointList.get(endpointList.size() - 1));
//                add(0);
//            }};
//            result.add(tmp);
//        }
//
//        return result;
//    }
//    private Node buildNode(int start, int end){
//        if(start > end){
//            return null;
//        }else{
//            Node result = new Node(start, end);
//            if(start + 1 < end){
//                int center = start + (end - start) / 2;
//                result.left = buildNode(start, center);
//                result.right = buildNode(center, end);
//            }
//
//            return result;
//        }
//    }
//    private void add(Node node, int start, int end, int height){
//        if(node == null || start >= node.end || end <= node.start || height < node.height){
//            return;
//        }else{
//            if(node.left == null && node.right == null){
//                node.height = Math.max(node.height, height);
//            }else{
//                add(node.left, start, end, height);
//                add(node.right, start, end, height);
//                //Though the Following statement has no impacts on the output, it does has great impact on performance! (update time is greatly shorten)
//                node.height = Math.min(node.left.height, node.right.height);
//            }
//        }
//    }
//    private void explore(List<List<Integer>> result, List<Integer> endpointList, Node node){
//        if(node == null){
//            return;
//        }else{
//            if(node.left == null && node.right == null && (result.size() == 0 || result.get(result.size() - 1).get(1) != node.height)){
//                List<Integer> tmp = new ArrayList<Integer>(){{
//                    add(endpointList.get(node.start));
//                    add(node.height);
//                }};
//                result.add(tmp);
//            }else{
//                explore(result, endpointList, node.left);
//                explore(result, endpointList, node.right);
//            }
//        }
//    }

    /* Slow solution! */
//    List<List<Integer>> res = new ArrayList<>();
//
//    public List<List<Integer>> getSkyline(int[][] buildings) {
//        if(buildings == null)
//            throw new IllegalArgumentException("input error");
//        else if(buildings.length < 1)
//            return res;
//
//        Set<Integer> coords = new HashSet<>();
//        for (int[] building : buildings) {
//            coords.add(building[0]);
//            coords.add(building[1]);
//        }
//
//        List<Integer> allPoints = new ArrayList<>(coords);
//        Collections.sort(allPoints);
//
//        //Populate segments
//        List<Segment> segments = new ArrayList<>();
//        for (int i = 0; i < allPoints.size() - 1; i++) {
//            segments.add(new Segment(allPoints.get(i), allPoints.get(i+1)));
//        }
//
//        //Insert buildings to the segments
//        for (int[] building : buildings) {
//            int left = building[0];
//            int right = building[1];
//            int height = building[2];
//
//            for (Segment seg : segments) {
//                if(seg.start >= left && seg.end <= right)
//                    seg.h = Math.max(seg.h, height);
//            }
//        }
//
//
//        //Add first seg
//        int preHeight = segments.get(0).h;
//        List<Integer> first = new ArrayList<>();
//        first.add(segments.get(0).start);
//        first.add(preHeight);
//        res.add(first);
//        //Loop the segments and add the rest
//        for (int i = 1; i < segments.size(); i++) {
//            int curHeight = segments.get(i).h;
//            if (curHeight == preHeight) continue;
//
//            List<Integer> tmp = new ArrayList<>();
//            tmp.add(segments.get(i).start);
//            tmp.add(curHeight);
//            res.add(tmp);
//
//            preHeight = curHeight;
//        }
//
//        //Don't forget the last
//        if (!res.isEmpty()) {
//            List<Integer> last = new ArrayList<>();
//            last.add(segments.get(segments.size() - 1).end);
//            last.add(0);
//            res.add(last);
//        }
//        return res;
//    }
//
//    private class Segment {
//        int start;
//        int end;
//        int h;
//
//        Segment(int s, int e) {
//            start = s;
//            end = e;
//            h = 0;
//        }
//    }
}
