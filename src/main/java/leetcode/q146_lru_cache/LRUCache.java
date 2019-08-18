package leetcode.q146_lru_cache;

import java.util.HashMap;

/**
 * @author ruby_
 * @create 2019-01-20-17:45
 */

public class LRUCache {
    private HashMap<Integer, DNode> cache;
    private int capacity;
    private DNode LRU;
    private DNode MRU;

    //A double-linked list that keeps all available keys, if a key is used, move it to the queue end,
    public LRUCache(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("Invalid Cache Size");

        this.capacity = capacity;
        cache = new HashMap(capacity);
    }

    //Core APIs: LRU get & put

    /**
     * Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     *
     * @param key
     * @return
     */
    public synchronized int get(int key) {
        if (!cache.containsKey(key)) return -1;

        DNode cur = cache.get(key);
        int res = cur.value;

        //update MRU
        moveNodeToMRU(cur);
        return res;
    }

    private void moveNodeToMRU(DNode node) {
        if (MRU.key == node.key) return;

        //NOTE: don't forget if the node was move was LRU...
        if(LRU.key == node.key) {
            LRU = LRU.pre;
            LRU.next = null;
        }

        MRU.pre = node;
        if (node.pre != null) node.pre.next = node.next;
        if (node.next != null) node.next.pre = node.pre;
        node.pre = null;
        node.next = MRU;
        MRU = node;
    }

    /**
     * Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
     *
     * @param key
     * @param value
     */
    public synchronized void put(int key, int value) {
        //init cache for first time use
        if (cache.isEmpty()) {
            DNode node = new DNode(key, value);
            MRU = node;
            LRU = node;
            cache.put(key, node);
            return;
        }

        DNode cur;
        if (cache.containsKey(key)) {
            cur = cache.get(key);
            cur.value = value;

            //update MRU
            moveNodeToMRU(cur);
        } else {
            cur = new DNode(key, value);

            cur.next = MRU;
            MRU.pre = cur;
            MRU = cur;
            cache.put(key, cur);
        }
        //update LRU
        if (cache.size() > capacity) {
            cache.remove(LRU.key);
            LRU = LRU.pre;
            LRU.next = null;
        }
    }

    //Easy implementation for double-linked list
    private class DNode {
        int key;
        int value;
        DNode next;
        DNode pre;

        DNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}
