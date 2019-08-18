package leetcode.q243_244_245_shortest_word_dist;

import java.util.*;

/**
 * 解题思路：
 *  1) 多次查询，初始化construct的时候生成词位置的映射，使用二分查找进行最短距离搜索;
 *  2) 同时考虑到因为所给的words[]是既定de，那么对于某两个词de距离反复查询，可以考虑使用cache来缓存结果;
 */
class WordDistance {

    private HashMap<String, List<Integer>> wordDict = new HashMap<>();
    private HashMap<StringPair, Integer> cache = new HashMap<>();

    public WordDistance(String[] words) {
        //init
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if(wordDict.isEmpty() || !wordDict.containsKey(w)) wordDict.put(w, new ArrayList<>(Arrays.asList(i)));
            else wordDict.get(w).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        if(wordDict.isEmpty() || wordDict.size() == 1) return 0; //or throw exception...

        //使用缓存
        StringPair key = new StringPair(word1, word2);
        int cached = cache.getOrDefault(key, -1);
        if(cached != -1) return cached;

        List<Integer> pos1 = wordDict.get(word1);
        List<Integer> pos2 = wordDict.get(word2);


        if(pos1.size() < pos2.size()) cached = internalSD(pos1, pos2);
        else cached = internalSD(pos2, pos1);

        //缓存
        cache.put(key, cached);

        return cached;
    }

    /**
     * word1's frequency is always lower than word2
     * @param pos1  Positions of word1
     * @param pos2  Positions of word2
     * @return
     */
    private int internalSD(List<Integer> pos1, List<Integer> pos2) {
        int sDist = Integer.MAX_VALUE;
        for (int p1 : pos1) {
            int insertPos = binaryPartition(p1, pos2);
            if (insertPos == 0) {
                sDist = Math.min(sDist, Math.abs(p1 - pos2.get(0)));
            } else if (insertPos == pos2.size()) {
                sDist = Math.min(sDist, Math.abs(p1 - pos2.get(pos2.size() - 1)));
            } else {
                int tmp = Math.min(Math.abs(p1 - pos2.get(insertPos)), Math.abs(p1 - pos2.get(insertPos - 1)));
                sDist = Math.min(sDist, tmp);
            }
        }

        return sDist;
    }

    /**
     * Return the index where val falls in arr, where [0: index - 1] are less than val; and [index:] are greater than val
     * @param val   value to be binary searched;
     * @param arr   array to be binary partitioned
     * @return
     */
    private int binaryPartition(int val, List<Integer> arr) {
        int l = 0;
        int r = arr.size() - 1;
        if(val < arr.get(0)) return 0; //比第一个小
        else if(val > arr.get(arr.size() - 1)) return arr.size(); //比最后一个大
        else {//夹中间
            while( l < r) {
                int mid = (l + r) / 2;
                if(val == arr.get(mid)){
                    return mid;
                } else if(val < arr.get(mid)) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
        }

        return l;
    }

    private class StringPair {
        String _word1;
        String _word2;

        StringPair(String word1, String word2) {
            _word1 = word1;
            _word2 = word2;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(_word1 +  _word2);
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if(obj == null || !this.getClass().equals(obj.getClass())) return false;
            StringPair that = (StringPair) obj;
            return (this._word1.equals(that._word1) && this._word2.equals(that._word2));
        }
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
