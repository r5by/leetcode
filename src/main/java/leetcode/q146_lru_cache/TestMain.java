package leetcode.q146_lru_cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ruby_
 * @create 2018-12-07-8:56 PM
 */

public class TestMain {
    private static Logger logger = LoggerFactory.getLogger(TestMain.class);

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        int d;
        cache.put(1, 1);
        cache.put(2, 2);
        d = cache.get(1);       // returns 1
        cache.put(3, 3);    // evicts key 2
        d = cache.get(2);       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        d = cache.get(1);       // returns -1 (not found)
        d= cache.get(3);       // returns 3
        d=cache.get(4);       // returns 4
        logger.debug("");
    }
}
