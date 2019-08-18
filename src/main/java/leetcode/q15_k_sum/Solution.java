package leetcode.q15_k_sum;

import java.util.*;

/**
 * Generate this questions to "K-sum" problem (with duplication)
 */
class Solution {
    private HashMap<KTarget, Set<Set<Integer>>> cache = new HashMap<>(); // cache partial solutions

    public List<List<Integer>> threeSum(int[] nums) {
        List<Integer> input = toList(nums);
        Set<Set<Integer>> outSet = kSum(input, 3, 0);
        List<List<Integer>> res = new ArrayList<>();
        for (Set<Integer> s : outSet) {
            List<Integer> tmp = new ArrayList<>();
            for (int i : s) {
                tmp.add(nums[i]);
            }
            res.add(tmp);
        }
        return res;
    }


    /* return a set of index combinations which satifies sum of the values of indices of the nums equals to target*/
    public Set<Set<Integer>> kSum(List<Integer> nums, final int k, final int target) {
        //exceptions
        if (k < 2)
            throw new IllegalArgumentException("error");

        //memo
        KTarget cKey = new KTarget(k, target);
        if(cache.containsKey(cKey))
            return cache.get(cKey);

        Set<Set<Integer>> res = new HashSet<>();
        //end condition: k = 2
        if (k == 2) {
//            HashMap<Integer, Integer> map = new HashMap<>(); //without dup
            HashMap<Integer, List<Integer>> map = new HashMap<>(); //with dup
            init(map, nums);

            for (int i = 0; i < nums.size(); i++) {
                Integer cur = nums.get(i);
                if (cur == null) continue; //Note: because in recursion, the already selected element needs to be replaced by "null" in the list
                int t = target - cur.intValue();
                if (map.containsKey(t)) {
                    for (Integer l : map.get(t)) {
                        if(l != i) {
                            Set<Integer> s = new HashSet<>();
                            s.add(i);
                            s.add(l);
                            if (res.contains(s)) continue;
                            res.add(s);
                        }
                    }
                }
            }
        } else {
            // k>2 recursion
            for (int i = 0; i < nums.size(); i++) {
                Integer cur = nums.get(i);
                if (cur == null) continue;
                int t = target - cur.intValue();

                List<Integer> next = new ArrayList<>(nums);
                next.set(i, null);

                Set<Set<Integer>> tmp = kSum(next, k - 1, t);
                if (!tmp.isEmpty()) {
                    for (Set<Integer> s : tmp) {
                        Set<Integer> stmp = new HashSet<>(s); //TODO: NOTE don't forget to deep copy the result, otherwise later modification will change the value saved!!
                        stmp.add(i);
                        if (!res.contains(stmp) && stmp.size() == k) res.add(stmp); //TODO: Note don't forget to check the set to see if the length is right, o.w. a set may be shorter if a element is taken twice!!
                    }
                }
            }
        }

        //save cache
        cache.put(cKey, res);

        return res;
    }

    private void init(HashMap<Integer, List<Integer>> map, final List<Integer> nums) {
        for (int i = 0; i < nums.size(); i++) {
            Integer cur = nums.get(i);
            if(cur == null) continue;
            if(map.get(cur) != null) {
                map.get(cur).add(i);
            } else {
                final int j = i;
                map.put(cur, new ArrayList<Integer>(){{
                    add(j);
                }});
            }
        }
    }

    public List<Integer> toList(int[] arr) {
        List<Integer> res = new ArrayList<>();
        for (int i : arr) res.add(i);
        return res;
    }

    private class KTarget {
        int mk, mt;
        KTarget(int k, int t){
            mk = k;
            mt = t;
        }

        @Override
        public boolean equals(Object that) {
            if(this == that) return true;
            if(that == null) return false;
            if(getClass() != that.getClass()) return false;
            KTarget t = (KTarget) that;
            return (mk == t.mk && mt == t.mt);
        }

        @Override
        public int hashCode() {
            return Integer.valueOf(mk).hashCode() + Integer.valueOf(mt).hashCode();
        }
    }
}
