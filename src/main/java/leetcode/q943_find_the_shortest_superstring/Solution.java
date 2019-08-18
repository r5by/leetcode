package leetcode.q943_find_the_shortest_superstring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 解題思路：
 * 1）key idea在於將每兩個字符串組成最短 superstring 的時候，紀錄兩者的距離，距離定義為 dist(string a, string b); 這樣就將此問題轉化為了tsp問題；
 * 2）使用tsp解法，稍微不同的是紀錄每個狀態 dp[i][S] 為包含字符串i的某個子集合，且以i為首字符的最短superstring;
 * 3) 這裡特別強調一下不要重複計算，即dp[i][S]已經被定義為i打頭，一旦重複計算將會出錯!
 */

class Solution {
    private static Logger LOG = LoggerFactory.getLogger(Solution.class);

    public String shortestSuperstring(String[] A) {
        if (A == null || A.length < 1) return "";

        //計算每兩個字符串之間的距離
        int n = A.length;
        int[][] c = new int[n][n];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i; j < n; j++) {
                c[i][j] = dist(A[i], A[j]);
                c[j][i] = dist(A[j], A[i]);
            }
        }

        //TSP
        int[][] dp = new int[n][1 << n]; //dp[i][S] remembers the shortest string length starts with string i, containing all strings including i in the subset S
//        int[][] memo = new int[n][1 << n]; //mem[i][S] remembers the selected string at end of the formed shortest string in state dp[i][S]: no need since we decide to append i at head for every scenario
        int[][] path = new int[n][1 << n];//path[i][S] remembers the previous choice of state dp[i][S]

        for (int S = 0; S < (1 << n); S++) {//all subset of combinations of n strings
            if (S == (1 << n) - 1)
                LOG.debug("");

            for (int i = 0; i < n; i++) {//the i-th string in A

                if (i == 3)
                    LOG.debug("");

                if (S == 0) {//init
                    Arrays.fill(dp[i], Integer.MAX_VALUE);
                } else {
                    int selected = 1 << i;
                    if ((selected & S) == 0) continue; // selected ith string not within subset

                    if ((S - selected) == 0) {//init the state for single string
                        dp[i][S] = A[i].length();
//                        memo[i][S] = i;
                    } else {
                        for (int k = 0; k < n; k++) {
                            if (k == 1)
                                LOG.debug("");

                            //selected^S calculate the subset without i, e.g. "3^1" ==>  "0011" xor "0001" ==> "0010" ==> "2" (digit bit same then equals 0, else 1)
                            if (k == i || ((1 << k) & (S - selected)) == 0)
                                continue; //for each kth string in the subset without i

                            /*todo: REMEMBER here we already define dp[i][S] as shortest string for i at start, so there is no need to calculate tail scenario (if do it's wrong) */
                            /* Record the state based on the shortest string, appending i at head!! (not tail) */
                            int costOptAppendHead = dp[k][S - selected] - A[k].length() + A[i].length() + c[i][k];
//                            int costOptAppendTail = dp[k][S - selected] + c[memo[k][S - selected]][i];

                            if (costOptAppendHead < dp[i][S]) {
                                dp[i][S] = costOptAppendHead;
//                                memo[i][S] = memo[k][S - selected]; //not used!
                                path[i][S] = k;
                            }

//                            if (costOptAppendTail < dp[i][S]) {
//                                dp[i][S] = costOptAppendTail;
//                                memo[i][S] = i;
//                                path[i][S] = k;
//                            }
                        }
                    }
                }
            }//end row loop
        }//end column loop

        //find the start string that forms the shortest superstring, in last column
        int k = 0, last = 0;
        int min = Integer.MAX_VALUE;
        int lastSet = (1 << n) - 1;
        for (; k < n; k++) {
            LOG.debug("Shortest string length starts with: " + k + " is" + dp[k][lastSet]);
            if (dp[k][lastSet] < min) {
                min = dp[k][lastSet];
                last = k;
            }
        }

        //populate result
//        return populateResult(A, memo, path, c, "", last, lastSet); //removed!
        return populateResult(A, path, c, "", last, lastSet);
    }

    private String populateResult(final String[] A, final int[][] path, final int[][] c, String s, int i, int S) {
        if (S - (1 << i) == 0)
            return A[i];

        int pre = path[i][S];
        String preStr = populateResult(A, path, c, s, path[i][S], S - (1 << i));
//        if (memo[i][S] == i) {//append i in tail scenario, removed!!
//            int cost = c[memo[pre][S - (1 << i)]][i];
//            return preStr + A[i].substring(A[i].length() - cost);
//        } else {
        int cost = c[i][pre];
        return A[i] + preStr.substring(A[pre].length() - cost);
//        }
    }


    /**
     * Return cost of using a as head and attach b to the tail as superstring.
     * e.g. "abcd" , "cde" ==> return the cost of 1
     *
     * @param a
     * @param b
     * @return
     */
    private int dist(String a, String b) {
        for (int i = 1; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return b.length() - a.length() + i;
            }
        }
        return b.length();
    }

    /* Reference */
//    public String shortestSuperstring(String[] A) {
//        int n = A.length;
//        int[][] graph = new int[n][n];
//        // build the graph
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                graph[i][j] = calc(A[i], A[j]);
//                graph[j][i] = calc(A[j], A[i]);
//            }
//        }
//        int[][] dp = new int[1 << n][n];
//        int[][] path = new int[1 << n][n];
//        int last = -1, min = Integer.MAX_VALUE;
//
//        // start TSP DP
//        for (int i = 1; i < (1 << n); i++) {
//            Arrays.fill(dp[i], Integer.MAX_VALUE);
//            for (int j = 0; j < n; j++) {
//                if ((i & (1 << j)) > 0) {
//                    int prev = i - (1 << j);
//                    if (prev == 0) {
//                        dp[i][j] = A[j].length();
//                    } else {
//                        for (int k = 0; k < n; k++) {
//                            if (dp[prev][k] < Integer.MAX_VALUE && dp[prev][k] + graph[k][j] < dp[i][j]) {
//                                dp[i][j] = dp[prev][k] + graph[k][j];
//                                path[i][j] = k;
//                            }
//                        }
//                    }
//                }
//                if (i == (1 << n) - 1 && dp[i][j] < min) {
//                    min = dp[i][j];
//                    last = j;
//                }
//            }
//        }
//
//        // build the path
//        StringBuilder sb = new StringBuilder();
//        int cur = (1 << n) - 1;
//        Stack<Integer> stack = new Stack<>();
//        while (cur > 0) {
//            stack.push(last);
//            int temp = cur;
//            cur -= (1 << last);
//            last = path[temp][last];
//        }
//
//        // build the result
//        int i = stack.pop();
//        sb.append(A[i]);
//        while (!stack.isEmpty()) {
//            int j = stack.pop();
//            sb.append(A[j].substring(A[j].length() - graph[i][j]));
//            i = j;
//        }
//        return sb.toString();
//    }

//    private int calc(String a, String b) {
//        for (int i = 1; i < a.length(); i++) {
//            if (b.startsWith(a.substring(i))) {
//                return b.length() - a.length() + i;
//            }
//        }
//        return b.length();
//    }
}
